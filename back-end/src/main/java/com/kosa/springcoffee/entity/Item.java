package com.kosa.springcoffee.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "sc_item")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    @Id @GeneratedValue
    private Long itemNo;

    private String name;

    private String content;

    private String image;

    private int stockQuantity;

    private int price;

    private String category;

    public void changeName(String name) {
        this.name = name;
    }

    public void changeContent(String content) {
        this.content = content;
    }

    public void changeImage(String image) {
        this.image=image;
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

    //비즈니스 로직
    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }
    public void removeStock(int quantity) {
        int restStock = this.stockQuantity - quantity;
        if (restStock < 0) {
            throw new RuntimeException("need more stock");
        }
        this.stockQuantity = restStock;
    }

}
