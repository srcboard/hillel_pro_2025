package org.example.lesson_30_jpa_hibernate;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "homeworks")
public class Homework {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private LocalDate deadline;
    private int mark;
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    public Homework(String description, LocalDate deadline, int mark, Student student) {
        this.description = description;
        this.deadline = deadline;
        this.mark = mark;
        this.student = student;
    }

    public Homework() {

    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Homework homework = (Homework) o;
        return mark == homework.mark
                && Objects.equals(description, homework.description)
                && Objects.equals(deadline, homework.deadline)
                && Objects.equals(student, homework.student);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Homework{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", deadline=" + deadline +
                ", mark=" + mark +
                ", student=" + student +
                '}';
    }
}
