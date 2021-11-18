package com.kosa.springcoffee.controller;

import com.google.common.io.ByteStreams;
import com.kosa.springcoffee.dto.ItemImgDTO;
import com.kosa.springcoffee.entity.ItemImg;
import com.kosa.springcoffee.repository.ItemImgRepository;
import com.kosa.springcoffee.service.ItemImgService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static com.google.common.io.ByteStreams.toByteArray;

@RestController
@RequestMapping("/v2-2")
@Log4j2
@RequiredArgsConstructor
public class ItemImgController {
    private final ItemImgService itemImgService;
    private final ItemImgRepository itemImgRepository;

    @CrossOrigin
    @GetMapping(value = "/thumbnail/{itemImgNo}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public ResponseEntity getTumbnail(@PathVariable Long itemImgNo) throws IOException{
        String absolutePath = new File("").getAbsolutePath() + File.separator + File.separator;
        String path;

        if(itemImgNo != 0){
            ItemImg img = itemImgRepository.findByItemImgNo(itemImgNo);
            path = img.getFilePath();
        }
        else
            path = "images" + File.separator + "thumbnail" + File.separator + "thumbnail.png";

        InputStream imageStream = new FileInputStream(absolutePath + path);
        byte[] imageByteArray = toByteArray(imageStream);

        imageStream.close();

        return new ResponseEntity<>(imageByteArray, HttpStatus.OK);
    }
    @CrossOrigin
    @GetMapping(
            value = "/image/{itemImgNo}",
            produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE}
    )
    public ResponseEntity<byte[]> getImage(@PathVariable Long itemImgNo) throws IOException{
        ItemImgDTO dto = itemImgService.findByFileNo(itemImgNo);
        String absolutePath
                = new File("").getAbsolutePath() + File.separator + File.separator;
        String path = dto.getFilePath();

        InputStream imageStream = new FileInputStream(absolutePath + path);
        byte[] imageByteArray = toByteArray(imageStream);
        imageStream.close();

        return new ResponseEntity<>(imageByteArray, HttpStatus.OK);
    }
}
