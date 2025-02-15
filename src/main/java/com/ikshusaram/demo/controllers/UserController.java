package com.ikshusaram.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ikshusaram.demo.entities.UserEntity;
import com.ikshusaram.demo.services.UserServices;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserServices user_service;
	
	@RequestMapping("/add")
	public String addUser(@RequestBody UserEntity user) {
		
		return user_service.registerUser(user);
	}
	
	
}
