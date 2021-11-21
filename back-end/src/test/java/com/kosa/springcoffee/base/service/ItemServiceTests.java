package com.kosa.springcoffee.service;


import com.kosa.springcoffee.item.itemimg.ItemImgRepository;
import com.kosa.springcoffee.item.ItemRepository;
import com.kosa.springcoffee.item.ItemService;
import com.kosa.springcoffee.item.ItemServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
@Transactional
public class ItemServiceTests {
    @Autowired
    ItemService itemService;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    ItemImgRepository itemImgRepository;

    @Autowired
    ItemServiceImpl itemServiceImpl;

    List<MultipartFile> createMultipartFiles() throws Exception {

        List<MultipartFile> multipartFileList = new ArrayList<>();

        for(int i=0;i<5;i++){
            String path = "C:/ion/item";
            String imageName = "image" + i + ".jpg";
            MockMultipartFile multipartFile = new MockMultipartFile(path, imageName,
                    "image/jpg", new byte[]{1,2,3,4});
            multipartFileList.add(multipartFile);
        }

        return multipartFileList;
    }

    @Test
    @DisplayName("상품 등록 테스트")
    public void saveItem() throws Exception {

//        ItemFormDTO itemFormDto = new ItemFormDTO();
//        itemFormDto.setName("테스트 상품");
//        itemFormDto.setContent("테스트 설명");
//        itemFormDto.setStockQuantity(100);
//        itemFormDto.setPrice(1000);
//        itemFormDto.setCategory("coffee");
//
//        List<MultipartFile> multipartFileList = createMultipartFiles();
//        System.out.println(multipartFileList.get(0));
//        Long itemId = itemServiceImpl.createWithImg(itemFormDto, multipartFileList);
//        List<ItemImg> itemImgList = itemImgRepository.findByItemImgNoOrderByItemImgNoAsc(itemId);
//        System.out.println(itemImgList.size());
//
//        Item item = itemRepository.findByItemNo(itemId);
//
//        assertEquals(itemFormDto.getName(), item.getName());
//        assertEquals(itemFormDto.getContent(), item.getContent());
//        assertEquals(itemFormDto.getPrice(), item.getPrice());
//        assertEquals(itemFormDto.getStockQuantity(), item.getStockQuantity());
//        System.out.println(multipartFileList.get(0).getOriginalFilename());
//        System.out.println(itemImgList.get(0).getOriginName());
//        assertEquals(multipartFileList.get(0).getOriginalFilename(), itemImgList.get(0).getOriginName());
    }


    @Test
    public void a(){
        System.out.println(1);
    }

}
