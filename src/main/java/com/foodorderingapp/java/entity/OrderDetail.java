package com.foodorderingapp.java.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;

@Entity
public class OrderDetail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer orderDetailId;
	private LocalDate orderdate;
	private Integer userId;
	private Integer storeId;
	
	@ElementCollection
	@JoinTable(name = "orderproducts", joinColumns = @JoinColumn(name = "orderDetailId"))
	private List<OrderProduct> orderProductList = new ArrayList<>();
	private double totalPrice;
	
	@Enumerated(EnumType.STRING)
	private OrderStatus status;

	public Integer getOrderDetailId() {
		return orderDetailId;
	}

	public void setOrderDetailId(Integer orderDetailId) {
		this.orderDetailId = orderDetailId;
	}

	public LocalDate getOrderdate() {
		return orderdate;
	}

	public void setOrderdate(LocalDate orderdate) {
		this.orderdate = orderdate;
	}

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

	public List<OrderProduct> getOrderProductList() {
		return orderProductList;
	}

	public void setOrderProductList(List<OrderProduct> orderProductList) {
		this.orderProductList = orderProductList;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "OrderDetail [orderDetailId=" + orderDetailId + ", orderdate=" + orderdate + ", userId=" + userId
				+ ", storeId=" + storeId + ", orderProductList=" + orderProductList + ", totalPrice=" + totalPrice
				+ ", status=" + status + "]";
	}
}
