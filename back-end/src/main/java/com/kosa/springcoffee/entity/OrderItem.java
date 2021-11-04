package com.kosa.springcoffee.entity;

import lombok.*;

import javax.persistence.*;

@Entity(name = "sc_order_item")
@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem{
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

    public static OrderItem createOrderItem(Item item, int orderPrice, int count) {
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setAllPrice(orderPrice);
        orderItem.setCount(count);
        item.removeStock(count);
        return orderItem;
    }

    //비즈니스로직 주문 취소
    public void cancel() {
        getItem().addStock(count);
    }
    //조회 로직 주문상품 전체 가격조회
    public int getTotalPrice() {
        return getAllPrice() * getCount();
    }

    public void changeCount(int count) {
        this.count = count;
    }

}
