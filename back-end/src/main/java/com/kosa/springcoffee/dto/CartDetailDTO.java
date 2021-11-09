package com.kosa.springcoffee.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartDetailDTO {
    private Long cartItemNo;
    private String itemName;
    private int price;
    private int count;
//    private String imgUrl;

    public CartDetailDTO(Long cartItemNo, String itemName, int price, int count) {
        this.cartItemNo = cartItemNo;
        this.itemName = itemName;
        this.price = price;
        this.count = count;
    }
}
