package com.kosa.springcoffee.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CartItemTestDTO {
    private Long itemNo;
    private int count;
    private String email;
}
