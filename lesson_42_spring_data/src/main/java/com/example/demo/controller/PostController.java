package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Post;
import com.example.demo.service.TransactionService;

import lombok.AllArgsConstructor;

@RequestMapping("/posts")
@AllArgsConstructor
@RestController
public class PostController {
	private TransactionService transactionService;

	@PostMapping("/add")
	public ResponseEntity<?> add(@RequestBody Post post) {
		transactionService.addPost(post);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/getByUserId")
	public List<Post> getByUserId(@RequestParam int id) {
		return transactionService.getAllPostsByUserId(id);
	}
}
