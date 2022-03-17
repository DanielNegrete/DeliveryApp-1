package com.foodorderingapp.java.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.foodorderingapp.java.entity.Address;

public class UserRequestDTO {
	
	@NotEmpty(message = "Username should not be empty")
	private String username;
	
	@NotEmpty(message = "Password should not be empty")
	private String password;
	
	@NotEmpty(message = "Email should not be empty")
	private String email;
	
	@NotEmpty(message = "Phone number should not be empty")
	private String phoneNo;
	
	@NotNull
	private List<Address> address = new ArrayList<>();
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public List<Address> getAddress() {
		return address;
	}
	public void setAddress(List<Address> address) {
		this.address = address;
	}
}
