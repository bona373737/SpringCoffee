package com.kosa.springcoffee.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class CartOrderDTO {
    private Long cartItemNo;
    private List<CartOrderDTO> cartOrderDTOList;
}
