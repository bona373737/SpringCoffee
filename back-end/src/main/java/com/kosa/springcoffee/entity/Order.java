package com.kosa.springcoffee.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "sc_order")
@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order extends BaseEntityOrder{

    @Id @GeneratedValue
    private Long orderNo;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @OneToMany(mappedBy = "order", cascade = {CascadeType.PERSIST,CascadeType.REMOVE}, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();

    private OrderStatus status;

    private String orderAddress;

    //연관관계 메소드
    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }
    //생성메서드
    public static Order createOrder(Member member, List<OrderItem> orderItemList) {
        Order order = new Order();
        order.setMember(member);
        for (OrderItem orderItem : orderItemList) {
            order.addOrderItem(orderItem);
        }
        return order;
    }

    public void cancel() {

        this.setStatus(OrderStatus.주문취소);
        for (OrderItem orderItem : orderItems) {
            orderItem.cancel();
        }
    }
    public void shipping() {
        this.setStatus(OrderStatus.배송중);
    }
    public void done() {
        this.setStatus(OrderStatus.배송완료);
    }
    public void prepare() {
        this.setStatus(OrderStatus.배송준비중);
    }

    public int getTotalPrice() {
        int totalPrice = 0;
        for (OrderItem orderItem : orderItems) {
            totalPrice += orderItem.getTotalPrice();
        }
        return totalPrice;
    }

}