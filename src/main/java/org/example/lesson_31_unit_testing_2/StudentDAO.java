package org.example.lesson_31_unit_testing_2;

import lombok.AllArgsConstructor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class StudentDAO {
  private Connection connection;

  public int add(Student student) throws SQLException {
    try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO students " +
            "(name, phone_number, description, hillel_group_id) VALUES (?,?,?,?)")) {
      preparedStatement.setString(1, student.getName());
      preparedStatement.setString(2, student.getPhoneNumber());
      preparedStatement.setString(3, student.getDescription());
      preparedStatement.setInt(4, student.getGroupId());
      return preparedStatement.executeUpdate();
    }
  }

  public List<Student> getAll() throws SQLException {
    List<Student> students = new ArrayList<>();
    try (Statement statement = connection.createStatement()) {
      try (ResultSet resultSet = statement.executeQuery("SELECT * FROM students")) {
        while (resultSet.next()) {
          students.add(new Student(
                  resultSet.getInt("id"),
                  resultSet.getString("name"),
                  resultSet.getString("phone_number"),
                  resultSet.getString("description"),
                  resultSet.getInt("hillel_group_id")));
        }
        return students;
      }
    }
  }
}
