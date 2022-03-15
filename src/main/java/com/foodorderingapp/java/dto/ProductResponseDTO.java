package com.foodorderingapp.java.dto;

import java.util.ArrayList;
import java.util.List;

import com.foodorderingapp.java.entity.Product;
import com.foodorderingapp.java.service.dto.ProductDetails;

public class ProductResponseDTO extends ResponseDTO{
	
	public ProductResponseDTO(String message, int statusCode) {
		super(message, statusCode);
		// TODO Auto-generated constructor stub
	}

	private List<ProductDetails> productsList = new ArrayList<>();
	
	@Override
	public String toString() {
		return "ProductResponseDTO [productsList=" + productsList + "]";
	}

	public List<ProductDetails> getProductsList() {
		return productsList;
	}

	public void setProductsList(List<ProductDetails> productsList) {
		this.productsList = productsList;
	} 
	
}
