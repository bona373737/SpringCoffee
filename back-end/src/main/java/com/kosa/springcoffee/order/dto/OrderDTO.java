package com.kosa.springcoffee.order.dto;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderDTO {
    @NotNull
    private Long itemNo;
    private int count;
    private String email;
    private String orderAddress;

}
