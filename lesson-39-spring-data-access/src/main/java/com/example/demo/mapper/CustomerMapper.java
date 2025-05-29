package com.example.demo.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.demo.dto.CustomerDto;

public class CustomerMapper implements RowMapper<CustomerDto> {

	@Override
	public CustomerDto mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new CustomerDto(
				rs.getInt("id"), 
				rs.getString("fullName"), 
				rs.getString("email"),
				rs.getInt("socialSecurityNumber"));
	}

}
