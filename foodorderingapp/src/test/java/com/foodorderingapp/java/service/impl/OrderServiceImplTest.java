package com.foodorderingapp.java.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.foodorderingapp.java.dto.OrderRequestDTO;
import com.foodorderingapp.java.entity.OrderDetail;
import com.foodorderingapp.java.entity.OrderProduct;
import com.foodorderingapp.java.entity.Product;
import com.foodorderingapp.java.entity.Store;
import com.foodorderingapp.java.entity.User;
import com.foodorderingapp.java.exception.ProductNotFoundException;
import com.foodorderingapp.java.exception.StoreNotFoundException;
import com.foodorderingapp.java.exception.UserNotFoundException;
import com.foodorderingapp.java.repo.OrderRepo;
import com.foodorderingapp.java.repo.ProductRepo;
import com.foodorderingapp.java.repo.StoreRepo;
import com.foodorderingapp.java.repo.UserRepo;

@ExtendWith(MockitoExtension.class)
public class OrderServiceImplTest {
	
	@Mock
	OrderRepo orderRepo;
	
	@Mock
	StoreRepo storeRepo;
	
	@Mock
	ProductRepo productRepo;
	
	@Mock
	UserRepo userRepo;
	
	@InjectMocks
	OrderServiceImpl orderServiceImpl;
	
	OrderRequestDTO orderRequestDTO;
	List<OrderProduct> productList;
	OrderProduct orderProduct;
	User user;
	Store store;
	Product product;
	
	@BeforeEach
	public void setUp() {
		orderRequestDTO = new OrderRequestDTO();
		productList = new ArrayList<>();
		orderProduct = new OrderProduct();
		user = new User();
		store = new Store();
		product = new Product();
		
		orderProduct.setProductId(1);
		orderProduct.setProductPrice(500);
		orderProduct.setQuantity(2);
		
		productList.add(orderProduct);
		
		orderRequestDTO.setUserId(1);
		orderRequestDTO.setStoreId(2);
		orderRequestDTO.setProductList(productList);
		orderRequestDTO.setTotalPrice(1000);
		orderRequestDTO.setInstruction("Handle with care");
		
		user.setUserId(1);
		store.setStoreId(2);
	}
	
	@Test
	@DisplayName("Save order details: positive")
	public void saveOrderDetailTest() {
		when(userRepo.findById(1)).thenReturn(Optional.of(user));
		
		when(storeRepo.findById(2)).thenReturn(Optional.of(store));
		
		productList.forEach(x -> {
			when(productRepo.findById(x.getProductId())).thenReturn(Optional.of(product));
		});
		
		when(orderRepo.save(any(OrderDetail.class))).then(i -> {
			OrderDetail order = i.getArgument(0);
			order.setOrderDetailId(1);
			return order;
		});
		
		OrderDetail orderResult = orderServiceImpl.saveOrderDetail(orderRequestDTO);
		assertNotNull(orderResult);
		assertEquals(1, orderResult.getOrderDetailId());
		assertEquals(1, orderResult.getUserId());
	}
	
	@Test
	@DisplayName("Save order details: negative")
	public void saveOrderDetailTest1() {
		when(userRepo.findById(1)).thenReturn(Optional.empty());
		
		assertThrows(UserNotFoundException.class, () -> orderServiceImpl.saveOrderDetail(orderRequestDTO));
	}

	@Test
	@DisplayName("Save order details: negative")
	public void saveOrderDetailTest2() {
		when(userRepo.findById(1)).thenReturn(Optional.of(user));
		
		when(storeRepo.findById(2)).thenReturn(Optional.empty());
		
		assertThrows(StoreNotFoundException.class, () -> orderServiceImpl.saveOrderDetail(orderRequestDTO));
	}
	
	@Test
	@DisplayName("Save order details: negative")
	public void saveOrderDetailTest3() {
		when(userRepo.findById(1)).thenReturn(Optional.of(user));
		
		when(storeRepo.findById(2)).thenReturn(Optional.of(store));
		
		productList.forEach(x -> {
			when(productRepo.findById(x.getProductId())).thenReturn(Optional.empty());
		});
		
		assertThrows(ProductNotFoundException.class, () -> orderServiceImpl.saveOrderDetail(orderRequestDTO));
	}
}
