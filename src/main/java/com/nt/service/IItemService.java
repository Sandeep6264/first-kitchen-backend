package com.nt.service;

import java.util.List;

import com.nt.requestDTO.ItemIdRequestDTO;
import com.nt.requestDTO.ItemRequestDTO;
import com.nt.responseDTO.ItemResponseDTO;

public interface IItemService {
		public ItemResponseDTO addItem(ItemRequestDTO itemRequestDTO);
		public ItemResponseDTO updateItem(ItemRequestDTO itemRequestDTO);
		public List<ItemResponseDTO> getAllItem();
		public String deleteItem(ItemIdRequestDTO itemRequestDTO);
		public ItemResponseDTO updateItemById(ItemRequestDTO itemRequestDTO);
}
