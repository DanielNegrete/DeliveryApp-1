package com.foodorderingapp.java.service;

import com.foodorderingapp.java.dto.OrderRequestDTO;
import com.foodorderingapp.java.dto.OrderResponseDTO;

public interface OrderService {

	OrderResponseDTO getAllOrderDetails(Integer userId, Integer pageNo, Integer pageSize);

	void saveOrderDetail(OrderRequestDTO orderRequestDTO);

}
