package com.kosa.springcoffee.controller;

import com.kosa.springcoffee.dto.*;
import com.kosa.springcoffee.entity.Item;
import com.kosa.springcoffee.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/v2")
@Log4j2
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;


    @PostMapping(value = "/new")
    public ResponseEntity createWithImg(@RequestBody ItemFormDTO itemFormDTO, @RequestParam(name = "itemImgFile")List<MultipartFile> itemImgFileList){
        if (itemImgFileList.get(0).isEmpty() && itemFormDTO.getItemNo() == null) {
            return new ResponseEntity<String>("첫번째 이미지는 필수", HttpStatus.FORBIDDEN);
        }
        try {
            itemService.createWithImg(itemFormDTO, itemImgFileList);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("에러 발생", HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<Long>(itemFormDTO.getItemNo(), HttpStatus.OK);


    }


    @PostMapping("/register")
    public ResponseEntity<Long> create(@RequestBody ItemDTO dto){
        itemService.create(dto);
        log.info("상품 등록");
        Long num = itemService.create(dto);

        return new ResponseEntity<>(num, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{itemNo}", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> remove(@PathVariable("itemNo") Long itemNo){
        log.info(itemNo);
        itemService.remove(itemNo);
        return new ResponseEntity<>("removed", HttpStatus.OK);
    }


    @PostMapping(value = "/{itemNo}")
    public ResponseEntity modifyWithImg(@RequestBody ItemFormDTO itemFormDTO, @RequestParam(name = "itemImgFile")List<MultipartFile> itemImgFileList){
        if (itemImgFileList.get(0).isEmpty() && itemFormDTO.getItemNo() == null){
            return new ResponseEntity<String>("첫번째 이미지는 필수", HttpStatus.FORBIDDEN);
        }
        try {
            itemService.modifyWithImg(itemFormDTO, itemImgFileList);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("에러 발생", HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<String>("modified", HttpStatus.OK);
    }

//    @PutMapping(value = "/{itemNo}", produces = MediaType.TEXT_PLAIN_VALUE)
//    public ResponseEntity<String> modify(@RequestBody ItemDTO itemDTO){
//        log.info(itemDTO);
//        itemService.modify(itemDTO);
//        return new ResponseEntity<>("modified", HttpStatus.OK);
//    }

    @GetMapping("/list")
    public PageResultDTO<ItemDTO, Item> readAll(PageRequestDTO pageRequestDTO) {
        log.info("상품 전체 조회");
        return itemService.readAll(pageRequestDTO);
    }

    @GetMapping("/list/{category}")
    public PageResultDTO<ItemDTO, Item> getCategory(CategoryPageRequestDTO pageRequestDTO, @PathVariable String category) {
        log.info("상품 "+category+" 조회");
        return itemService.getCategory(pageRequestDTO);
    }

    @GetMapping("/{itemNo}")
    public ResponseEntity getItemDetail(@PathVariable("itemNo") Long itemNo){
        ItemFormDTO itemFormDTO = itemService.getItemDetail(itemNo);
        return new ResponseEntity<String>("아이템 상세정보",HttpStatus.OK);
    }
}