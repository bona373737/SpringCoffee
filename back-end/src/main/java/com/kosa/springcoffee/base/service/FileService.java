package com.kosa.springcoffee.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

@Service
@Log4j2
public class FileService {
    public String uploadFile(String uploadPath, String originFileName, byte[] fileData) throws IOException{
        UUID uuid = UUID.randomUUID();
        String extension = originFileName.substring(originFileName.lastIndexOf("."));
        String savedFileName= uuid.toString() + extension;

        String fileUploadFulUrl = uploadPath + "/" + savedFileName;

        FileOutputStream fos = new FileOutputStream(fileUploadFulUrl);
        fos.write(fileData);
        fos.close();

        return savedFileName;


    }


    public void deleteFile(String filePath){
        File file = new File(filePath);

        if (file.exists()){
            file.delete();
            log.info("File delete");
        }else {
            log.info("No file");
        }
    }

}
