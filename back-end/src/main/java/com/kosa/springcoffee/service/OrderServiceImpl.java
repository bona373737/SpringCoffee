package com.kosa.springcoffee.service;

import com.kosa.springcoffee.dto.OrderDTO;
import com.kosa.springcoffee.dto.OrderHistDTO;
import com.kosa.springcoffee.dto.OrderItemDTO;
import com.kosa.springcoffee.entity.*;
import com.kosa.springcoffee.repository.ItemRepository;
import com.kosa.springcoffee.repository.MemberRepository;
import com.kosa.springcoffee.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;


    @Override
    public Long create(OrderDTO orderDTO, String email) {
        Item item = itemRepository.findById(orderDTO.getItemNo()).orElseThrow(EntityNotFoundException::new);
        Member member = memberRepository.getByEmail(email);

        List<OrderItem> orderItemList = new ArrayList<>();


        OrderItem orderItem = OrderItem.createOrderItem(item, orderDTO.getCount());
        orderItemList.add(orderItem);

        Order order = Order.createOrder(member, orderItemList);
        order.setStatus(OrderStatus.ORDER);
        order.setOrderAddress(orderDTO.getOrderAddress());
        orderRepository.save(order);

        return order.getOrderNo();
    }

    @Override
    public Page<OrderHistDTO> getOrderListForAdmin(Pageable pageable) {
        List<Order> orders = orderRepository.findOrdersforAdmin(pageable);
        Long totalCount = orderRepository.countAllOrder();

        return getOrderHistDTOPage(pageable, orders, totalCount);
    }

    private Page<OrderHistDTO> getOrderHistDTOPage(Pageable pageable, List<Order> orders, Long totalCount) {
        List<OrderHistDTO> orderHistDtos = new ArrayList<>();

        for (Order order : orders) {
            OrderHistDTO orderHistDto = new OrderHistDTO(order);
            List<OrderItem> orderItems = order.getOrderItems();
            for (OrderItem orderItem : orderItems) {
                OrderItemDTO orderItemDto = new OrderItemDTO(orderItem);
                orderHistDto.addOrderItemDto(orderItemDto);
            }
            orderHistDtos.add(orderHistDto);
        }
        return new PageImpl<>(orderHistDtos, pageable, totalCount);
    }


    @Override
    public Page<OrderHistDTO> getOrderList(String email, Pageable pageable) {
        List<Order> orders = orderRepository.findOrders(email, pageable);
        Long totalCount = orderRepository.countOrder(email);

        return getOrderHistDTOPage(pageable, orders, totalCount);
    }



    @Override
    @Transactional(readOnly = true)
    public boolean validateOrder(Long orderNo, String email) {
        Order order = orderRepository.findById(orderNo).orElseThrow(EntityNotFoundException::new);

        if (StringUtils.equals(order.getMember().getEmail(), email)) {
            return true;
        }
        return false;
    }

    @Override
    public void cancelOrder(Long orderNo) {
        Order order = orderRepository.findByOrderNo(orderNo);
        order.cancel();
        orderRepository.save(order);
    }

    @Override
    public Long cartOrder(List<OrderDTO> orderDTOList, String email,String address) {
        Member member = memberRepository.getByEmail(email);
        List<OrderItem> orderItemList = new ArrayList<>();
        for(OrderDTO orderDTO : orderDTOList){
            Item item = itemRepository.findByItemNo(orderDTO.getItemNo());
            OrderItem orderItem = OrderItem.createOrderItem(item, orderDTO.getCount());
            orderItemList.add(orderItem);
        }

        Order order = Order.createOrder(member, orderItemList);
        order.setStatus(OrderStatus.ORDER);
        order.setOrderAddress(address);
        orderRepository.save(order);
        return order.getOrderNo();
    }

}