package com.kosa.springcoffee.dto;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderDTO {
    @NotNull
    private Long itemNo;

    private int count;

}
