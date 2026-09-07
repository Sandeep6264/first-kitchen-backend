package com.nt.service;

import java.util.List;

import com.nt.request.ItemIdRequest;
import com.nt.request.ItemRequest;
import com.nt.response.dto.ItemResponseDTO;

public interface IItemService {
		public ItemResponseDTO addItem(ItemRequest itemRequest);
		public ItemResponseDTO updateItem(ItemRequest itemRequest);
		public List<ItemResponseDTO> getAllItem();
		public String deleteItem(ItemIdRequest itemRequestDTO);
		public ItemResponseDTO updateItemById(ItemRequest itemRequest);
}
