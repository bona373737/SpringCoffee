package com.kosa.springcoffee.service;

import com.kosa.springcoffee.dto.OrderDTO;
import com.kosa.springcoffee.dto.OrderItemDTO;
import com.kosa.springcoffee.entity.*;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public interface OrderService {
    //주문
    Long create(OrderDTO orderDTO);
    //주문취소
    void orderCancel(Long orderNo);


    default Order dtoToEntity(OrderDTO dto){
        Order entity = Order.builder()
                .orderNo(dto.getOrderNo())
                .member(Member.builder().email(dto.getMember()).build())
                .orderItems(dto.getOrderItems().stream().map(OrderItemDTO::dtoToEntityOrderItem).collect(Collectors.toList()))
                .status(OrderStatus.valueOf(dto.getStatus()))
                .build();

        return entity;
    }

    default OrderDTO entityToDto(Order entity){
        OrderDTO dto = OrderDTO.builder()
                .orderNo(entity.getOrderNo())
                .member(entity.getMember().getEmail())
                .orderItems(entity.getOrderItems().stream().map(OrderItemDTO::entityToDtoOrderItem).collect(Collectors.toList()))
                .status(entity.getStatus().toString())
                .build();

        return dto;
    }



}
