package com.foodorderingapp.java.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.foodorderingapp.java.dto.OrderRequestDTO;
import com.foodorderingapp.java.dto.OrderResponseDTO;
import com.foodorderingapp.java.entity.OrderDetail;
import com.foodorderingapp.java.entity.OrderProduct;
import com.foodorderingapp.java.entity.OrderStatus;
import com.foodorderingapp.java.entity.Product;
import com.foodorderingapp.java.entity.ProductCategory;
import com.foodorderingapp.java.entity.Store;
import com.foodorderingapp.java.entity.User;
import com.foodorderingapp.java.exception.ProductNotFoundException;
import com.foodorderingapp.java.exception.StoreNotFoundException;
import com.foodorderingapp.java.exception.UserNotFoundException;
import com.foodorderingapp.java.repo.OrderRepo;
import com.foodorderingapp.java.repo.ProductRepo;
import com.foodorderingapp.java.repo.StoreRepo;
import com.foodorderingapp.java.repo.UserRepo;
import com.foodorderingapp.java.service.OrderService;
import com.foodorderingapp.java.service.dto.OrderUserDetails;

@Service
public class OrderServiceImpl implements OrderService{

	double total = 0;
	
	@Autowired
	OrderRepo orderRepo;
	
	@Autowired
	StoreRepo storeRepo;
	
	@Autowired
	ProductRepo productRepo;
	
	@Autowired
	UserRepo userRepo;
	
	@Override
	public OrderResponseDTO getAllOrderDetails(Integer userId, Integer pageNo, Integer pageSize) {
		Pageable paging = PageRequest.of(pageNo, pageSize);
		
		List<OrderDetail> orderList = orderRepo.findAllByUserId(userId, paging);
		
		if(orderList.isEmpty())
			throw new UserNotFoundException("User not found: " + userId);
		
		List<OrderUserDetails> ordertDetailsList = orderList.stream().map(order -> {
				OrderUserDetails orderUserDetails = new OrderUserDetails();
				BeanUtils.copyProperties(order, orderUserDetails);
				
				Store store = storeRepo.findById(order.getStoreId()).get();
				orderUserDetails.setStoreName(store.getStoreName());
				orderUserDetails.setOrderDate(order.getOrderdate());
				orderUserDetails.setProductDetails(order.getOrderProductList());
				return orderUserDetails;
		}).collect(Collectors.toList());

		OrderResponseDTO orderResponseDTO = new OrderResponseDTO("Order List Success", 200);
		orderResponseDTO.setOrderUserDetails(ordertDetailsList);
		
		return orderResponseDTO;
	}

	@Override
	public OrderDetail saveOrderDetail(OrderRequestDTO orderRequestDTO) {
		OrderDetail orderDetail = new OrderDetail();
		BeanUtils.copyProperties(orderRequestDTO, orderDetail);
		List<OrderProduct> productList = orderRequestDTO.getProductList();
		
		Optional<User> optionaUser = userRepo.findById(orderDetail.getUserId());
		
		if(optionaUser.isEmpty())
			throw new UserNotFoundException("User not found " + orderDetail.getUserId());
		
		Optional<Store> optionalStore = storeRepo.findById(orderDetail.getStoreId());
		
		if(optionalStore.isEmpty())
			throw new StoreNotFoundException("Store not found " + orderDetail.getStoreId());
		
		productList.forEach(x -> {
			
			Optional<Product> optionalProduct = productRepo.findById(x.getProductId());
			if(optionalProduct.isEmpty())
				throw new ProductNotFoundException("Product not found " + x.getProductId());
			
			Product product = optionalProduct.get(); 
			x.setProductPrice(product.getProductPrice());
			total += x.getProductPrice() * x.getQuantity();
		});
		
		orderDetail.setTotalPrice(total);
		orderDetail.setOrderProductList(productList);	
		orderDetail.setOrderdate(LocalDate.now());
		orderDetail.setStatus(OrderStatus.PLACED);
		return orderRepo.save(orderDetail);
	}

}
