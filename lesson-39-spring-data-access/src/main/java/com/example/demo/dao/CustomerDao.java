package com.example.demo.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.CustomerDto;
import com.example.demo.mapper.CustomerMapper;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class CustomerDao {
	private final JdbcTemplate jdbcTemplate;

	public void add(CustomerDto customerDto) {
		jdbcTemplate.update(
				"INSERT INTO customer (\"fullName\", email, \"socialSecurityNumber\") VALUES (?, ?, ?)",
				customerDto.getFullName(), 
				customerDto.getEmail(), 
				customerDto.getSocialSecurityNumber());
	}

	public CustomerDto getById(int id) {
		try {
			return jdbcTemplate.queryForObject("SELECT * FROM customer WHERE id = ?", new CustomerMapper(), id);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public void update(CustomerDto customerDto) {
		jdbcTemplate.update("UPDATE customer SET \"fullName\" = ?, email = ?, \"socialSecurityNumber\" = ? WHERE id = ?",
				customerDto.getFullName(), 
				customerDto.getEmail(), 
				customerDto.getSocialSecurityNumber(),
				customerDto.getId());
	}
	
	public void delete(int id) {
		jdbcTemplate.update("DELETE FROM customer WHERE id = ?", id);
	}
}
