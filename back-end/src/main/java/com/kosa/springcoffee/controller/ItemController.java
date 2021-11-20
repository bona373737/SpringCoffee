package com.kosa.springcoffee.controller;

import com.kosa.springcoffee.dto.ItemDTO;
import com.kosa.springcoffee.dto.ItemReadDTO;
import com.kosa.springcoffee.dto.ItemResponseDTO;
import com.kosa.springcoffee.entity.Item;
import com.kosa.springcoffee.entity.ItemImg;
import com.kosa.springcoffee.repository.ItemImgRepository;
import com.kosa.springcoffee.repository.ItemRepository;
import com.kosa.springcoffee.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
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

    @PutMapping(value = "/update/{itemNo}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
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