package org.example.lesson_29_jdbc_orm;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        DatabaseConnector db = new DatabaseConnector();

        try {
            db.connect();
            db.createTableIfNotExists();

            EmployeeDAO dao = new EmployeeDAO(db.getConnection());

            dao.addEmployee(new Employee("Ivan Petrov", 30, "Developer", 1000.0f));
            dao.addEmployee(new Employee("Olga Ivanova", 25, "Designer", 900.0f));
            System.out.println("After addition:");
            dao.getAllEmployees().forEach(System.out::println);

            dao.updateEmployee(new Employee(1, "Ivan Petrov", 31, "Senior Developer", 1200.0f));
            System.out.println("After updating:");
            dao.getAllEmployees().forEach(System.out::println);

            dao.deleteEmployee(2);
            System.out.println("After deletion:");
            dao.getAllEmployees().forEach(System.out::println);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
    }
}