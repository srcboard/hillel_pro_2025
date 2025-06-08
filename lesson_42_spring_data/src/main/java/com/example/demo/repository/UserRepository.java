package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	public List<User> getUsersByName(String name);

	@Query(value = "SELECT * FROM users WHERE email LIKE %:domain", nativeQuery = true)
	public List<User> getUsersByDomain(@Param("domain") String domain);
}
