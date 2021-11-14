package com.kosa.springcoffee.service;


import com.kosa.springcoffee.dto.OrderItemDTO;
import com.kosa.springcoffee.dto.PageRequestDTO;
import com.kosa.springcoffee.dto.PageResultDTO;
import com.kosa.springcoffee.entity.OrderItem;
import com.kosa.springcoffee.repository.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderIItemService{

    OrderItemRepository orderItemRepository;

    @Override
    public Long create(OrderItemDTO dto) {
        OrderItem entity = dtoToEntityOrderItem(dto);
        orderItemRepository.save(entity);
        //entity.getItem().removeStock(dto.getCount());
        return entity.getOrderItemNo();
    }

    @Override
    public void modify(OrderItemDTO dto) {
        Long orderItemNo = dto.getOrderItemNo();
        Optional<OrderItem> result = orderItemRepository.findById(orderItemNo);

        if (result.isPresent()){
            OrderItem orderItem = result.get();
            //orderItem.getItem().addStock(orderItem.getItem().getStockQuantity());
            orderItem.changeCount(dto.getCount());
            //orderItem.getItem().removeStock(dto.getCount());

            orderItemRepository.save(orderItem);
        }
    }

    @Override
    public void remove(Long orderItemNo) {

        Optional<OrderItem> result = orderItemRepository.findById(orderItemNo);

        if (result.isPresent()) {
            OrderItem orderItem = result.get();
            orderItem.getItem().addStock(orderItem.getItem().getStockQuantity());

        }

        orderItemRepository.deleteById(orderItemNo);
    }


    @Override
    public PageResultDTO<OrderItemDTO, OrderItem> readAll(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("orderItemNo").descending());

        Page<OrderItem> result = orderItemRepository.findAll(pageable);

        Function<OrderItem, OrderItemDTO> fn = (entity -> entityToDtoOrderItem(entity));

        return new PageResultDTO<>(result, fn);
    }
}
