package com.foodorderingapp.java.service;

import com.foodorderingapp.java.dto.OrderRequestDTO;
import com.foodorderingapp.java.dto.OrderResponseDTO;
import com.foodorderingapp.java.entity.OrderDetail;

public interface OrderService {

	OrderResponseDTO getAllOrderDetails(Integer userId, Integer pageNo, Integer pageSize);

	OrderDetail saveOrderDetail(OrderRequestDTO orderRequestDTO);

}
