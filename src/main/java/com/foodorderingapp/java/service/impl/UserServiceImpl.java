package com.foodorderingapp.java.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.foodorderingapp.java.dto.LoginRequestDTO;
import com.foodorderingapp.java.dto.ResponseDTO;
import com.foodorderingapp.java.dto.UserRequestDTO;
import com.foodorderingapp.java.entity.Address;
import com.foodorderingapp.java.entity.User;
import com.foodorderingapp.java.repo.UserRepo;
import com.foodorderingapp.java.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserRepo userRepo;
	
	@Override
	public void saveUser(UserRequestDTO userRequestDTO) {
		User user = new User();
		BeanUtils.copyProperties(userRequestDTO, user);
		user.setAddressList(userRequestDTO.getAddress());
		
		userRepo.save(user);
	}

	@Override
	public ResponseDTO login(@Valid LoginRequestDTO loginRequestDTO) {
		List<User> userList = userRepo.findByUsernameAndPassword(loginRequestDTO.getUsername(), loginRequestDTO.getPassword());
		ResponseDTO responseDTO = new ResponseDTO("", 0);
		
		if(userList.isEmpty()){
			responseDTO.setMessage("Bad credentials");
			responseDTO.setStatusCode(404);
		}
		else{
			responseDTO.setMessage("User accepted");
			responseDTO.setStatusCode(200);
		}
		return responseDTO;
	}
}
