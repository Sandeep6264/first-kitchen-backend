package com.nt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nt.entity.OrderItemEntity;

@Repository("orderItemRepository")
public interface IOrderItemRepository extends JpaRepository<OrderItemEntity,Long>  {

}
