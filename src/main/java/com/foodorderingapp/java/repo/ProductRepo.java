package com.foodorderingapp.java.repo;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.foodorderingapp.java.entity.Product;
import com.foodorderingapp.java.entity.Store;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer>{
	
	List<Product> findByStore(Store store, Pageable paging);
}
