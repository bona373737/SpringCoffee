package com.kosa.springcoffee.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderCancelDTO {
    private String email;
    private Long orderNo;
}
