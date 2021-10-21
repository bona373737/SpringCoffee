package com.kosa.springcoffee.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "item")
@Getter
@Setter
public class Item {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="item_id")
    private Long id;

    private String name;
    private int price;
    private int quantity;
    private int soldOut;
    private String category;
}
