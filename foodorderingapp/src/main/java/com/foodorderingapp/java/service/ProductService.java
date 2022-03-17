package com.foodorderingapp.java.service;

import com.foodorderingapp.java.dto.ProductRequestDTO;
import com.foodorderingapp.java.dto.ProductResponseDTO;
import com.foodorderingapp.java.entity.Product;

public interface ProductService {

	Product saveProductDetails(ProductRequestDTO productRequestDTO);

	ProductResponseDTO getAllProductsDetails(Integer storeId, Integer pageNo, Integer pageSize);
	
}
