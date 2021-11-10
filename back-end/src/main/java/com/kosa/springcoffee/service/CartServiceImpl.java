package com.kosa.springcoffee.service;

import com.kosa.springcoffee.dto.*;
import com.kosa.springcoffee.entity.Cart;
import com.kosa.springcoffee.entity.CartItem;
import com.kosa.springcoffee.entity.Item;
import com.kosa.springcoffee.entity.Member;
import com.kosa.springcoffee.repository.CartItemRepository;
import com.kosa.springcoffee.repository.CartRepository;
import com.kosa.springcoffee.repository.ItemRepository;
import com.kosa.springcoffee.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService{

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;
    private final OrderService orderService;


    @Override
    public Long create(CartItemDTO cartItemDTO, String email) {
        Optional<Member> member = memberRepository.findByEmailOptional(email);
        Cart cart = cartRepository.findByMemberEmail(member.get().getName());
        if (cart == null){
            cart = Cart.createCart(member.get());
            cartRepository.save(cart);
        }

        Item item = itemRepository.findById(cartItemDTO.getItemNo()).orElseThrow(EntityNotFoundException::new);
        CartItem cartItem = cartItemRepository.findByCartNoAndItemNo(cart.getCartNo(), item.getItemNo());

        if (cartItem == null){
            cartItem = CartItem.createCartItem(cart, item, cartItemDTO.getCount());
            cartItemRepository.save(cartItem);
        }
        else{
            cartItem.addCount(cartItemDTO.getCount());
        }
        return cartItem.getCartItemNo();
    }

    @Override
    public List<CartDetailDTO> getCartList(String email) {
        List<CartDetailDTO> cartDetailDTOList =new ArrayList<>();

        Optional<Member> member = memberRepository.findByEmailOptional(email);
        Cart cart = cartRepository.findByMemberEmail(member.get().getEmail());
        if(cart == null)
            return cartDetailDTOList;

        cartDetailDTOList = cartItemRepository.findCartDetialDtoList(cart.getCartNo());
        return cartDetailDTOList;
    }

    @Override
    public boolean validateCartItem(Long cartItemNo, String email) {
        Optional<Member> curMember = memberRepository.findByEmailOptional(email);
        CartItem cartItem = cartItemRepository.findByCartItemNo(cartItemNo);
        Member savedMember = cartItem.getCart().getMember();

        if (!StringUtils.equals(curMember.get().getEmail(), savedMember.getEmail())){
            return true;
        }
        return false;
    }

    @Override
    public void changeCartItemCount(Long cartItemNo, int count) {
        CartItem cartItem = cartItemRepository.findByCartItemNo(cartItemNo);
        cartItem.changeCount(count);
        cartItemRepository.save(cartItem);
    }

    @Override
    public void deleteCartItem(Long cartItemNo) {
        CartItem cartItem = cartItemRepository.findByCartItemNo(cartItemNo);
        cartItemRepository.delete(cartItem);
    }

    @Override
    public Long orderCartItem(List<CartOrderDTO> cartOrderDtoList, String email) {
        List<OrderDTO> orderDTOList = new ArrayList<>();

        for (CartOrderDTO cartOrderDTO : cartOrderDtoList){
            CartItem cartItem = cartItemRepository.findByCartItemNo(cartOrderDTO.getCartItemNo());
            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setItemNo(cartItem.getItem().getItemNo());
            orderDTO.setCount(cartItem.getCount());
            orderDTOList.add(orderDTO);
        }

        Long orderNo = orderService.cartOrder(orderDTOList, email);

        for (CartOrderDTO cartOrderDTO : cartOrderDtoList){
            CartItem cartItem = cartItemRepository.findByCartItemNo(cartOrderDTO.getCartItemNo());
            cartItemRepository.delete(cartItem);
        }
        return orderNo;
    }
}
