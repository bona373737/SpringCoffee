package com.kosa.springcoffee.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItemDTO2 {
    private Long itemNo;
    private String name;
    private String content;
    private int stockQuantity;
    private int price;
    private String category;


    @Builder.Default
    private List<ItemImgDTO2> imgDTOList = new ArrayList<>();

}