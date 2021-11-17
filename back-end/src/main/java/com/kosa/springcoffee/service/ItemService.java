package com.kosa.springcoffee.service;

import com.kosa.springcoffee.dto.*;
import com.kosa.springcoffee.entity.Item;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ItemService {
    Long create(ItemDTO dto);
    Long createWithImg(ItemDTO itemDTO, List<MultipartFile> itemImgFileList) throws Exception;
    void modify(ItemDTO dto);
    void modifyWithImg(Long itemNo,ItemDTO itemDTO, List<MultipartFile> itemImgFileList) throws Exception;
    void remove(Long itemNo);
    PageResultDTO<ItemDTO, Item> readAll(PageRequestDTO requestDTO);
    List<ItemReadDTO> readAllItem();
    PageResultDTO<ItemDTO, Item> getCategory(CategoryPageRequestDTO requestDTO);
    ItemResponseDTO searchById(Long id, List<Long> fileId);
    List<Item> searchAll();
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