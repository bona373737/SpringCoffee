package com.kosa.springcoffee.dto;

import com.kosa.springcoffee.entity.Item;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemReadDTO {
    private Long itemNo;
    private String name;
    private String content;
    private int stockQuantity;
    private int price;
    private String category;
    private Long fileId;

    public ItemReadDTO(Item entity,Long fileId) {
        this.itemNo = entity.getItemNo();
        this.name = entity.getName();
        this.content = entity.getContent();
        this.stockQuantity = entity.getStockQuantity();
        this.price = entity.getPrice();
        this.category = entity.getCategory();
        this.fileId = fileId;
    }
}
