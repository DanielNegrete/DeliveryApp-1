package com.foodorderingapp.java.repo;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foodorderingapp.java.entity.OrderDetail;

@Repository
public interface OrderRepo extends JpaRepository<OrderDetail, Integer>{
	
	List<OrderDetail> findAllByUserId(Integer userId, Pageable page);
}
