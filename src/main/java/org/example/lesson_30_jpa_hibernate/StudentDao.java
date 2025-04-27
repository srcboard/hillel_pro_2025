package org.example.lesson_30_jpa_hibernate;

import jakarta.persistence.*;

import java.util.List;

public class StudentDao implements GenericDao<Student, Long> {

    private final EntityManagerFactory entityManagerFactory;

    public StudentDao(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public void save(Student student) {
        EntityTransaction transaction = null;
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            transaction = entityManager.getTransaction();
            transaction.begin();
            TypedQuery<Student> query = entityManager.createQuery(
                    "SELECT s FROM Student s WHERE s.email = :email", Student.class);
            query.setParameter("email", student.getEmail());
            List<Student> foundsStudent = query.getResultList();
            if (foundsStudent.isEmpty()) {
                entityManager.persist(student);
                transaction.commit();
            } else {
                transaction.rollback();
                student.setId(foundsStudent.getFirst().getId());
            }
        } catch (RuntimeException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Error while saving the student", e);
        }
    }

    @Override
    public Student findById(Long id) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            return entityManager.find(Student.class, id);
        }
    }

    @Override
    public Student findByEmail(String email) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            TypedQuery<Student> query = entityManager.createQuery(
                    "SELECT s FROM Student s WHERE s.email = :email", Student.class);
            query.setParameter("email", email);
            return query.getResultStream().findFirst().orElse(null);
        }
    }

    @Override
    public List<Student> findAll() {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            return entityManager.createQuery("SELECT s FROM Student s", Student.class)
                    .getResultList();
        }
    }

    @Override
    public Student update(Student student) {
        EntityTransaction transaction = null;
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            transaction = entityManager.getTransaction();
            transaction.begin();
            Student updatedStudent = entityManager.merge(student);
            transaction.commit();
            return updatedStudent;
        } catch (RuntimeException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Error updating student");
        }
    }

    @Override
    public boolean deleteById(Long id) {
        EntityTransaction transaction = null;
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            transaction = entityManager.getTransaction();
            transaction.begin();
            Student student = entityManager.find(Student.class, id);
            if (student != null) {
                entityManager.remove(student);
                entityManager.getTransaction().commit();
                entityManager.close();
                return true;
            } else {
                transaction.rollback();
                return false;
            }
        } catch (RuntimeException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Error deleting student");
        }
    }
}
