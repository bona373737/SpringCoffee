package com.kosa.springcoffee.itemimg.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Builder
@Getter @Setter
public class ItemImgDTO {
    private String origFileName;
    private String filePath;
    private Long fileSize;
}
