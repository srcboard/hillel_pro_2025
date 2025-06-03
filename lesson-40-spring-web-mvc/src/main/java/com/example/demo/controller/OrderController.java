package com.example.demo.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Order;
import com.example.demo.repository.OrderRepository;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {
	private OrderRepository orderRepository;

	@GetMapping("/getById")
	public Order getOrderById(int id) {
		return orderRepository.getOrderById(id);
	}

	@PostMapping("/add")
	public void addOrder(@RequestBody Order order) {
		orderRepository.addOrder(order);
	}

	@GetMapping("/getAll")
	public Map<Integer, Order> getAllOrders() {
		return orderRepository.getAllOrders();
	}
}
