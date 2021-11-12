package com.kosa.springcoffee.service;

import com.kosa.springcoffee.dto.*;
import com.kosa.springcoffee.entity.Item;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ItemService {
    Long create(ItemDTO dto);
    Long createWithImg(ItemFormDTO itemFormDTO, List<MultipartFile> itemImgFileList) throws Exception;
    ItemFormDTO getItemDetail(Long itemNo);
    void modify(ItemDTO dto);
    Long modifyWithImg(ItemFormDTO itemFormDTO, List<MultipartFile> itemImgFileList) throws Exception;
    void remove(Long itemNo);
    PageResultDTO<ItemDTO, Item> readAll(PageRequestDTO requestDTO);
    PageResultDTO<ItemDTO, Item> getCategory(CategoryPageRequestDTO requestDTO);

    default Item dtoToEntity(ItemDTO dto){
        Item entity= Item.builder()
                .itemNo(dto.getItemNo())
                .name(dto.getName())
                .content(dto.getContent())
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
                .stockQuantity(entity.getStockQuantity())
                .price(entity.getPrice())
                .category(entity.getCategory())
                .build();
        return dto;
    }

}