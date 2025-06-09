package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
	@Query(value = "SELECT * FROM posts WHERE user_id=?", nativeQuery = true)
	public List<Post> getAllPostsByUserId(int id);
}
