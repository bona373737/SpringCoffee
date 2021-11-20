package com.kosa.springcoffee.dto;

import com.kosa.springcoffee.entity.OrderItem;
import lombok.*;

@Getter @Setter
public class OrderItemDTO {
    private Long itemNo;
    private int count;
    private int allPrice;
    private byte[] image;


    public OrderItemDTO(OrderItem orderItem) {
        this.itemNo = orderItem.getItem().getItemNo();
        this.count = orderItem.getCount();
        this.allPrice = orderItem.getAllPrice();

    }


}
