package com.nt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nt.entity.OrdersEntity;

@Repository("orderRepository")
public interface IOrderRepostiory extends JpaRepository<OrdersEntity,Long>  {
	@Query("SELECT DISTINCT o FROM OrdersEntity o JOIN FETCH o.items oi JOIN FETCH oi.item WHERE o.user.uid = :userId ORDER BY o.orderDate DESC")
		List<OrdersEntity> findOrdersWithItemsByUser(@Param("userId") Long userId);
		
}
