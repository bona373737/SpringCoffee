package com.kosa.springcoffee.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItemDTO {
    private Long itemNo;
    private String name;
    private String content;
    private String image;
    private int stockQuantity;
    private int price;
    private String category;

}
