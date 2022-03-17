package com.foodorderingapp.java.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.foodorderingapp.java.dto.ResponseDTO;
import com.foodorderingapp.java.dto.StoreRequestDTO;
import com.foodorderingapp.java.dto.StoreResponseDTO;
import com.foodorderingapp.java.entity.Address;
import com.foodorderingapp.java.entity.Store;
import com.foodorderingapp.java.repo.StoreRepo;
import com.foodorderingapp.java.service.StoreService;
import com.foodorderingapp.java.service.dto.StoreDetails;

@Service
public class StoreServiceImpl implements StoreService{
	
	@Autowired
	StoreRepo storeRepo;
	
	@Override
	public StoreResponseDTO getAllStoreDetails(Integer pageNo, Integer pageSize) {
		Pageable paging = PageRequest.of(pageNo, pageSize);
		
		Page<Store> storePage = storeRepo.findAll(paging);
		
		List<Store> storeList = storePage.getContent();
		
		List<StoreDetails> storeDetailsList = storeList.stream().map(store -> {
			StoreDetails storeDetails = new StoreDetails();
			BeanUtils.copyProperties(store, storeDetails);
			return storeDetails;
		}).collect(Collectors.toList());
		
		StoreResponseDTO storeResponseDTO = new StoreResponseDTO("Store Details Fetch Success", 200);
		storeResponseDTO.setStoresList(storeDetailsList);
		
		return storeResponseDTO;
	}

	@Override
	public void saveStoreDetails(StoreRequestDTO storeRequestDTO) {
		Store store = new Store();
		BeanUtils.copyProperties(storeRequestDTO, store);
		
		
		storeRequestDTO.getAddress().forEach(address -> {
			Address address2 = new Address();
			address2.setCity(address.getCity());
			address2.setPincode(address.getPincode());
			address2.setStreet(address.getStreet());
			store.setAddress(address2);
		});
		
		storeRepo.save(store);
	}
}
