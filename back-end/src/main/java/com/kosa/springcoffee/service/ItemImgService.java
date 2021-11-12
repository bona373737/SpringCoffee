package com.kosa.springcoffee.service;

import com.kosa.springcoffee.entity.ItemImg;
import com.kosa.springcoffee.repository.ItemImgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ItemImgService {
    @Value("${itemImgLocation}")
    private String itemImgLocation;

    private final FileService fileService;
    private final ItemImgRepository itemImgRepository;

    public void saveItemImg(ItemImg itemimg, MultipartFile itemImgFile) throws IOException {

        String originName = itemImgFile.getOriginalFilename();
        String imgName = "";
        String imgUrl = "";

        if (!StringUtils.isEmpty(originName)) {
            imgName = fileService.uploadFile(itemImgLocation, originName, itemImgFile.getBytes());
            imgUrl = "/images/item/" + imgName;
        }

        itemimg.updateItemImg(originName, imgName, imgUrl);
        itemImgRepository.save(itemimg);
    }

    public void modifyItemImg(Long itemImgNo, MultipartFile itemImgFile) throws IOException {

        if (!itemImgFile.isEmpty()) {
            ItemImg savedItemImg = itemImgRepository.findByItemImgNo(itemImgNo);

            if (!StringUtils.isEmpty(savedItemImg.getImgName())) {
                fileService.deleteFile(itemImgLocation + "/" + savedItemImg);
            }

            String originName = itemImgFile.getOriginalFilename();
            String imgName = fileService.uploadFile(itemImgLocation, originName, itemImgFile.getBytes());
            String imgUrl = "/images/item/" + imgName;
            savedItemImg.updateItemImg(originName, imgName, imgUrl);

        }
    }
}
