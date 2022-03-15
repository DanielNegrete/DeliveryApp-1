package com.foodorderingapp.java.entity;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Store {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer storeId;
	private String storeName;
	private Address address;
	private float rating;
	
	@OneToMany(mappedBy = "store")
	private List<Product> productList = new ArrayList<>();
	
	private LocalTime openTill;
	private String storeDescription;
	
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public float getRating() {
		return rating;
	}
	public void setRating(float rating) {
		this.rating = rating;
	}
	public List<Product> getProductList() {
		return productList;
	}
	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}
	public LocalTime getOpenTill() {
		return openTill;
	}
	public void setOpenTill(LocalTime openTill) {
		this.openTill = openTill;
	}
	public String getStoreDescription() {
		return storeDescription;
	}
	public void setStoreDescription(String storeDescription) {
		this.storeDescription = storeDescription;
	}
}
