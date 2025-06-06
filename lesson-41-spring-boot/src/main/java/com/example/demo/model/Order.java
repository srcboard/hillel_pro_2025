package com.example.demo.model;

import java.time.LocalDate;
import java.util.List;

import lombok.Getter;

@Getter
public class Order {
	private int id;
	private int totalCost;
	private List<Product> products;
	private LocalDate createdAt;

	public Order(int id, int totalCost, LocalDate createdAt) {
		this.id = id;
		this.totalCost = totalCost;
		this.createdAt = createdAt;
	}
}
