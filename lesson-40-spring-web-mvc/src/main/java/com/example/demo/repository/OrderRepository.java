package com.example.demo.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.example.demo.model.Order;

@Repository
public class OrderRepository {
	private Map<Integer, Order> orders = new HashMap<>();

	public Order getOrderById(int id) {
		return orders.get(id);
	}

	public Map<Integer, Order> getAllOrders() {
		return orders;
	}

	public void addOrder(Order order) {
		orders.put(order.getId(), order);
	}
}
