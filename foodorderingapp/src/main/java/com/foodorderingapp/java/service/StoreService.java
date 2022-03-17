package com.foodorderingapp.java.service;

import com.foodorderingapp.java.dto.StoreRequestDTO;
import com.foodorderingapp.java.dto.StoreResponseDTO;

public interface StoreService {

	StoreResponseDTO getAllStoreDetails(Integer pageNo, Integer pageSize);

	void saveStoreDetails(StoreRequestDTO storeRequestDTO);

}
