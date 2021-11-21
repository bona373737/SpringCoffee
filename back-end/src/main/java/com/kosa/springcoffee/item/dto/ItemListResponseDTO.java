package com.kosa.springcoffee.dto;

import com.kosa.springcoffee.item.Item;
import lombok.*;

import java.util.List;
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemListResponseDTO {
    private Long itemNo;
    private String name;
    private String content;
    private int stockQuantity;
    private int price;
    private String category;
    private Long thumbnailNo;

    public ItemListResponseDTO(Item entity, List<Long> fileId) {
        this.itemNo = entity.getItemNo();
        this.name = entity.getName();
        this.content = entity.getContent();
        this.stockQuantity = entity.getStockQuantity();
        this.price = entity.getPrice();
        this.category = entity.getCategory();
        if(!entity.getItemImg().isEmpty())
            this.thumbnailNo = entity.getItemImg().get(0).getItemImgNo();
        else
            this.thumbnailNo = 0L;
    }

}
