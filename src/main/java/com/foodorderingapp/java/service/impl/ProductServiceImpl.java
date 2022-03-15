package com.foodorderingapp.java.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.foodorderingapp.java.dto.ProductRequestDTO;
import com.foodorderingapp.java.dto.ProductResponseDTO;
import com.foodorderingapp.java.entity.Product;
import com.foodorderingapp.java.entity.ProductCategory;
import com.foodorderingapp.java.entity.Store;
import com.foodorderingapp.java.exception.CategoryNotFound;
import com.foodorderingapp.java.exception.StoreNotFoundException;
import com.foodorderingapp.java.repo.ProductRepo;
import com.foodorderingapp.java.repo.StoreRepo;
import com.foodorderingapp.java.service.ProductService;
import com.foodorderingapp.java.service.dto.ProductDetails;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	StoreRepo storeRepo;
	
	@Autowired
	ProductRepo productRepo;
	
	@Override
	public void saveProductDetails(ProductRequestDTO productRequestDTO) {
		Product product = new Product();
		BeanUtils.copyProperties(productRequestDTO, product);
		
		if(productRequestDTO.getProductCategory().compareToIgnoreCase("VEG") < 0 || productRequestDTO.getProductCategory().compareToIgnoreCase("NONVEG") < 0)
			throw new CategoryNotFound("Category not found: " + productRequestDTO.getProductCategory());
			
		product.setProductCategory(ProductCategory.valueOf(productRequestDTO.getProductCategory()));
		
		Optional<Store> storeOptional =  storeRepo.findById(productRequestDTO.getStoreId());
		
		
		if(storeOptional.isEmpty())
			throw new StoreNotFoundException("Store not found: " + productRequestDTO.getStoreId());
			
		product.setStore(storeOptional.get());
		productRepo.save(product);
	}

	@Override
	public ProductResponseDTO getAllProductsDetails(Integer storeId, Integer pageNo, Integer pageSize) {
		Pageable paging = PageRequest.of(pageNo, pageSize);
		
		Optional<Store> optionalStore = storeRepo.findById(storeId);
		
		if(optionalStore.isEmpty())
			throw new StoreNotFoundException("Store not found: " + storeId);
		
		Store store = optionalStore.get();
		
		List<Product> productList = productRepo.findByStore(store, paging);
		
		List<ProductDetails> productDetailsList = productList.stream().map(product -> {
			ProductDetails productDetails = new ProductDetails();
			BeanUtils.copyProperties(product, productDetails);
			return productDetails;
		}).collect(Collectors.toList());
		
		ProductResponseDTO productResponseDTO = new ProductResponseDTO("Product List Fecth", 200);
		productResponseDTO.setProductsList(productDetailsList);
		
		return productResponseDTO;
	}
}
