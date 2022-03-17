package com.foodorderingapp.java.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.foodorderingapp.java.dto.OrderRequestDTO;
import com.foodorderingapp.java.entity.OrderProduct;
import com.foodorderingapp.java.service.OrderService;

@WebMvcTest(OrderController.class)
public class OrderControllerTest {
	
	@MockBean
	OrderService orderService;
	
	@Autowired
	private MockMvc mockMvc;
	
	OrderRequestDTO orderRequestDTO;
	List<OrderProduct> productList;
	OrderProduct orderProduct;
	
	@BeforeEach
	public void setUp() {
		orderRequestDTO = new OrderRequestDTO();
		productList = new ArrayList<>();
		orderProduct = new OrderProduct();
		
		orderProduct.setProductId(1);
		orderProduct.setProductPrice(500);
		orderProduct.setQuantity(2);
		
		productList.add(orderProduct);
		
		orderRequestDTO.setUserId(1);
		orderRequestDTO.setStoreId(2);
		orderRequestDTO.setProductList(productList);
		orderRequestDTO.setTotalPrice(1000);
		orderRequestDTO.setInstruction("Handle with care");
	}
	
	@Test
	public void saveOrderDetailTest() throws Exception {
		mockMvc.perform(post("/orderdetails")
				.content(asJsonString(orderRequestDTO))
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isAccepted())
		.andExpect(jsonPath("$.message").value("Order Saved Success"))
		.andExpect(jsonPath("$.statusCode").value(200));
	}

	public String asJsonString(OrderRequestDTO orderRequestDTO2) {
		try {
			return new ObjectMapper().writeValueAsString(orderRequestDTO);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}
}
