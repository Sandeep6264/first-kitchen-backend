package com.nt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.requestDTO.ItemIdRequestDTO;
import com.nt.requestDTO.ItemRequestDTO;
import com.nt.responseDTO.ItemResponseDTO;
import com.nt.service.IItemService;
import com.nt.util.ResponseUtil;

@RestController
@RequestMapping("/api/item")
public class ItemOperationRestController {
	
	@Autowired
	private IItemService itemService;
	
	@PostMapping("/addItem")
	public ResponseEntity<?> addItem(@RequestBody ItemRequestDTO itemRequestDTO ){
	  ItemResponseDTO itemResponseDTO=  itemService.addItem(itemRequestDTO);
	  System.out.println(itemResponseDTO);
	  return ResponseUtil.success(itemResponseDTO, "Item saved successfully");
	}
	
	@PatchMapping("/updateItem")
	public ResponseEntity<?> updateItem(){
		return null;
	}

	@GetMapping("/getAllItem")
	public ResponseEntity<?> getAllItem(){
		List<ItemResponseDTO> itemReponseDTOList=itemService.getAllItem();
		return ResponseUtil.success(itemReponseDTOList, "Item fetched successfully");
	}
	
	@GetMapping("/getItem/{itemId}")
	public ResponseEntity<?> getItemById(){
		return null;
	}
	
	@DeleteMapping("/deleteItem")
	public  ResponseEntity<?> deleteItem(@RequestBody ItemIdRequestDTO itemId){
		String message=itemService.deleteItem(itemId);
		return ResponseUtil.success(null, message);
	}
	

}