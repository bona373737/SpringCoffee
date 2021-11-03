package com.kosa.springcoffee.controller;

import com.kosa.springcoffee.dto.*;
import com.kosa.springcoffee.entity.Item;
import com.kosa.springcoffee.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v2")
@Log4j2
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

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

    @PutMapping(value = "/{itemNo}", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> modify(@RequestBody ItemDTO itemDTO){
        log.info(itemDTO);
        itemService.modify(itemDTO);
        return new ResponseEntity<>("modified", HttpStatus.OK);
    }

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




}
