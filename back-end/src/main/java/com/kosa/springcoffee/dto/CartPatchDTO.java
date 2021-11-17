package com.kosa.springcoffee.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.PathVariable;

@Getter @Setter
public class CartPatchDTO {

    private Long cartItemNo;
    private int count;
    private String email;
}
