package com.kosa.springcoffee.service;

import com.kosa.springcoffee.dto.OrderDTO;
import com.kosa.springcoffee.entity.Member;
import com.kosa.springcoffee.entity.Order;
import com.kosa.springcoffee.entity.OrderItem;
import com.kosa.springcoffee.repository.ItemRepository;
import com.kosa.springcoffee.repository.MemberRepository;
import com.kosa.springcoffee.repository.OrderItemRepository;
import com.kosa.springcoffee.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;


    @Override
    public Long create(OrderDTO orderDTO) {
        Order entity = dtoToEntity(orderDTO);
        List<OrderItem> getListOrderItem = entity.getOrderItems();
        Iterator<OrderItem> iter = getListOrderItem.stream().iterator();
        while(iter.hasNext()){
            orderItemRepository.deleteById(iter.next().getOrderItemNo());
            iter.next().getItem().removeStock(iter.next().getCount());
        }
        return entity.getOrderNo();
    }

    @Override
    public void orderCancel(Long orderNo) {
        Order order = orderRepository.findByOrderNo(orderNo);
        List<OrderItem> getListOrderItem = order.getOrderItems();
        Iterator<OrderItem> iter = getListOrderItem.stream().iterator();
        while(iter.hasNext()) {
            iter.next().getItem().addStock(iter.next().getCount());
        }
        order.cancel();
    }
}
