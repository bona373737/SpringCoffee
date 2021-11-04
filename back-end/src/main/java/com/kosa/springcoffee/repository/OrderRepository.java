package com.kosa.springcoffee.repository;

import com.kosa.springcoffee.entity.Member;
import com.kosa.springcoffee.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findByOrderNo(Long orderNo);
}
