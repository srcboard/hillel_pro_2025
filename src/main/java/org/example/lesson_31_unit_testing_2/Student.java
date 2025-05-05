package org.example.lesson_31_unit_testing_2;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class Student {
  private int id;
  private String name;
  private String phoneNumber;
  private String description;
  private int groupId;

  public Student(String name, String phoneNumber, String description, int groupId) {
    this.name = name;
    this.phoneNumber = phoneNumber;
    this.description = description;
    this.groupId = groupId;
  }
}
