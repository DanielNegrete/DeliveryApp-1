package com.foodorderingapp.java.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.foodorderingapp.java.dto.LoginRequestDTO;
import com.foodorderingapp.java.dto.ResponseDTO;
import com.foodorderingapp.java.dto.StoreResponseDTO;
import com.foodorderingapp.java.dto.UserRequestDTO;
import com.foodorderingapp.java.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;
	
	@PostMapping("/user")
	public ResponseEntity<ResponseDTO> saveUser(@Valid @RequestBody UserRequestDTO userRequestDTO){
		userService.saveUser(userRequestDTO);
		
		return new ResponseEntity<ResponseDTO>(new ResponseDTO("User Saved Success", 200), HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<ResponseDTO> login(@Valid @RequestBody LoginRequestDTO loginRequestDTO){
		ResponseDTO responseDTO =  userService.login(loginRequestDTO);
		
		return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
	}

}
