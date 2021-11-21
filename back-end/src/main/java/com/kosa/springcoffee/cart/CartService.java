package com.kosa.springcoffee.cart;

import com.kosa.springcoffee.cart.dto.CartDetailDTO;
import com.kosa.springcoffee.cart.dto.CartItemDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CartService {
    Long create(CartItemDTO cartItemDTO, String email);
    List<CartDetailDTO> getCartList(String email);
    boolean validateCartItem(Long cartItemNo, String email);
    void changeCartItemCount(Long cartItemNo, int count);
    void deleteCartItem(Long cartItemNo);
    Long orderCartItem(List<Long> cartOrderDtoList, String email, String address);
}