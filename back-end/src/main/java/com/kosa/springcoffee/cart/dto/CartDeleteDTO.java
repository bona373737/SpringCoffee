package com.kosa.springcoffee.cart.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CartDeleteDTO {
    private Long cartItemNo;
    private String email;
}
