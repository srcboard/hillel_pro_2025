package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Post;
import com.example.demo.entity.User;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TransactionService {
	private UserRepository userRepository;
	private PostRepository postRepository;

	@Transactional
	public void addUser(User user) {
		userRepository.save(user);
	}

	@Transactional
	public void addPost(Post post) {
		postRepository.save(post);
	}

	public User getUserByName(String name) {
		return userRepository.getUsersByName(name).getFirst();
	}

	public List<User> getUsersByDomain(String domain) {
		return userRepository.getUsersByDomain(domain);
	}

	public List<Post> getAllPostsByUserId(int id) {
		return postRepository.getAllPostsByUserId(id);
	}
}
