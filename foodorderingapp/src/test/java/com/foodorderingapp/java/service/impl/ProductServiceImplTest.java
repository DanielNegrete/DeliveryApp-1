package com.foodorderingapp.java.service.impl;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.ListAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.foodorderingapp.java.dto.ProductRequestDTO;
import com.foodorderingapp.java.entity.Address;
import com.foodorderingapp.java.entity.Product;
import com.foodorderingapp.java.entity.ProductCategory;
import com.foodorderingapp.java.entity.Store;
import com.foodorderingapp.java.exception.StoreNotFoundException;
import com.foodorderingapp.java.repo.ProductRepo;
import com.foodorderingapp.java.repo.StoreRepo;
import com.foodorderingapp.java.service.dto.ProductDetails;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

	@Mock
	StoreRepo storeRepo;
	
	@Mock
	ProductRepo productRepo;
	
	@InjectMocks
	ProductServiceImpl productServiceImpl;
	
	ProductRequestDTO productRequestDTO;
	Store store;
	Pageable paging;
	List<Product> productList;
	
	@BeforeEach
	public void setUp() {
		productRequestDTO = new ProductRequestDTO();
		productRequestDTO.setProductName("Veg Pizza");
		productRequestDTO.setProductCategory("VEG");
		productRequestDTO.setProductPrice(500);
		productRequestDTO.setProductDescription("Cheese Pizza");
		productRequestDTO.setStoreId(2);
		productRequestDTO.setAvailable(true);
	
		Product product = new Product();
		product.setProductId(1);
		product.setProductCategory(ProductCategory.VEG);
		product.setProductDescription("veg sandwich");
		product.setProductName("Sandwich");
		product.setProductPrice(100);
		product.setStore(store);
		
		Address address = new Address();
		address.setCity("Bangaluru");
		address.setPincode("560062");
		address.setStreet("Brigade Street");
		
		store = new Store();
		store.setStoreId(2);
		store.setProductList(List.of(product));
		store.setRating(3);
		store.setAddress(address);
		store.setStoreName("Hari Sandwich");
		store.setStoreDescription("Pizza, Sandwich, coke...");
		
		paging = PageRequest.of(0, 10);
	}
	
	@Test
	@DisplayName("Save product details: positive")
	public void saveProductDetailsTest() {
		when(storeRepo.findById(2)).thenReturn(Optional.of(store));
		
		when(productRepo.save(any(Product.class))).thenAnswer(i -> {
			Product product = i.getArgument(0);
			product.setProductId(2);
			return product;
		});
		
		Product productResult = productServiceImpl.saveProductDetails(productRequestDTO);
		assertNotNull(productResult);
		assertEquals(2, productResult.getStore().getStoreId());
		assertEquals("Veg Pizza", productResult.getProductName());
	}
	
	@Test
	@DisplayName("Save product details: negative")
	public void saveProductDetailsTest1() {
		when(storeRepo.findById(2)).thenReturn(Optional.empty());
		
		assertThrows(StoreNotFoundException.class, () -> productServiceImpl.saveProductDetails(productRequestDTO));
	}
	
	@Test
	@DisplayName("Get all product details: positive")
	public void getAllProductsDetails() {
		/*
		when(storeRepo.findById(2)).thenReturn(Optional.of(store));
		
		when(productRepo.findByStore(store, paging)).thenReturn(productList);
		
		
		Product productRes = productList.get(0);
		assertEquals(1, productRes.getProductId());
		*/
	}
	
	@Test
	@DisplayName("Get all product details: negative")
	public void getAllProductsDetails1() {
		when(storeRepo.findById(2)).thenReturn(Optional.empty());
		
		assertThrows(StoreNotFoundException.class, () -> productServiceImpl.getAllProductsDetails(2, 0, 10));
	}
	
}
