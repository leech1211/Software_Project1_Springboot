package com.example.Proj1_2019202085.service;

import com.example.Proj1_2019202085.domain.Gallery;
import com.example.Proj1_2019202085.repository.GalleryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class GalleryService {
    @Autowired
    private GalleryRepo galleryRepo;
    @Transactional
    public void create(Gallery gallery, MultipartFile file) throws Exception
    {
        //저장할 경로
        String path = System.getProperty("user.dir") + "\\src\\main\\resources\\static";

        UUID uuid = UUID.randomUUID();        //랜덤 UUID 생성
        String imgName = uuid + "_" + file.getOriginalFilename();       //파일이름과 UUID 합치기
        File savedFile = new File(path,imgName);        //파일생성
        file.transferTo(savedFile);                     //경로에 생성
        gallery.setImageName(imgName);                  //이미지이름
        gallery.setImagePath(imgName);                  //이미지 경로
        galleryRepo.save(gallery);                      //save
    }

    public void delete(Long id){ //Delete Data from Board Table with ID
        galleryRepo.deleteById(id);
    }

    public Gallery getGalleryById(Long imageId) {
        return galleryRepo.findById(imageId).orElse(null);
    }

    @Transactional
    public void edit(Gallery gallery,MultipartFile file) throws IOException {
        //저장할 경로
        String path = System.getProperty("user.dir") + "\\src\\main\\resources\\static";

        UUID uuid = UUID.randomUUID();      //랜덤 UUID 생성
        String imgName = uuid + "_" + file.getOriginalFilename();   //파일이름과 UUID 합치기
        File savedFile = new File(path,imgName);            //파일생성
        file.transferTo(savedFile);                     //경로에 생성
        gallery.setImageName(imgName);                  //이미지이름
        gallery.setImagePath(imgName);                  //이미지 경로
        galleryRepo.save(gallery);                      //기존의 데이터 수정
    }


}
