package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

	@GetMapping("/public")
	public String publicPage() {
		return "Public page - No login required";
	}

	@GetMapping("/user")
	public String userPage() {
		return "User page - ROLE_USER required";
	}

	@GetMapping("/admin")
	public String adminPage() {
		return "Admin page - ROLE_ADMIN required";
	}
}
