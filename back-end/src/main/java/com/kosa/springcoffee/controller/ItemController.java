package com.kosa.springcoffee.controller;

import com.kosa.springcoffee.dto.*;
import com.kosa.springcoffee.entity.Item;
import com.kosa.springcoffee.entity.ItemImg;
import com.kosa.springcoffee.repository.ItemImgRepository;
import com.kosa.springcoffee.repository.ItemRepository;
import com.kosa.springcoffee.service.ItemImgService;
import com.kosa.springcoffee.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v2")
@Log4j2
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;
    private final ItemRepository itemRepository;
    private final ItemImgRepository itemImgRepository;

    @PostMapping(value="/create", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity create(@RequestParam(value="image") List<MultipartFile> files,
                       @RequestParam(value="name") String name,
                       @RequestParam(value="content") String content,
                       @RequestParam(value = "stockQuantity") int stockQuantity,
                       @RequestParam(value="price") int price,
                       @RequestParam(value="category") String category) throws Exception
    {

        ItemDTO requestDTO = ItemDTO.builder()
                .name(name)
                .content(content)
                .stockQuantity(stockQuantity)
                .price(price)
                .category(category)
                .build();

        Long num = itemService.createWithImg(requestDTO, files);
        return new ResponseEntity<Long>(num, HttpStatus.OK);


    }

    @PutMapping("/update/{itemNo}")
    public ResponseEntity update(@PathVariable Long itemNo,
                                 @RequestParam(value="image") List<MultipartFile> files,
                                 @RequestParam(value="name") String name,
                                 @RequestParam(value="content") String content,
                                 @RequestParam(value = "stockQuantity") int stockQuantity,
                                 @RequestParam(value="price") int price,
                                 @RequestParam(value="category") String category) throws Exception{

        ItemDTO dto = ItemDTO.builder()
                .itemNo(itemNo)
                .name(name)
                .content(content)
                .stockQuantity(stockQuantity)
                .price(price)
                .category(category)
                .build();


        List<ItemImg> dbImgList = itemImgRepository.findAllByItemNo(itemNo);

        List<MultipartFile> multipartFileList = files;

        List<MultipartFile> addFileList = new ArrayList<>();

        if (CollectionUtils.isEmpty(dbImgList)){
            if (!CollectionUtils.isEmpty(multipartFileList)){
                for (MultipartFile multipartFile : multipartFileList){
                    addFileList.add(multipartFile);
                }
            }
        }
        else{
            if (CollectionUtils.isEmpty(multipartFileList)){
                for (ItemImg dbImg : dbImgList){
                    itemImgRepository.deleteById(dbImg.getItemImgNo());
                }
            }
            else{
                List<String> dbOriginNameList = new ArrayList<>();

                for (ItemImg dbImg : dbImgList){
                    ItemImg img = itemImgRepository.findByItemImgNo(dbImg.getItemImgNo());
                    String oriName = img.getOrigFileName();

                    if (!multipartFileList.contains(oriName))
                        itemImgRepository.deleteById(dbImg.getItemImgNo());
                    else
                        dbOriginNameList.add(oriName);
                }

                for (MultipartFile multipartFile : multipartFileList){
                    String multipartOriName = multipartFile.getOriginalFilename();
                    if (!dbOriginNameList.contains(multipartOriName))
                        addFileList.add(multipartFile);
                }


            }
        }
        itemService.modifyWithImg(itemNo,dto,addFileList );
        return new ResponseEntity<String>("success", HttpStatus.OK);

    }

//    @PostMapping("/register")
//    public ResponseEntity<Long> create(@RequestBody ItemDTO dto){
//        itemService.create(dto);
//        log.info("상품 등록");
//        Long num = itemService.create(dto);
//
//        return new ResponseEntity<>(num, HttpStatus.OK);
//    }

    @GetMapping("/list")
    public ResponseEntity readAllItem() {
        log.info("상품 전체 조회");
        List<Item> items = itemRepository.findAll();
        List<ItemReadDTO> getItems = itemService.readAllItem();
        if(items.size() != getItems.size())
            return new ResponseEntity<String>("가져오기 실패", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<List<ItemReadDTO>>(getItems,HttpStatus.OK);
    }

    @GetMapping("/list/{category}")
    public ResponseEntity getCategory(@PathVariable String category) {
        log.info("카테고리별 상품 전체 조회");
        return new ResponseEntity(itemService.readAllItemByCategory(category), HttpStatus.OK);
    }



//    @GetMapping("/list/{category}")
//    public PageResultDTO<ItemDTO, Item> getCategory(CategoryPageRequestDTO pageRequestDTO, @PathVariable String category) {
//        log.info("상품 "+category+" 조회");
//        return itemService.getCategory(pageRequestDTO);
//    }

    @GetMapping("/{itemNo}")
    public ResponseEntity getItemDetail(@PathVariable("itemNo") Long itemNo){
        Item item = itemRepository.findByItemNo(itemNo);


        ItemResponseDTO dto = ItemResponseDTO.builder()
                .itemNo(item.getItemNo())
                .name(item.getName())
                .content(item.getContent())
                .stockQuantity(item.getStockQuantity())
                .price(item.getPrice())
                .category(item.getCategory())
                .fileId(item.getItemImg().stream().map(ItemImg::getItemImgNo).collect(Collectors.toList()))
                .build();


        return new ResponseEntity<ItemResponseDTO>(dto,HttpStatus.OK);
    }

    @DeleteMapping(value = "/{itemNo}", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> remove(@PathVariable("itemNo") Long itemNo) throws Exception{
        log.info(itemNo);
        itemService.remove(itemNo);
        return new ResponseEntity<>("removed", HttpStatus.OK);
    }

}