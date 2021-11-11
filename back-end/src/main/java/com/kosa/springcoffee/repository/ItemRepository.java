package com.kosa.springcoffee.repository;


import com.kosa.springcoffee.dto.ItemDTO;
import com.kosa.springcoffee.entity.Cart;
import com.kosa.springcoffee.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {
    Item findByItemNo(Long ItemNo);
    Page<Item> findByCategory(String category, Pageable pageable);
}
