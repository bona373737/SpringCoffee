package com.kosa.springcoffee.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CartListResponseDTO {
    private Long cartItemNo;
    private String itemName;
    private int price;
    private int count;
    private Long fileId;

    public CartListResponseDTO(Long cartItemNo, String itemName, int price, int count, Long fileId) {
        this.cartItemNo = cartItemNo;
        this.itemName = itemName;
        this.price = price;
        this.count = count;
        this.fileId = fileId;
    }
}
