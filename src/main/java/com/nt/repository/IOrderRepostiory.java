package com.nt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nt.entity.OrdersEntity;

@Repository("orderRepository")
public interface IOrderRepostiory extends JpaRepository<OrdersEntity,Long>  {

}
