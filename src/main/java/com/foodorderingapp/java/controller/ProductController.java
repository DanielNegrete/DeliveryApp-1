package com.foodorderingapp.java.controller;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.foodorderingapp.java.constants.ApiConstants;
import com.foodorderingapp.java.dto.ProductRequestDTO;
import com.foodorderingapp.java.dto.ProductResponseDTO;
import com.foodorderingapp.java.dto.ResponseDTO;
import com.foodorderingapp.java.dto.StoreResponseDTO;
import com.foodorderingapp.java.exception.ErrorResponse;
import com.foodorderingapp.java.exception.StoreNotFoundException;
import com.foodorderingapp.java.service.ProductService;

@RestController
@Validated
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@PostMapping("/products")
	public ResponseEntity<ResponseDTO> saveProductDetails(@Valid @RequestBody ProductRequestDTO productRequestDTO){
		productService.saveProductDetails(productRequestDTO);
		
		return new ResponseEntity<ResponseDTO>(new ResponseDTO("Product Saved Success", 200), HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/stores/{storeId}/products")
	public ResponseEntity<ResponseDTO> getAllProductDetails(
			@RequestParam(defaultValue = "0") Integer pageNo, 
			@RequestParam(defaultValue =  "10") Integer pageSize,
			@NotNull @Min(1) @PathVariable Integer storeId){
		ProductResponseDTO productResponseDTO = productService.getAllProductsDetails(storeId, pageNo, pageSize);
		
		return new ResponseEntity<ResponseDTO>(productResponseDTO, HttpStatus.OK);
	}
}
