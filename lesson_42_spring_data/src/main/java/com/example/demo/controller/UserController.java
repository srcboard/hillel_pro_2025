package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.service.TransactionService;

import lombok.AllArgsConstructor;

@RequestMapping("/users")
@AllArgsConstructor
@RestController
public class UserController {
	private TransactionService transactionService;

	@PostMapping("/add")
	public ResponseEntity<?> add(@RequestBody User user) {
		transactionService.addUser(user);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/getByName")
	public User getByName(@RequestParam String name) {
		return transactionService.getUserByName(name);
	}

	@GetMapping("/getByDomain")
	public List<User> getUsersByDomain(@RequestParam String domain) {
		return transactionService.getUsersByDomain(domain);
	}
}
