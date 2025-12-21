package com.nt.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/firstKitchen")
public class FirstKitchenOperationController {
	
	@GetMapping("/fetchItem")
	public ResponseEntity<?> fetchAllItems(){
		return new ResponseEntity<>("All Items",HttpStatus.OK);
	}
	
	

}
