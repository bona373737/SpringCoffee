package com.kosa.springcoffee.cart.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class CartOrderRequestDTO {
    private List<Long> cartItemNo;
    private String email;
    private String address;
}
