package com.foodorderingapp.java.service;

import javax.validation.Valid;

import com.foodorderingapp.java.dto.LoginRequestDTO;
import com.foodorderingapp.java.dto.ResponseDTO;
import com.foodorderingapp.java.dto.UserRequestDTO;

public interface UserService {

	void saveUser(UserRequestDTO userRequestDTO);

	ResponseDTO login(@Valid LoginRequestDTO loginRequestDTO);

}
