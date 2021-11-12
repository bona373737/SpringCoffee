package com.kosa.springcoffee.service;

import com.kosa.springcoffee.dto.CartItemDTO;
import com.kosa.springcoffee.entity.CartItem;
import com.kosa.springcoffee.entity.Item;
import com.kosa.springcoffee.entity.Member;
import com.kosa.springcoffee.repository.CartItemRepository;
import com.kosa.springcoffee.repository.ItemRepository;
import com.kosa.springcoffee.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
@Transactional
public class CartServiceTests {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    public CartService cartService;

    @Autowired
    public CartItemRepository cartItemRepository;

    public Item saveItem(){
        Item item = new Item();
        item.setName("테스트 상품");
        item.setPrice(10000);
        item.setContent("테스트 상품 상세 설명");
        item.setStockQuantity(100);
        item.setCategory("coffee");
        return itemRepository.save(item);
    }

    public Member saveMember(){
        Member member = new Member();
        member.setEmail("test@test.com");
        return memberRepository.save(member);
    }

    @Test
    public void 장바구니담기테스트(){
        Item item =saveItem();
        Member member= saveMember();

        CartItemDTO cartItemDTO = new CartItemDTO();
        cartItemDTO.setItemNo(item.getItemNo());
        cartItemDTO.setCount(5);

        Long cartItemNo = cartService.create(cartItemDTO, member.getEmail());
        CartItem cartItem = cartItemRepository.findByCartItemNo(cartItemNo);

        assertEquals(item.getItemNo(), cartItem.getItem().getItemNo());
        assertEquals(cartItemDTO.getCount(), cartItem.getCount());


    }

}
