package com.kosa.springcoffee.repository;

import com.kosa.springcoffee.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
