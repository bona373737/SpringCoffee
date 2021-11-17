package com.kosa.springcoffee.service;

import com.kosa.springcoffee.dto.ItemImgDTO;
import com.kosa.springcoffee.entity.Item;
import com.kosa.springcoffee.entity.ItemImg;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

//multipartfile list를 itemimg list로 변환하기위한 핸들러
@Component
public class FileHandler {
    private final ItemImgService itemImgService;

    public FileHandler(ItemImgService itemImgService) {
        this.itemImgService = itemImgService;
    }

    public List<ItemImg> parseFile(Item item, List<MultipartFile> multipartFiles) throws Exception{
        List<ItemImg> fileList = new ArrayList<>();

        if (!CollectionUtils.isEmpty(multipartFiles)){
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            String curDate = now.format(dateTimeFormatter);

            String absolutePath = new File("").getAbsolutePath() + File.separator + File.separator;

            String path = "images" + File.separator + curDate;
            File file = new File(path);

            if (!file.exists()){
                boolean wasSuccessful = file.mkdirs();
                if (!wasSuccessful) System.out.println("디렉터리 생성 실패");
            }

            for (MultipartFile multipartFile : multipartFiles){
                String originalFileExtension;
                String contentType = multipartFile.getContentType();


                if(ObjectUtils.isEmpty(contentType)) {
                    break;
                }
                else {
                    if(contentType.contains("image/jpeg"))
                        originalFileExtension = ".jpg";
                    else if(contentType.contains("image/png"))
                        originalFileExtension = ".png";
                    else
                        break;
                }
                String new_file_name = System.nanoTime() + originalFileExtension;

                ItemImgDTO dto = ItemImgDTO.builder()
                        .origFileName(multipartFile.getOriginalFilename())
                        .filePath(path +  File.separator + new_file_name)
                        .fileSize(multipartFile.getSize())
                        .build();

                ItemImg itemImg = new ItemImg(dto.getOrigFileName(), dto.getFilePath(), dto.getFileSize());

                if(item.getItemNo() != null)
                    itemImg.setItem(item);


                fileList.add(itemImg);

                file = new File(absolutePath + path + File.separator +new_file_name);
                multipartFile.transferTo(file);
                file.setWritable(true);
                file.setReadable(true);




            }


        }


        return fileList;

    }


}
