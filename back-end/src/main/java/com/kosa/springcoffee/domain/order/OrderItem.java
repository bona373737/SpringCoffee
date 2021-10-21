package com.kosa.springcoffee.domain.order;

import com.kosa.springcoffee.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "order_item")
@Getter
@Setter
public class OrderItem {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Long id;

    // 주문아이디
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Orders order;

    // 상품아이디
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    private int count;
    private int totalPrice;
}
