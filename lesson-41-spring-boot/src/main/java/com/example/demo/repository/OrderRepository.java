package com.example.demo.repository;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.mapper.OrderMapper;
import com.example.demo.model.Order;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class OrderRepository {
	private JdbcTemplate jdbcTemplate;

	public Order getById(int id) {
		return jdbcTemplate.queryForObject("SELECT * FROM public.order WHERE id = ?", new OrderMapper(), id);
	}

	public List<Order> getAll() {
		return jdbcTemplate.query("SELECT * FROM public.order", new OrderMapper());
	}

	public void add(Order order) {
		jdbcTemplate.update("INSERT INTO public.order (id, total_cost, created_at) VALUES (?, ?, ?)", order.getId(),
				order.getTotalCost(), order.getCreatedAt());
	}

	public void delete(int id) {
		jdbcTemplate.update("DELETE FROM public.order WHERE id = ?", id);
	}

	public void update(int id, Order order) {
		jdbcTemplate.update("UPDATE public.order SET total_cost = ?, created_at = ? WHERE id = ?", order.getTotalCost(),
				order.getCreatedAt(), id);
	}
}
