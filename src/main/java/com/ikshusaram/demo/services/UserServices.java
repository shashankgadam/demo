package com.ikshusaram.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ikshusaram.demo.entities.UserEntity;
import com.ikshusaram.demo.repositories.UserRepository;

@Service
public class UserServices {
	
	@Autowired
	UserRepository user_repo;
	
	//Registration of User
	public String registerUser(UserEntity user) {
		
		try {
			user_repo.save(user);
			return "User Added Successfully";
		}
		catch(Exception e) {
			throw e;
		}
	}
	
	//Login of User
	
//	public String loginUser(UserEntity user) {
//		
//	}
	
}
