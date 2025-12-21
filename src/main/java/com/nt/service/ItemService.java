package com.nt.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.entity.ItemEntity;
import com.nt.repository.IItemRepository;
import com.nt.repository.ItemMapper;
import com.nt.requestDTO.ItemIdRequestDTO;
import com.nt.requestDTO.ItemRequestDTO;
import com.nt.responseDTO.ItemResponseDTO;

@Service
public class ItemService implements IItemService {
	@Autowired
	private IItemRepository itemRepository;
	
	 @Autowired
	   private ItemMapper itemMapper;
	
	@Override
	public ItemResponseDTO addItem(ItemRequestDTO itemRequestDTO) {
		if(itemRequestDTO.getId()!=null) {
			 Optional<ItemEntity> itemContainer=itemRepository.findById(itemRequestDTO.getId());
			 if(itemContainer.isPresent()) {
				 throw  new IllegalArgumentException("Item already exists");
		}
		 }
			
			 ItemEntity itemEntity=new ItemEntity();
			 BeanUtils.copyProperties(itemRequestDTO, itemEntity);
			 ItemEntity savedItemEntity=itemRepository.save(itemEntity);
			ItemResponseDTO itemResponseDTO=new ItemResponseDTO();
			BeanUtils.copyProperties(savedItemEntity, itemResponseDTO);
			return itemResponseDTO;
		 
	}

	@Override
	public ItemResponseDTO updateItem(ItemRequestDTO itemRequestDTO) {
		ItemEntity itemEntity=itemRepository.findById(itemRequestDTO.getId()).orElseThrow(()-> new IllegalArgumentException("Item not found"));
		if (itemRequestDTO.getItemName() != null && !itemRequestDTO.getItemName().isBlank()) {
		    itemEntity.setItemName(itemRequestDTO.getItemName());
		}

		if (itemRequestDTO.getItemImg() != null && !itemRequestDTO.getItemImg().isBlank()) {
		    itemEntity.setItemImg(itemRequestDTO.getItemImg());
		}

		if (itemRequestDTO.getDescription() != null && !itemRequestDTO.getDescription().isBlank()) {
		    itemEntity.setDescription(itemRequestDTO.getDescription());
		}

		if (itemRequestDTO.getItemCategory() != null && !itemRequestDTO.getItemCategory().isBlank()) {
		    itemEntity.setItemCategory(itemRequestDTO.getItemCategory());
		}

		if (itemRequestDTO.getItemSubCategory() != null && !itemRequestDTO.getItemSubCategory().isBlank()) {
		    itemEntity.setItemSubCategory(itemRequestDTO.getItemSubCategory());
		}

		if (itemRequestDTO.getItemPrice() != null) {
		    itemEntity.setItemPrice(itemRequestDTO.getItemPrice());
		}

		if (itemRequestDTO.getItemStatus() != null && !itemRequestDTO.getItemStatus().isBlank()) {
		    itemEntity.setItemStatus(itemRequestDTO.getItemStatus());
		}

		if (itemRequestDTO.getIsHalf() != null && !itemRequestDTO.getIsHalf().isBlank()) {
		    itemEntity.setIsHalf(itemRequestDTO.getIsHalf());
		}

		if (itemRequestDTO.getHalfPrice() != null) {
		    itemEntity.setHalfPrice(itemRequestDTO.getHalfPrice());
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
	public String deleteItem(ItemIdRequestDTO itemId) {
		if(itemId.getItemId()==null) {
			throw new IllegalArgumentException("Item not found");
		}
		ItemEntity itemEntity=itemRepository.findById(itemId.getItemId()).orElseThrow(()-> new IllegalArgumentException("Item not found"));
		 itemRepository.deleteById(itemEntity.getItemId());
		 return "Item delete with id :: "+itemEntity.getItemId();
	}

	@Override
	public ItemResponseDTO updateItemById(ItemRequestDTO itemRequestDTO) {
		// TODO Auto-generated method stub
		return null;
	}

}
