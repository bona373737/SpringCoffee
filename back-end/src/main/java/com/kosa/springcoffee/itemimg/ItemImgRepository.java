package com.kosa.springcoffee.item.itemimg;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ItemImgRepository extends JpaRepository<ItemImg, Long> {
    @Query("select ii from sc_item_img ii where ii.item.itemNo = :itemNo order by ii.itemImgNo asc ")
    List<ItemImg> findByItemImgNoOrderByItemImgNoAsc(@Param("itemNo") Long itemNo);

    ItemImg findByItemImgNo(Long itemImgNo);

    @Query("select ii from sc_item_img ii where ii.item.itemNo = :itemNo")
    List<ItemImg> findAllByItemNo(@Param("itemNo") Long itemNo);

    @Transactional
    @Modifying
    @Query("delete from sc_item_img ii where ii.item.itemNo = :itemNo")
    void deleteAllByItemNo(@Param("itemNo") Long itemNo);
}