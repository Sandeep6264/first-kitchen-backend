package com.nt.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.entity.ItemEntity;
import com.nt.repository.IItemRepository;
import com.nt.repository.ItemMapper;
import com.nt.request.ItemIdRequest;
import com.nt.request.ItemRequest;
import com.nt.response.dto.ItemResponseDTO;

@Service
public class ItemService implements IItemService {
	@Autowired
	private IItemRepository itemRepository;
	
	 @Autowired
	   private ItemMapper itemMapper;
	
	@Override
	public ItemResponseDTO addItem(ItemRequest itemRequest) {
		if(itemRequest.getId()!=null) {
			 Optional<ItemEntity> itemContainer=itemRepository.findById(itemRequest.getId());
			 if(itemContainer.isPresent()) {
				 throw  new IllegalArgumentException("Item already exists");
		}
		 }
			
			 ItemEntity itemEntity=new ItemEntity();
			 BeanUtils.copyProperties(itemRequest, itemEntity);
			 ItemEntity savedItemEntity=itemRepository.save(itemEntity);
			ItemResponseDTO itemResponseDTO=new ItemResponseDTO();
			BeanUtils.copyProperties(savedItemEntity, itemResponseDTO);
			return itemResponseDTO;
		 
	}

	@Override
	public ItemResponseDTO updateItem(ItemRequest itemRequest) {
		ItemEntity itemEntity=itemRepository.findById(itemRequest.getId()).orElseThrow(()-> new IllegalArgumentException("Item not found"));
		if (itemRequest.getItemName() != null && !itemRequest.getItemName().isBlank()) {
		    itemEntity.setItemName(itemRequest.getItemName());
		}

		if (itemRequest.getItemImg() != null && !itemRequest.getItemImg().isBlank()) {
		    itemEntity.setItemImg(itemRequest.getItemImg());
		}

		if (itemRequest.getDescription() != null && !itemRequest.getDescription().isBlank()) {
		    itemEntity.setDescription(itemRequest.getDescription());
		}

		if (itemRequest.getItemCategory() != null && !itemRequest.getItemCategory().isBlank()) {
		    itemEntity.setItemCategory(itemRequest.getItemCategory());
		}

		if (itemRequest.getItemSubCategory() != null && !itemRequest.getItemSubCategory().isBlank()) {
		    itemEntity.setItemSubCategory(itemRequest.getItemSubCategory());
		}

		if (itemRequest.getItemPrice() != null) {
		    itemEntity.setItemPrice(itemRequest.getItemPrice());
		}

		if (itemRequest.getItemStatus() != null && !itemRequest.getItemStatus().isBlank()) {
		    itemEntity.setItemStatus(itemRequest.getItemStatus());
		}

		if (itemRequest.getIsHalf() != null && !itemRequest.getIsHalf().isBlank()) {
		    itemEntity.setIsHalf(itemRequest.getIsHalf());
		}

		if (itemRequest.getHalfPrice() != null) {
		    itemEntity.setHalfPrice(itemRequest.getHalfPrice());
		}
		ItemEntity saveItemEntity= itemRepository.save(itemEntity);
		ItemResponseDTO itemReponseDTO=new ItemResponseDTO();
		BeanUtils.copyProperties(saveItemEntity, itemReponseDTO);
		return itemReponseDTO;
	}

	@Override
	public List<ItemResponseDTO> getAllItem() {
		List<ItemEntity> itemEntityList=itemRepository.findAll();
		return itemMapper.toDTOList(itemEntityList);
	}

	@Override
	public String deleteItem(ItemIdRequest itemId) {
		if(itemId.getItemId()==null) {
			throw new IllegalArgumentException("Item not found");
		}
		ItemEntity itemEntity=itemRepository.findById(itemId.getItemId()).orElseThrow(()-> new IllegalArgumentException("Item not found"));
		 itemRepository.deleteById(itemEntity.getItemId());
		 return "Item delete with id :: "+itemEntity.getItemId();
	}

	@Override
	public ItemResponseDTO updateItemById(ItemRequest itemRequest) {
		// TODO Auto-generated method stub
		return null;
	}

}
