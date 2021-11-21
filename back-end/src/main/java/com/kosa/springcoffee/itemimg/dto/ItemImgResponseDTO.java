package com.kosa.springcoffee.itemimg.dto;

import com.kosa.springcoffee.itemimg.ItemImg;
import lombok.Getter;

@Getter
public class ItemImgResponseDTO {
    private Long fileId;

    public ItemImgResponseDTO(ItemImg entity) {
        this.fileId = entity.getItemImgNo();
    }
}
