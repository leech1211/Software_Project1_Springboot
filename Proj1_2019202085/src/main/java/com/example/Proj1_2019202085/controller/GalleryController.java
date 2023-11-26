package com.example.Proj1_2019202085.controller;

import com.example.Proj1_2019202085.domain.Gallery;
import com.example.Proj1_2019202085.repository.GalleryRepo;
import com.example.Proj1_2019202085.service.GalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Collections;
import java.util.List;

@Controller
public class GalleryController {
    @Autowired
    private GalleryService galleryService;

    @Autowired
    private GalleryRepo galleryRepo;

    @GetMapping(value = {"/", "/index.html"})
    public String getIndexPage(Model model) {
        List<Gallery> galleryList = galleryRepo.findAll(); // 데이터베이스에서 모든 갤러리 항목을 가져옴
        Collections.reverse(galleryList); // 데이터 역순으로 정렬
        model.addAttribute("galleryList", galleryList); // 모델에 galleryList를 추가하여 템플릿에 전달
        return "Index";
    }

    @PostMapping("/upload")
    public RedirectView handleFileUpload(Gallery gallery, MultipartFile file) throws Exception {
        if (!file.getContentType().startsWith("image/")) {      //이미지가 아닌경우
        }
        else
        {
            galleryService.create(gallery, file);   // DB데이터 생성
        }

        return new RedirectView("/");
    }



    @GetMapping("/upload")
    public String upload() {
        return "Upload";    //Upload.html
    }

    @GetMapping("/image/{imageId}")
    public String imageView(@PathVariable("imageId") Long imageId, Model model) {
        // id로 데이터 찾기
        Gallery gallery = galleryService.getGalleryById(imageId);
        // 모델에 데이터 전달
        model.addAttribute("imageId", imageId);
        model.addAttribute("title", gallery.getTitle());
        model.addAttribute("comment", gallery.getComment());
        model.addAttribute("imagePath", gallery.getImagePath());

        return "ImageView";
    }

    @PostMapping("/delete/{imageId}")
    public RedirectView deleteImage(@PathVariable Long imageId) {
        // 이미지 삭제 로직 실행
        galleryService.delete(imageId);

        // index 페이지로 리다이렉트
        return new RedirectView("/");
    }

    @GetMapping("/edit/{imageId}")
    public String showEditForm(@PathVariable Long imageId, Model model) {
        // 이미지 정보 조회
        Gallery gallery = galleryService.getGalleryById(imageId);

        // 모델에 데이터 전달
        model.addAttribute("imageId", imageId);
        model.addAttribute("title", gallery.getTitle());
        model.addAttribute("comment", gallery.getComment());

        return "Upload";
    }

    @PostMapping("/edit")
    public RedirectView editImg(Gallery gallery, MultipartFile file) throws Exception {
        if (!file.getContentType().startsWith("image/")) {  // 이미지가 아닌경우
        }
        else
        {
            galleryService.edit(gallery,file);      // DB 데이터 수정
        }

        return new RedirectView("/"); // 인덱스 페이지로 리다이렉트함
    }


}
