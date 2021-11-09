package com.kosa.springcoffee.entity;

import lombok.*;

import javax.persistence.*;

@Entity(name = "sc_cart")
@Getter
@Setter
@ToString
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cartNo;

    @OneToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "sc_member_email")
    private Member member;

    public static Cart createCart(Member member){
        Cart cart = new Cart();
        cart.setMember(member);
        return cart;
    }
}
