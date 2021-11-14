package com.kosa.springcoffee.repository;

import com.kosa.springcoffee.entity.ItemImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemImgRepository extends JpaRepository<ItemImg, Long> {
    @Query("select ii from sc_item_img ii where ii.item.itemNo = :itemNo order by ii.itemImgNo asc ")
    List<ItemImg> findByItemImgNoOrderByItemImgNoAsc(@Param("itemNo") Long itemNo);

    ItemImg findByItemImgNo(Long itemImgNo);

}