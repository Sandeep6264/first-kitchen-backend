package com.nt.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.nt.common.CustomUserDetails;
import com.nt.entity.ItemEntity;
import com.nt.entity.OrderItemEntity;
import com.nt.entity.OrdersEntity;
import com.nt.entity.UserEntity;
import com.nt.repository.IItemRepository;
import com.nt.repository.IOrderItemRepository;
import com.nt.repository.IOrderRepostiory;
import com.nt.requestDTO.OrderItemRequestDTO;
import com.nt.requestDTO.OrderRequestDTO;
import com.nt.responseDTO.OrderItemDTO;
import com.nt.responseDTO.OrderResponseDTO;
import com.nt.responseDTO.ReturnOrderResponseDTO;

import jakarta.transaction.Transactional;

@Service("orderService")
public class OrderServiceImpl implements IOrderService {
	@Autowired
	private IOrderRepostiory orderRepository;
	
	@Autowired
	private IOrderItemRepository orderItemRepository;
	
	@Autowired
	private IItemRepository itemRepository;

	@Override
	@Transactional
	public OrderResponseDTO placeOrder(OrderRequestDTO orderRequestDTO) {
		Set<OrderItemRequestDTO> orderItemDTO=orderRequestDTO.getOrderItem();
		List<Long> ids=new ArrayList<>();
		orderItemDTO.forEach( (item)->ids.add(item.getItemId()) );
		
		 List<ItemEntity> totalItemEntity= itemRepository.findAllById(ids);
		 if(orderItemDTO.size()!=totalItemEntity.size())
			 throw new IllegalArgumentException("Failed to place order");
		 
//		ItemEntity itemEntity=new ItemEntity();
		OrdersEntity orderEntity=new OrdersEntity();
		UserEntity userEntity=new UserEntity();
		orderItemDTO.forEach(itemDTO -> {
		    OrderItemEntity item = new OrderItemEntity();
		    BeanUtils.copyProperties(itemDTO, item);
		    item.setPrice(itemDTO.getItemPrice());
		   System.out.println(itemDTO);
		    ItemEntity ref = new ItemEntity();
		    ref.setItemId(itemDTO.getItemId());
		    item.setItem(ref);
		    orderEntity.addItem(item); 
		});
		userEntity.setUid(1L);
		orderEntity.setStatus(orderRequestDTO.getStatus());
		orderEntity.setTotalAmount(orderRequestDTO.getTotalAmount());
		orderEntity.setOrderDate(orderRequestDTO.getOrderDate());
		orderEntity.setUser(userEntity);	
	
		OrdersEntity savedRecord=orderRepository.save(orderEntity);
		OrderResponseDTO ordereResponseDTO=new OrderResponseDTO(savedRecord.getOid(),savedRecord.getOrderDate(),savedRecord.getTotalAmount(),savedRecord.getStatus(),savedRecord.getUser().getUid());
		
		return ordereResponseDTO;
		
	}
	
	@Override
	public List<ReturnOrderResponseDTO> findMyOrders() {
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails user =
		        (CustomUserDetails) auth.getPrincipal();
		Long userId = user.getId();
		List<OrdersEntity> orders = orderRepository.findOrdersWithItemsByUser(userId);

		List<ReturnOrderResponseDTO> response = orders.stream()
		    .map(order -> {
		        ReturnOrderResponseDTO dto = new ReturnOrderResponseDTO();

		        dto.setOrderId(order.getOid());
		        dto.setOrderDate(order.getOrderDate());
		        dto.setStatus(order.getStatus());
		        dto.setTotalAmount(order.getTotalAmount());

		        List<OrderItemDTO> itemDTOs = order.getItems().stream()
		            .map(oi -> {
		                OrderItemDTO i = new OrderItemDTO();
		                i.setItemId(oi.getItem().getItemId());
		                i.setItemName(oi.getItem().getItemName());
		                i.setQty( oi.getQty().intValue());
		                i.setPrice(oi.getPrice());
		                return i;
		            })
		            .toList();

		        dto.setItems(itemDTOs);
		        return dto;
		    })
		    .toList();
		return response;

		
	}

}
