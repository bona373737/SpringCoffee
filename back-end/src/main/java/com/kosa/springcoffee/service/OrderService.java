package com.kosa.springcoffee.service;

import com.kosa.springcoffee.dto.OrderDTO;
import com.kosa.springcoffee.dto.OrderHistDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {
    //주문
    Long create(OrderDTO orderDTO, String email);
    //주문취소
//    void orderCancel(Long orderNo);
    Page<OrderHistDTO> getOrderList(String email, Pageable pageable);
    boolean validateOrder(Long orderNo, String email);

    void cancelOrder(Long orderNo);
    Long cartOrder(List<OrderDTO> orderDTOList, String email);


}
