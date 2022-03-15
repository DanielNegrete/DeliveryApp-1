package com.foodorderingapp.java.service;

import com.foodorderingapp.java.dto.ProductRequestDTO;
import com.foodorderingapp.java.dto.ProductResponseDTO;

public interface ProductService {

	void saveProductDetails(ProductRequestDTO productRequestDTO);

	ProductResponseDTO getAllProductsDetails(Integer storeId, Integer pageNo, Integer pageSize);
	
}
