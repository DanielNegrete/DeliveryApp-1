package com.foodorderingapp.java.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.foodorderingapp.java.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer>{
	
	List<User> findByUsernameAndPassword(String username, String password);
}
