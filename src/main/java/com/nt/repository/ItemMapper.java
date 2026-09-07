package com.nt.repository;

import java.util.List;
import java.util.Set;

import org.mapstruct.Mapper;

import com.nt.entity.ItemEntity;
import com.nt.request.OrderItemRequest;
import com.nt.response.dto.ItemResponseDTO;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    ItemResponseDTO toDTO(ItemEntity entity);

    List<ItemResponseDTO> toDTOList(List<ItemEntity> entityList);
    
    ItemEntity toSetDTO(OrderItemRequest dto);
    
    Set<ItemEntity> toDTOSet(Set<OrderItemRequest> orderItemDTO);
    
}
