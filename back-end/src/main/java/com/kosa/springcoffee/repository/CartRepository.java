package com.kosa.springcoffee.repository;

import com.kosa.springcoffee.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CartRepository extends JpaRepository<Cart, Long> {

    @Query("select c from sc_cart c where c.member.email = :email")
    Cart findByMemberEmail(@Param("email") String email);

//    Cart findByMemberEmail(String email);
}