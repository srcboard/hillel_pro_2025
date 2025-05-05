package org.example.lesson_30_jpa_hibernate;

import org.junit.jupiter.api.*;
import jakarta.persistence.*;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

public class StudentDaoTest {

    private static EntityManagerFactory entityManagerFactory;
    private static StudentDao studentDao;

    @BeforeAll
    public static void setupClass() {
        entityManagerFactory =
                Persistence.createEntityManagerFactory("hillel-persistence-unit");
        studentDao = new StudentDao(entityManagerFactory);
    }

    @AfterAll
    public static void tearDownClass() {
        if (entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
        }
    }

    @Test
    public void testSaveStudent() {
        Student student = new Student("John", "Doe", "test@example.com");
        Homework homework = new Homework(
                "Homework", LocalDate.of(2025, 6, 1), 0, student);
        student.addHomework(homework);
        studentDao.save(student);
        Student savedStudent = studentDao.findByEmail("test@example.com");
        assertNotNull(savedStudent);
        assertEquals(student, savedStudent);
    }

    @Test
    public void testFindById() {
        Student student = new Student("Jane", "Doe", "find@example.com");
        studentDao.save(student);
        Student foundStudent = studentDao.findById(student.getId());
        assertNotNull(foundStudent);
        assertEquals(student.getId(), foundStudent.getId());
        assertEquals(student, foundStudent);
    }

    @Test
    public void testFindByEmail() {
        Student student = new Student("Mike", "Ross", "uniqueemail@example.com");
        studentDao.save(student);
        Student foundStudent = studentDao.findByEmail("uniqueemail@example.com");
        assertNotNull(foundStudent);
        assertEquals(student, foundStudent);
    }

    @Test
    public void testFindAll() {
        List<Student> studentsBefore = studentDao.findAll();
        int sizeAfter = studentsBefore.size();
        Student student1 = new Student("Student", "One", "email1@example.com");
        if (!studentsBefore.contains(student1)) {
            studentDao.save(student1);
            sizeAfter++;
        }
        Student student2 = new Student("Student", "Two", "email2@example.com");
        if (!studentsBefore.contains(student2)) {
            studentDao.save(student2);
            sizeAfter++;
        }
        List<Student> studentsAfter = studentDao.findAll();
        assertNotNull(studentsAfter);
        assertEquals(studentsAfter.size(), sizeAfter);
    }

    @Test
    public void testUpdateStudent() {
        Student student = new Student("Old", "Name", "update@example.com");
        Homework homework = new Homework(
                "Homework", LocalDate.of(2025, 5, 10), 0, student);
        student.addHomework(homework);
        studentDao.save(student);
        student.setFirstName("Updated Name");
        student.removeHomework(homework);
        Student updatedStudent = studentDao.update(student);
        assertNotNull(updatedStudent);
        assertEquals(student, updatedStudent);
    }

    @Test
    public void testDeleteById() {
        Student student =
                new Student("To Be Deleted", "Name", "delete@example.com");
        studentDao.save(student);
        boolean deleted = studentDao.deleteById(student.getId());
        assertTrue(deleted);
        Student deletedStudent = studentDao.findById(student.getId());
        assertNull(deletedStudent);
    }

    @Test
    public void testDeleteNonExistingStudent() {
        boolean deleted = studentDao.deleteById(999L);
        assertFalse(deleted);
    }
}
