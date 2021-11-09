package com.kosa.springcoffee.repository;

import com.kosa.springcoffee.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByMemberEmail(String MemberEmail);
}
