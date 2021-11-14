package com.kosa.springcoffee.service;

import com.kosa.springcoffee.dto.OrderDTO;
import com.kosa.springcoffee.entity.*;
import com.kosa.springcoffee.repository.ItemRepository;
import com.kosa.springcoffee.repository.MemberRepository;
import com.kosa.springcoffee.repository.OrderRepository;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
public class OrderServiceTest {

    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    MemberRepository memberRepository;

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
    @DisplayName("주문 테스트")
    public void order() {
        Item item = saveItem();
        Member member = saveMember();

        OrderDTO orderDto = new OrderDTO();
        orderDto.setCount(10);
        orderDto.setItemNo(item.getItemNo());
        Long orderId = orderService.create(orderDto, member.getEmail());

        Order order = orderRepository.findById(orderId).orElseThrow(EntityNotFoundException::new);

        List<OrderItem> orderItems = order.getOrderItems();

        int totalPrice = orderDto.getCount() * item.getPrice();

        assertEquals(totalPrice, order.getTotalPrice());
    }

    @Test
    @DisplayName("주문 취소 테스트")
    public void cancelOrder() {

        Item item = saveItem();
        Member member = saveMember();

        OrderDTO orderDto = new OrderDTO();
        orderDto.setCount(10);
        orderDto.setItemNo(item.getItemNo());

        // 주문 객체 저장
        Long orderId = orderService.create(orderDto, member.getEmail());
        assertEquals(90, item.getStockQuantity());
        // 주문된 객체를 조회한 뒤에 주문 취소
        Order order = orderRepository.findByOrderNo(orderId);
        orderService.cancelOrder(orderId);

        // 주문의 상태가 "CANCEL" 이고 처음 수량 100이 맞다면 테스트 통과
        assertEquals(OrderStatus.CANCEL, order.getStatus());
        assertEquals(100, item.getStockQuantity());

    }

}
