package com.ikshusaram.demo.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ikshusaram.demo.dto.LoginRequest;
import com.ikshusaram.demo.dto.LoginResponse;
import com.ikshusaram.demo.entities.UserEntity;
import com.ikshusaram.demo.services.UserServices;

import jakarta.validation.Valid;
@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserServices user_service;
	
	@PostMapping("/add")
	public String addUser(@RequestBody UserEntity user) {
		
		return user_service.registerUser(user);
	}
	
	@PostMapping(value = "/login")
	public ResponseEntity<?> login(@Valid @RequestBody LoginRequest entity) {
		LoginResponse response = user_service.loginUser(entity);
		return ResponseEntity.ok(response);
	}
	
	
}
