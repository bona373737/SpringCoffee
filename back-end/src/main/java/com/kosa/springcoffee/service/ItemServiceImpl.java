package com.kosa.springcoffee.service;

import com.kosa.springcoffee.dto.*;
import com.kosa.springcoffee.entity.Board;
import com.kosa.springcoffee.entity.Item;
import com.kosa.springcoffee.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService{


    private final ItemRepository itemRepository;

    @Override
    public Long create(ItemDTO dto) {
        Item entity = dtoToEntity(dto);
        itemRepository.save(entity);

        return entity.getItemNo();
    }


    @Override
    public void modify(ItemDTO dto) {
        Long itemNo = dto.getItemNo();
        Optional<Item> result = itemRepository.findById(itemNo);

        if(result.isPresent()){
            Item item = result.get();
            item.changeName(dto.getName());
            item.changeContent(dto.getContent());
            item.changeImage(dto.getImage());
            item.changeStockQuantity(dto.getStockQuantity());
            item.changePrice(dto.getPrice());
            item.changeCategory(dto.getCategory());
            itemRepository.save(item);
        }
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
