package com.kosa.springcoffee.service;

import com.kosa.springcoffee.dto.ItemImgDTO;
import com.kosa.springcoffee.dto.ItemImgResponseDTO;
import com.kosa.springcoffee.dto.ItemResponseDTO;
import com.kosa.springcoffee.entity.Item;
import com.kosa.springcoffee.entity.ItemImg;
import com.kosa.springcoffee.repository.ItemImgRepository;
import com.kosa.springcoffee.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemImgService {
    private final ItemImgRepository itemImgRepository;

    @Transactional
    public ItemImgDTO findByFileNo(Long imgNo){
        ItemImg entity = itemImgRepository.findByItemImgNo(imgNo);

        ItemImgDTO dto = ItemImgDTO.builder()
                .origFileName(entity.getOrigFileName())
                .filePath(entity.getFilePath())
                .fileSize(entity.getFileSize())
                .build();


        return dto;
    }


    @Transactional
    public List<ItemImgResponseDTO> findAllByItem(Long itemNo){

        List<ItemImg> itemImgList = itemImgRepository.findAllByItemNo(itemNo);

        return itemImgList.stream().map(ItemImgResponseDTO::new).collect(Collectors.toList());
    }



}
