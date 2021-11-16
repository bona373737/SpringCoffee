package com.kosa.springcoffee.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "sc_item_img")
@Getter @Setter
public class ItemImg {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long itemImgNo;
    private String uuid;
    private String fileName;
    private String Path;

    @ManyToOne(fetch = FetchType.LAZY)
    private Item item;


}
