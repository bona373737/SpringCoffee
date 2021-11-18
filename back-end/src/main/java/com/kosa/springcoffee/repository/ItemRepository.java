package com.kosa.springcoffee.repository;

import com.kosa.springcoffee.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
    Item findByItemNo(Long itemNo);
    Page<Item> findByCategory(String category, Pageable pageable);


    Item findByName(String name);
}