package com.kosa.springcoffee.service;

import com.kosa.springcoffee.dto.*;
import com.kosa.springcoffee.entity.Board;
import com.kosa.springcoffee.entity.Item;
import com.kosa.springcoffee.entity.ItemImg;
import com.kosa.springcoffee.repository.ItemImgRepository;
import com.kosa.springcoffee.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService{


    private final ItemRepository itemRepository;
    private final ItemImgRepository itemImgRepository;
    private final ItemImgService itemImgService;

    @Override
    public Long create(ItemDTO dto) {
        Item entity = dtoToEntity(dto);
        itemRepository.save(entity);

        return entity.getItemNo();
    }

    @Override
    public Long createWithImg(ItemFormDTO itemFormDTO, List<MultipartFile> itemImgFileList) throws Exception{
        Item item = itemFormDTO.dtoToEntity();
        itemRepository.save(item);

        for (int i=0;i<itemImgFileList.size(); i++){
            ItemImg itemImg = new ItemImg();
            itemImg.setItem(item);
            if(i==0){
                itemImg.setRepImg("Y");
            } else {
                itemImg.setRepImg("N");
            }

            itemImgService.saveItemImg(itemImg, itemImgFileList.get(i));


        }
        return item.getItemNo();
    }
    @Override
    public ItemFormDTO getItemDetail(Long itemNo){
        List<ItemImg> itemImgList = itemImgRepository.findByItemImgNoOrderByItemImgNoAsc(itemNo);
        List<ItemImgDTO> itemImgDtoList = new ArrayList<>();

        for (ItemImg itemImg : itemImgList) {
            ItemImgDTO itemImgDto = ItemImgDTO.of(itemImg);
            itemImgDtoList.add(itemImgDto);
        }


        Item item = itemRepository.findByItemNo(itemNo);
        ItemFormDTO itemFormDto = ItemFormDTO.entityToDto(item);
        itemFormDto.setItemImgDTOList(itemImgDtoList);
        return itemFormDto;
    }

    @Override
    public void modify(ItemDTO dto) {
        Long itemNo = dto.getItemNo();
        Optional<Item> result = itemRepository.findById(itemNo);

        if(result.isPresent()){
            Item item = result.get();
            item.changeName(dto.getName());
            item.changeContent(dto.getContent());
            item.changeStockQuantity(dto.getStockQuantity());
            item.changePrice(dto.getPrice());
            item.changeCategory(dto.getCategory());
            itemRepository.save(item);
        }
    }

    @Override
    public Long modifyWithImg(ItemFormDTO itemFormDTO, List<MultipartFile> itemImgFileList) throws Exception {
        Item item = itemRepository.findByItemNo(itemFormDTO.getItemNo());
        item.updateItem(itemFormDTO);

        List<Long> itemImgNo = itemFormDTO.getItemImgNo();

        for (int i=0;i<itemImgFileList.size(); i++){
            itemImgService.modifyItemImg(itemImgNo.get(i), itemImgFileList.get(i));


        }
        return item.getItemNo();
    }


    @Override
    public void remove(Long itemNo) {
        itemRepository.deleteById(itemNo);
    }

    @Override
    public PageResultDTO<ItemDTO, Item> readAll(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("itemNo").descending());

        Page<Item> result = itemRepository.findAll(pageable);

        Function<Item, ItemDTO> fn = (entity -> entityToDto(entity));

        return new PageResultDTO<>(result, fn);
    }

    @Override
    public PageResultDTO<ItemDTO, Item> getCategory(CategoryPageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("itemNo").descending());

        Page<Item> result = itemRepository.findByCategory(requestDTO.getCategory(), pageable);

        Function<Item, ItemDTO> fn = (entity -> entityToDto(entity));

        return new PageResultDTO<>(result, fn);
    }
}