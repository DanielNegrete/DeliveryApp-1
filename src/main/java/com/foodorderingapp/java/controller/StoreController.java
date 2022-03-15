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

import com.foodorderingapp.java.dto.ResponseDTO;
import com.foodorderingapp.java.dto.StoreRequestDTO;
import com.foodorderingapp.java.dto.StoreResponseDTO;
import com.foodorderingapp.java.entity.Store;
import com.foodorderingapp.java.service.StoreService;

@RestController
public class StoreController {
	
	@Autowired
	StoreService storeService;
	
	@GetMapping("/stores")
	public ResponseEntity<StoreResponseDTO> getAllStoreDetails(
			@RequestParam(defaultValue = "0") Integer pageNo, 
			@RequestParam(defaultValue =  "10") Integer pageSize){
		StoreResponseDTO storeResponseDTO = storeService.getAllStoreDetails(pageNo, pageSize);
		return new ResponseEntity<StoreResponseDTO>(storeResponseDTO, HttpStatus.OK);
	}
	
	@PostMapping("/store")
	public ResponseEntity<ResponseDTO> saveStore(@Valid @RequestBody StoreRequestDTO storeRequestDTO){
		storeService.saveStoreDetails(storeRequestDTO);
		
		return new ResponseEntity<ResponseDTO>(new ResponseDTO("Store Saved Success", 200), HttpStatus.ACCEPTED);
	}
}
