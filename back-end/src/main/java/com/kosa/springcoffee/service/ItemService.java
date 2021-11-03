package com.kosa.springcoffee.service;

import com.kosa.springcoffee.dto.CategoryPageRequestDTO;
import com.kosa.springcoffee.dto.ItemDTO;
import com.kosa.springcoffee.dto.PageRequestDTO;
import com.kosa.springcoffee.dto.PageResultDTO;
import com.kosa.springcoffee.entity.Item;

public interface ItemService {
    Long create(ItemDTO dto);
    void modify(ItemDTO dto);
    void remove(Long itemNo);
    PageResultDTO<ItemDTO, Item> readAll(PageRequestDTO requestDTO);
    PageResultDTO<ItemDTO, Item> getCategory(CategoryPageRequestDTO requestDTO);

    default Item dtoToEntity(ItemDTO dto){
       Item entity= Item.builder()
               .itemNo(dto.getItemNo())
               .name(dto.getName())
               .content(dto.getContent())
               .image(dto.getImage())
               .stockQuantity(dto.getStockQuantity())
               .price(dto.getPrice())
               .category(dto.getCategory())
               .build();
        return entity;
    }

    default ItemDTO entityToDto(Item entity){
        ItemDTO dto = ItemDTO.builder()
                .itemNo(entity.getItemNo())
                .name(entity.getName())
                .content(entity.getContent())
                .image(entity.getImage())
                .stockQuantity(entity.getStockQuantity())
                .price(entity.getPrice())
                .category(entity.getCategory())
                .build();
        return dto;
    }

}
