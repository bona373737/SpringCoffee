package com.kosa.springcoffee.itemimg;

import com.kosa.springcoffee.item.Item;
import lombok.*;

import javax.persistence.*;

@Entity(name = "sc_item_img")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter @Setter
public class ItemImg {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long itemImgNo;
    @Column(nullable = false)
    private String origFileName;  // 파일 원본명

    @Column(nullable = false)
    private String filePath;  // 파일 저장 경로

    private Long fileSize;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Item item;


    @Builder
    public ItemImg(String origFileName, String filePath, Long fileSize) {
        this.origFileName = origFileName;
        this.filePath = filePath;
        this.fileSize = fileSize;
    }

    public void setItem(Item item){
        this.item = item;

        if(!item.getItemImg().contains(this)){
            item.getItemImg().add(this);
        }
    }
}
