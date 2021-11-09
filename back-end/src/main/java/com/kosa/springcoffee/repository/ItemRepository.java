package com.kosa.springcoffee.repository;


import com.kosa.springcoffee.dto.ItemDTO;
import com.kosa.springcoffee.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {
    Optional<Item> findByItemNo(Long ItemNo);
    Page<Item> findByCategory(String category, Pageable pageable);
}
