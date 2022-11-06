package com.example.userservice.controller;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.userservice.dto.UserResponse;
import com.example.userservice.model.User;
import com.example.userservice.service.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;



@RestController
@RequestMapping("/api/users")
public class UserController {
	public static final String USER_SERVICE="userService";
	private int attempt=1;
	@Autowired
	private UserService userService;
	
	@PostMapping
	public User create(@RequestBody User d) {
		return userService.create(d);
	}
	
	//@CircuitBreaker(name =  USER_SERVICE,fallbackMethod = "getAllAvailableProducts")
	@Retry(name = USER_SERVICE,fallbackMethod = "getAllAvailableProducts")
	@GetMapping("/{id}")
	public UserResponse getUserById(@PathVariable("id") Long id) {
		System.out.println("retry method called "+ attempt++ +" times "+" at "+new Date());
		return userService.getOne(id);
	}
	
	@GetMapping("/hello")
	public String hello() {
		return "heello";
	}
	
	@GetMapping("/getAllAvailableProducts")
	  public UserResponse getAllAvailableProducts(Exception e){
		return null;
	}
}
