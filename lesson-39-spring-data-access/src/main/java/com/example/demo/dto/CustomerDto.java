package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class CustomerDto {
	private int id;
	private String fullName;
	private String email;
	private int socialSecurityNumber;
	
	public CustomerDto(String fullName, String email, int socialSecurityNumber) {
		this.fullName = fullName;
		this.email = email;
		this.socialSecurityNumber = socialSecurityNumber;
	}
	
}
