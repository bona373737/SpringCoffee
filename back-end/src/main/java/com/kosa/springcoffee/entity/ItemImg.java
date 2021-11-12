package com.kosa.springcoffee.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "sc_item_img")
@Getter @Setter
public class ItemImg {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long itemImgNo;
    private String imgName;
    private String originName;
    private String imgUrl;
    private String repImg;

    @ManyToOne(fetch = FetchType.LAZY)
    private Item item;

    public void updateItemImg(String originName, String imgName, String imgUrl){
        this.originName = originName;
        this.imgName = imgName;
        this.imgUrl = imgUrl;
    }
}
