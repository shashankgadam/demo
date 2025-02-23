package com.ikshusaram.demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ikshusaram.demo.dto.LoginRequest;
import com.ikshusaram.demo.dto.LoginResponse;
import com.ikshusaram.demo.entities.UserEntity;
import com.ikshusaram.demo.repositories.UserRepository;
import com.ikshusaram.demo.security.JwtUtil;

import org.springframework.security.crypto.password.PasswordEncoder;
@Service
public class UserServices {
	
	@Autowired
	UserRepository user_repo;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	JwtUtil jwtUtil;
	//Registration of User
	public String registerUser(UserEntity user) {
		
		try {
			String newPassword = passwordEncoder.encode(user.getPassword());
			user.setPassword(newPassword);
			user_repo.save(user);

			return "User Added Successfully";
		}
		catch(Exception e) {
			throw e;
		}
	}
	
	//Login of User
	public LoginResponse<?> loginUser(LoginRequest loginRequest) {
		String accessToken = "";
		String refreshToken = "";
		Optional<UserEntity> user = user_repo.findByEmail(loginRequest.email);
		if (user.isPresent()) {
			System.out.println(user.get().getEmail());
			if(passwordEncoder.matches(loginRequest.password, user.get().getPassword())){
				accessToken = jwtUtil.generateAccessToken(user.get());
				refreshToken = jwtUtil.generateRefreshToken(user.get());
			}
			return new LoginResponse("Success", 200,true, accessToken ,refreshToken);

		} else {
			return new LoginResponse("Invalid email or password", 401, false, "", "");
		}
	}
	
}
