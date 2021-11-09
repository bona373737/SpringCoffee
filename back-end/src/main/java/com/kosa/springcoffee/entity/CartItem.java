package com.kosa.springcoffee.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "sc_cart_item")
@Getter @Setter
public class CartItem {
    @Id @GeneratedValue
    private Long cartItemNo;

    @ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "cart_cartNo")
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "item_itemNo")
    private Item item;

    private int count;

    public static CartItem createCartItem(Cart cart, Item item, int count){
        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setItem(item);
        cartItem.setCount(count);
        return cartItem;
    }
    public void addCount(int count){
        this.count += count;
    }
    public void changeCount(int count){
        this.count= count;
    }
}
