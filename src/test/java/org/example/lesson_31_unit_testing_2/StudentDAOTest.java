package org.example.lesson_31_unit_testing_2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentDAOTest {

    private Connection connection;
    private StudentDAO studentDAO;

    @BeforeEach
    void setup() {
        connection = mock(Connection.class);
        studentDAO = new StudentDAO(connection);
    }

    @Test
    void testGetAll() throws Exception {
        Statement statement = mock(Statement.class);
        ResultSet resultSet = mock(ResultSet.class);

        when(connection.createStatement()).thenReturn(statement);

        when(statement.executeQuery(anyString())).thenReturn(resultSet);

        when(resultSet.next()).thenReturn(true, false);
        when(resultSet.getInt("id")).thenReturn(1);
        when(resultSet.getString("name")).thenReturn("Test Student");
        when(resultSet.getString("phone_number")).thenReturn("1234567890");
        when(resultSet.getString("description")).thenReturn("Test Description");
        when(resultSet.getInt("hillel_group_id")).thenReturn(42);

        List<Student> students = studentDAO.getAll();

        assertEquals(1, students.size());
        Student student = students.getFirst();
        assertEquals(1, student.getId());
        assertEquals("Test Student", student.getName());
        assertEquals("1234567890", student.getPhoneNumber());
        assertEquals("Test Description", student.getDescription());
        assertEquals(42, student.getGroupId());

        verify(connection).createStatement();
        verify(statement).executeQuery(anyString());
        verify(resultSet, times(2)).next();
    }
}
