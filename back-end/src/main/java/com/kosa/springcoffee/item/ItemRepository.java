package com.kosa.springcoffee.item;

import com.kosa.springcoffee.item.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    Item findByItemNo(Long itemNo);
    Page<Item> findByCategory(String category, Pageable pageable);

    List<Item> findAllByCategory(String category);
    Item findByName(String name);
}