package com.kosa.springcoffee.entity;

import com.kosa.springcoffee.dto.ItemFormDTO;
import com.kosa.springcoffee.exception.NoStockException;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "sc_item")
@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long itemNo;

    private String name;

    private String content;


    private int stockQuantity;

    private int price;

    private String category;

    public void changeName(String name) {
        this.name = name;
    }

    public void changeContent(String content) {
        this.content = content;
    }

    public void changeStockQuantity(int stockQuantity){
        this.stockQuantity=stockQuantity;
    }
    public void changePrice(int price){
        this.price = price;
    }
    public void changeCategory(String category){
        this.category=category;
    }

    public void updateItem(ItemFormDTO itemFormDTO) {
        this.name = itemFormDTO.getName();
        this.content = itemFormDTO.getContent();
        this.stockQuantity = itemFormDTO.getStockQuantity();
        this.price = itemFormDTO.getPrice();
        this.category = itemFormDTO.getCategory();
    }

    public void removeStock(int quantity) {
        int restStock = this.stockQuantity - quantity;
        if (restStock < 0) {
            throw new NoStockException("상품 재고가 부족합니다.");
        }
        this.stockQuantity = restStock;
    }

}