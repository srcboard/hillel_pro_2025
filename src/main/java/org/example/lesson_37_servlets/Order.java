package org.example.lesson_37_servlets;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class Order {
    private long id;
    private LocalDate date;
    private double cost;
    private List<Product> products;
}
