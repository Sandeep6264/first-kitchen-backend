package com.nt.repository;

import java.util.List;
import java.util.Set;

import org.mapstruct.Mapper;

import com.nt.entity.ItemEntity;
import com.nt.requestDTO.OrderItemRequestDTO;
import com.nt.responseDTO.ItemResponseDTO;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    ItemResponseDTO toDTO(ItemEntity entity);

    List<ItemResponseDTO> toDTOList(List<ItemEntity> entityList);
    
    ItemEntity toSetDTO(OrderItemRequestDTO dto);
    
    Set<ItemEntity> toDTOSet(Set<OrderItemRequestDTO> orderItemDTO);
    
}
