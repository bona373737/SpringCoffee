package com.kosa.springcoffee.cart.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CartItemDTO {
    private Long itemNo;
    private int count;
}
