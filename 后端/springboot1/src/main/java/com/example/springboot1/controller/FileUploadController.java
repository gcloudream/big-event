package com.example.springboot1.controller;

import com.example.springboot1.pojo.Result;
import com.example.springboot1.utils.AliOSSUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
public class FileUploadController {
    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file) throws Exception {
        String originalFilename = file.getOriginalFilename();
        String filename= UUID.randomUUID().toString()+originalFilename.substring(originalFilename.lastIndexOf("."));
//        file.transferTo(new Fil e("D:\\June\\Project\\springboot1\\src\\main\\resources\\picture\\" + filename));
        String url = AliOSSUtil.uploadfile(filename, file.getInputStream());
        return Result.success(url);
    }
}
