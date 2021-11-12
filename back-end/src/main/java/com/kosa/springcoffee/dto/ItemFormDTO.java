package com.kosa.springcoffee.dto;

import com.kosa.springcoffee.entity.Item;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class ItemFormDTO {
    private Long itemNo;
    private String name;
    private String content;
    private int stockQuantity;
    private int price;
    private String category;

    private List<ItemImgDTO> itemImgDTOList = new ArrayList<>();
    private List<Long> itemImgNo = new ArrayList<>();

    private static ModelMapper modelMapper = new ModelMapper();

    public Item dtoToEntity(){
        return modelMapper.map(this, Item.class);
    }


    public static ItemFormDTO entityToDto(Item item){
        return modelMapper.map(item,ItemFormDTO.class);
    }


}