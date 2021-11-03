package com.kosa.springcoffee.entity;

import lombok.*;

import javax.persistence.*;

@Entity(name = "sc_order_item")
@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {
    @Id @GeneratedValue
    private Long orderItemNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_itemId")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="order_orderNo")
    private Order order;

    private int allPrice;

    private int count;


}
