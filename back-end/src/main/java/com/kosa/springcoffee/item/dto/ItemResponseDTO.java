package com.kosa.springcoffee.dto;

import com.kosa.springcoffee.item.Item;
import lombok.*;

import java.util.List;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemResponseDTO {
    private Long itemNo;
    private String name;
    private String content;
    private int stockQuantity;
    private int price;
    private String category;
    private List<Long> fileId;

    public ItemResponseDTO(Item entity, List<Long> fileId) {
        this.itemNo = entity.getItemNo();
        this.name = entity.getName();
        this.content = entity.getContent();
        this.stockQuantity = entity.getStockQuantity();
        this.price = entity.getPrice();
        this.category = entity.getCategory();
        this.fileId = fileId;
    }


}