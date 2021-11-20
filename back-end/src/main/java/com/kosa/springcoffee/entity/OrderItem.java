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
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;

    private int allPrice;

    private int count;

    public static OrderItem createOrderItem(Item item, int count) {
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setAllPrice(item.getPrice());
        orderItem.setCount(count);

        item.removeStock(count);
        return orderItem;
    }




    public void cancel() {
        this.getItem().changeStockQuantity(this.getItem().getStockQuantity() + this.getCount());
    }

    public int getTotalPrice() {
        return getAllPrice() * getCount();
    }

    public void changeCount(int count) {
        this.count = count;
    }

}
