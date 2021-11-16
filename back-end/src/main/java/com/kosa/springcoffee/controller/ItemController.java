package com.kosa.springcoffee.controller;

import com.kosa.springcoffee.dto.*;
import com.kosa.springcoffee.entity.Item;
import com.kosa.springcoffee.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
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
@RestController
@RequestMapping("/v2")
@Log4j2
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;


    @PostMapping(value = "/new")
    public ResponseEntity createWithImg(@RequestBody ItemFormDTO itemFormDTO, @RequestParam(name = "itemImgFile")List<MultipartFile> itemImgFileList){
        if (itemImgFileList.get(0).isEmpty() && itemFormDTO.getItemNo() == null) {
            return new ResponseEntity<String>("첫번째 이미지는 필수", HttpStatus.FORBIDDEN);
        }
        try {
            itemService.createWithImg(itemFormDTO, itemImgFileList);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("에러 발생", HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<Long>(itemFormDTO.getItemNo(), HttpStatus.OK);


    }

    
//    @PostMapping("/register")
//    public ResponseEntity<Long> create(@RequestBody ItemDTO dto){
//        itemService.create(dto);
//        log.info("상품 등록");
//        Long num = itemService.create(dto);
//
//        return new ResponseEntity<>(num, HttpStatus.OK);
//    }

    @GetMapping("/display/{fileName}")
    public ResponseEntity getImg(@PathVariable("fileName") String fileName){
        File file = new File("C:\\upload\\" + fileName);

        ResponseEntity<byte[]> result =null;

        try {
            HttpHeaders header = new HttpHeaders();

            header.add("Content-type", Files.probeContentType(file.toPath()));
            result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
        }
        catch (IOException e){
            e.printStackTrace();
        }

        return result;

    }

    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity register(@RequestParam("file") MultipartFile[] uploadFile){
        for (MultipartFile multipartFile: uploadFile){
            File checkFile = new File(multipartFile.getOriginalFilename());
            String type = null;

            try {
                type = Files.probeContentType(checkFile.toPath());
                log.info("MIME TYPE : " + type);
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (!type.startsWith("image")){
                List<AttachImageDTO> list = null;
                return new ResponseEntity<>(list, HttpStatus.BAD_REQUEST);
            }
        }

        String uploadFolder = "C:\\upload";

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String str = sdf.format(date);
        String datePath = str.replace("-", File.separator);

        File uploadPath = new File(uploadFolder, datePath);

        if (uploadPath.exists() == false){
            uploadPath.mkdirs();
        }

        List<AttachImageDTO> list = new ArrayList<>();

        for (MultipartFile multipartFile : uploadFile){

            AttachImageDTO dto = new AttachImageDTO();

            String uploadFileName = multipartFile.getOriginalFilename();

            dto.setFileName(uploadFileName);
            dto.setUploadPath(datePath);

            String uuid = UUID.randomUUID().toString();
            dto.setUuid(uuid);
            uploadFileName = uuid + "_" + uploadFileName;



            File saveFile = new File(uploadPath, uploadFileName);
            try {
                multipartFile.transferTo(saveFile);
                File thumbnail = new File(uploadPath, "s_" + uploadFileName);

                BufferedImage bo_img = ImageIO.read(saveFile);

                double ratio = 3;
                int w = (int) (bo_img.getWidth() / ratio);
                int h = (int) (bo_img.getHeight() / ratio);


                BufferedImage bt_img = new BufferedImage(w,h, BufferedImage.TYPE_3BYTE_BGR);

                Graphics2D graphic = bt_img.createGraphics();
                graphic.drawImage(bo_img,0,0,w,h,null);

                ImageIO.write(bt_img, "jpg", thumbnail);


            }catch (Exception e){
                e.printStackTrace();
            }

            list.add(dto);
        }

        return new ResponseEntity<List<AttachImageDTO>>(list , HttpStatus.OK);
    }
//    이미지파일 삭제
    @PostMapping("/deleteFile")
    public ResponseEntity deleteFile(String fileName){
        File file = null;
        try {
            file = new File("C:\\upload\\" + URLDecoder.decode(fileName, "UTF-8"));

            file.delete();

            String originFileName = file.getAbsolutePath().replace("s_","");
            log.info("originFileName : " + originFileName);
            file = new File(originFileName);

            file.delete();

        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<String>("fail", HttpStatus.NOT_IMPLEMENTED);
        }

        return new ResponseEntity<String>("success", HttpStatus.OK);
    }
    @DeleteMapping(value = "/{itemNo}", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> remove(@PathVariable("itemNo") Long itemNo){
        log.info(itemNo);
        itemService.remove(itemNo);
        return new ResponseEntity<>("removed", HttpStatus.OK);
    }


    @PostMapping(value = "/{itemNo}")
    public ResponseEntity modifyWithImg(@RequestBody ItemFormDTO itemFormDTO, @RequestParam(name = "itemImgFile")List<MultipartFile> itemImgFileList){
        if (itemImgFileList.get(0).isEmpty() && itemFormDTO.getItemNo() == null){
            return new ResponseEntity<String>("첫번째 이미지는 필수", HttpStatus.FORBIDDEN);
        }
        try {
            itemService.modifyWithImg(itemFormDTO, itemImgFileList);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("에러 발생", HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<String>("modified", HttpStatus.OK);
    }

//    @PutMapping(value = "/{itemNo}", produces = MediaType.TEXT_PLAIN_VALUE)
//    public ResponseEntity<String> modify(@RequestBody ItemDTO itemDTO){
//        log.info(itemDTO);
//        itemService.modify(itemDTO);
//        return new ResponseEntity<>("modified", HttpStatus.OK);
//    }

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

    @GetMapping("/{itemNo}")
    public ResponseEntity getItemDetail(@PathVariable("itemNo") Long itemNo){
        ItemFormDTO itemFormDTO = itemService.getItemDetail(itemNo);
        return new ResponseEntity<String>("아이템 상세정보",HttpStatus.OK);
    }
}