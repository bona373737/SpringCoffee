package com.kosa.springcoffee.dto;

import com.kosa.springcoffee.item.itemimg.ItemImg;
import lombok.Getter;

@Getter
public class ItemImgResponseDTO {
    private Long fileId;

    public ItemImgResponseDTO(ItemImg entity) {
        this.fileId = entity.getItemImgNo();
    }
}
