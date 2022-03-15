package com.foodorderingapp.java.dto;

import java.util.ArrayList;
import java.util.List;

import com.foodorderingapp.java.service.dto.StoreDetails;

public class StoreResponseDTO extends ResponseDTO{
	
	public StoreResponseDTO(String message, int statusCode) {
		super(message, statusCode);
		// TODO Auto-generated constructor stub
	}
	private List<StoreDetails> storesList = new ArrayList<>();
	
	public List<StoreDetails> getStoresList() {
		return storesList;
	}
	public void setStoresList(List<StoreDetails> storesList) {
		this.storesList = storesList;
	}
}
