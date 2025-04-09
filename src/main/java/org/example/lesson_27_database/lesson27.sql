CREATE DATABASE my_database;

USE my_database;

CREATE TABLE users (
    id    INT AUTO_INCREMENT PRIMARY KEY,
    name  VARCHAR(100),
    age   INT,
    email VARCHAR(100)
);

INSERT INTO users (name, age, email)
VALUES ('John', 30, 'john@example.com'),
       ('Alice', 25, 'alice@example.com'),
       ('Bob', 35, 'bob@example.com');

SELECT * FROM users;

DELETE FROM users WHERE name = 'Bob';

SELECT * FROM users;
