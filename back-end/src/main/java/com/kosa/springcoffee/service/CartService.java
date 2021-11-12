package com.kosa.springcoffee.service;

import com.kosa.springcoffee.dto.CartDetailDTO;
import com.kosa.springcoffee.dto.CartItemDTO;
import com.kosa.springcoffee.dto.CartListDTO;
import com.kosa.springcoffee.dto.CartOrderDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CartService {
    Long create(CartItemDTO cartItemDTO, String email);
    List<CartDetailDTO> getCartList(String email);
    boolean validateCartItem(Long cartItemNo, String email);
    void changeCartItemCount(Long cartItemNo, int count);
    void deleteCartItem(Long cartItemNo);
    Long orderCartItem(List<CartOrderDTO> cartOrderDtoList, String email);
}