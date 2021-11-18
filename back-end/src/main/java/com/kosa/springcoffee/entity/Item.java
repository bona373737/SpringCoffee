package com.kosa.springcoffee.entity;

import com.kosa.springcoffee.dto.ItemDTO;
import com.kosa.springcoffee.exception.NoStockException;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "item", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<ItemImg> itemImg = new ArrayList<>();

    @Builder
    public Item(String name, String content, int stockQuantity, int price, String category) {
        this.name = name;
        this.content = content;
        this.stockQuantity = stockQuantity;
        this.price = price;
        this.category = category;
    }

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

    public void updateItem(ItemDTO itemDTO) {
        this.name = itemDTO.getName();
        this.content = itemDTO.getContent();
        this.stockQuantity = itemDTO.getStockQuantity();
        this.price = itemDTO.getPrice();
        this.category = itemDTO.getCategory();
    }

    public void removeStock(int quantity) {
        int restStock = this.stockQuantity - quantity;
        if (restStock < 0) {
            throw new NoStockException("상품 재고가 부족합니다.");
        }
        this.stockQuantity = restStock;
    }
}