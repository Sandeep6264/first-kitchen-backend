package com.nt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nt.entity.ItemEntity;

@Repository("itemRepository")
public interface IItemRepository extends JpaRepository<ItemEntity, Long> {
	
}
