package com.foodorderingapp.java.service.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.foodorderingapp.java.entity.OrderProduct;

public class OrderUserDetails {
	
	private String storeName;
    private Integer storeId;
    private List<OrderProduct> productDetails = new ArrayList<>();
    private double totalPrice;
    private LocalDate orderDate;
    
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	public List<OrderProduct> getProductDetails() {
		return productDetails;
	}
	public void setProductDetails(List<OrderProduct> productDetails) {
		this.productDetails = productDetails;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public LocalDate getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}
	
	@Override
	public String toString() {
		return "OrderUserDetails [storeName=" + storeName + ", storeId=" + storeId + ", productDetails="
				+ productDetails + ", totalPrice=" + totalPrice + ", orderDate=" + orderDate + "]";
	}
	
}
