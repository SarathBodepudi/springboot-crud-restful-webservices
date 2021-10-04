package com.example.springbootcrudrestfulwebservices.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springbootcrudrestfulwebservices.entity.User;
import com.example.springbootcrudrestfulwebservices.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	// get all users
	// http://localhost:8080/api/users
	@GetMapping
	public List<User> getAllUsers() {
		ArrayList<User> usersList = prepareMockUsers();
		return usersList;
	}

	// get user by id
	// http://localhost:8080/api/users/1
	@GetMapping("/{id}")
	public User getUserById(@PathVariable (value = "id") long userId) {
		if(userId > 10) {
			throw new ResourceNotFoundException("User Not Found with id :" + userId);
		}
		return new User(1000L, "Sarath", "Bodepudi", "sarath.bodepudi@mutualofomaha.com");
	}
	
	// save user
	@PostMapping
	public User createUser(@RequestBody User user) {
		return user;
	}
	
	// update user
	@PutMapping("/{id}")
	public User updateUser(@RequestBody User user, @PathVariable("id") long userId) {
		User existingUser = new User(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail());
		return existingUser;
	}
	
	// delete user by id
	@DeleteMapping("/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable("id") long userId) {
		if(userId > 10) {
			throw new ResourceNotFoundException("User Not Found with id :" + userId);
		}
		return ResponseEntity.ok().build();
	}
	
	
	private ArrayList<User> prepareMockUsers() {
		ArrayList<User> users = new ArrayList<>();
		
		User user1 = new User(1000L, "Sarath", "Bodepudi", "sarath.bodepudi@mutualofomaha.com");
		User user2 = new User(1001L, "Sarath1", "Bodepudi1", "sarathbodepudi@gmail.com");
		users.add(user1);
		users.add(user2);
		return users;
	}
}
