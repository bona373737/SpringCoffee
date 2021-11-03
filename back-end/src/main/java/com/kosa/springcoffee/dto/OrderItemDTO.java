package com.kosa.springcoffee.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderItemDTO {
    private Long orderItemNo;
    private String item;
    private String order;
    private int allPrice;
    private int count;
}
