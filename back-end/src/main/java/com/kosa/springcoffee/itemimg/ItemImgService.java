package com.kosa.springcoffee.itemimg;

import com.kosa.springcoffee.itemimg.dto.ItemImgDTO;
import com.kosa.springcoffee.itemimg.dto.ItemImgResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ItemImgService {
    private final ItemImgRepository itemImgRepository;

    @Transactional
    public ItemImgDTO findByFileNo(Long imgNo){
        ItemImg entity = itemImgRepository.findByItemImgNo(imgNo);

        ItemImgDTO dto = ItemImgDTO.builder()
                .origFileName(entity.getOrigFileName())
                .filePath(entity.getFilePath())
                .fileSize(entity.getFileSize())
                .build();


        return dto;
    }


    @Transactional
    public List<ItemImgResponseDTO> findAllByItem(Long itemNo){

        List<ItemImg> itemImgList = itemImgRepository.findAllByItemNo(itemNo);

        return itemImgList.stream().map(ItemImgResponseDTO::new).collect(Collectors.toList());
    }

//    public MultipartFile getMultipartFile(Long itemImgNo) throws IOException {
//        ItemImg img = itemImgRepository.findByItemImgNo(itemImgNo);
//        if(img == null) {
//            System.out.println("이미지 값이 없습니다.");
//        }
//        File file = new File(new File("").getAbsolutePath() + File.separator + File.separator + img.getFilePath());
//        FileItem fileItem = new DiskFileItem("originFile", Files.probeContentType(file.toPath()), false, file.getName(), (int) file.length(), file.getParentFile());
//        try {
//            InputStream input = new FileInputStream(file);
//            OutputStream os = fileItem.getOutputStream();
//            IOUtils.copy(input, os);
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//
//        MultipartFile multipartFile = new CommonsMultipartFile(fileItem);
//
//        return multipartFile;
//    }
//
//
//    public String convertBinary(MultipartFile files) throws Exception{
//        String fileName = StringUtils.cleanPath(files.getOriginalFilename()) ;
//        BufferedImage image = ImageIO.read(files.getInputStream());
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        ImageIO.write(image, fileName.substring(fileName.lastIndexOf(".") + 1), baos);
//        return new String(Base64.encodeBase64(baos.toByteArray()));
//    }

}
