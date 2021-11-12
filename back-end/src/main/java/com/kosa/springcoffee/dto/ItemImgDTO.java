package com.kosa.springcoffee.dto;

import com.kosa.springcoffee.entity.ItemImg;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter @Setter
public class ItemImgDTO {
    private Long ItemImgNo;
    private String imgName;
    private String originName;
    private String imgUrl;
    private String repImg;

    private static ModelMapper modelMapper = new ModelMapper();

    public static ItemImgDTO of(ItemImg itemImg){
        return modelMapper.map(itemImg, ItemImgDTO.class);
    }
}
