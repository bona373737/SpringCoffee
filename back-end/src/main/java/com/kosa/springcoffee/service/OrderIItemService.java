package com.kosa.springcoffee.service;

import com.kosa.springcoffee.dto.BoardDTO;
import com.kosa.springcoffee.dto.OrderItemDTO;
import com.kosa.springcoffee.dto.PageRequestDTO;
import com.kosa.springcoffee.dto.PageResultDTO;
import com.kosa.springcoffee.entity.Board;
import com.kosa.springcoffee.entity.Item;
import com.kosa.springcoffee.entity.Order;
import com.kosa.springcoffee.entity.OrderItem;

public interface OrderIItemService {
    Long create(OrderItemDTO dto);
    void modify(OrderItemDTO dto);
    void remove(Long orderItemNo);

    PageResultDTO<OrderItemDTO, OrderItem> readAll(PageRequestDTO requestDTO);


    default OrderItem dtoToEntityOrderItem(OrderItemDTO dto){
        OrderItem entity = OrderItem.builder()
                .orderItemNo(dto.getOrderItemNo())
                .item(Item.builder().itemNo(dto.getItemNo()).build())
                .order(Order.builder().orderNo(dto.getOrderNo()).build())
                .allPrice(dto.getAllPrice())
                .count(dto.getCount())
                .build();
        return entity;
    }

    default OrderItemDTO entityToDtoOrderItem(OrderItem entity){
        OrderItemDTO dto = OrderItemDTO.builder()
                .orderItemNo(entity.getOrderItemNo())
                .itemNo(entity.getItem().getItemNo())
                .orderNo(entity.getOrder().getOrderNo())
                .allPrice(entity.getAllPrice())
                .count(entity.getCount())
                .build();
        return dto;
    }


}
