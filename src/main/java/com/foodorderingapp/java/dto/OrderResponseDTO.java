package com.foodorderingapp.java.dto;

import java.util.ArrayList;
import java.util.List;

import com.foodorderingapp.java.entity.OrderProduct;
import com.foodorderingapp.java.service.dto.OrderUserDetails;

public class OrderResponseDTO extends ResponseDTO{
	
	public OrderResponseDTO(String message, int statusCode) {
		super(message, statusCode);
		// TODO Auto-generated constructor stub
	}
	
	private List<OrderUserDetails> orderUserDetails = new ArrayList<>();

	public List<OrderUserDetails> getOrderUserDetails() {
		return orderUserDetails;
	}

	public void setOrderUserDetails(List<OrderUserDetails> orderUserDetails) {
		this.orderUserDetails = orderUserDetails;
	}
}
