package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Order;
import com.example.demo.repository.OrderRepository;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/orders")
public class OrderController {
	private OrderRepository orderRepository;

	@GetMapping("/getById")
	public Order getById(int id) {
		return orderRepository.getById(id);
	}

	@GetMapping("/getAll")
	public List<Order> getAll() {
		return orderRepository.getAll();
	}

	@PostMapping("/add")
	public void add(@RequestBody Order order) {
		orderRepository.add(order);
	}

	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable int id) {
		orderRepository.delete(id);
	}

	@PutMapping("/update/{id}")
	public void update(@PathVariable int id, @RequestBody Order order) {
		orderRepository.update(id, order);
	}
}
