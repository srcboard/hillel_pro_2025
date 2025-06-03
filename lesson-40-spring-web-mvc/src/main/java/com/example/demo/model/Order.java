package com.example.demo.model;

import java.time.LocalDate;
import java.util.List;

import lombok.Getter;

@Getter
public class Order {
	private int id;
	private LocalDate creationDate;
	private int totalCost;
	private List<Product> products;
}
