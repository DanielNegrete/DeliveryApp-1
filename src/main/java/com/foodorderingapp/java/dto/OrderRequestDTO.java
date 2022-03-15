package com.foodorderingapp.java.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.foodorderingapp.java.entity.OrderProduct;

public class OrderRequestDTO {
	
	@NotNull
	private Integer userId;
	
	@NotNull
	private Integer storeId;
	
	@Size(min = 1, message = "Product list should not be empty")
	private List<OrderProduct> productList = new ArrayList<>();
	
	@NotNull
	private double totalPrice;
	
	private String instruction;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	public List<OrderProduct> getProductList() {
		return productList;
	}
	public void setProductList(List<OrderProduct> productList) {
		this.productList = productList;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getInstruction() {
		return instruction;
	}
	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}
}
