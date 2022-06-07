package com.example.javaweb.controller;

import com.example.javaweb.exception.JavaWebException;
import com.example.javaweb.result.Result;
import com.example.javaweb.result.ResultEnum;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

/**
 * @author 怪兽
 * @version 1.0
 * Create by 2022/5/24 19:09
 */
@RestController
@CrossOrigin
public class UploadController {

    @Value("${file.path}")
    private String filePath;

    private DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss-Sss");

    @PostMapping("/upload")
    public Result<String> upload(@RequestParam("file")MultipartFile file,
                                 @RequestParam(value = "deviceNo", required = false) String deviceNo) {
        try {
            String fileName = saveFile(file);
            return Result.ok(fileName);
        }catch (JavaWebException e) {
            return Result.build(e.getCode(), e.getMessage());
        }
    }

    @PostMapping("/multiUpload")
    public Result<String> multiUpload(@RequestParam("file")MultipartFile[] multipartFile) {
        for (MultipartFile file : multipartFile) {
            saveFile(file);
        }
        return Result.ok();
    }

    private String saveFile(MultipartFile file) throws JavaWebException {
        if (file.isEmpty()) {
            throw new JavaWebException(ResultEnum.FILE_NOT_SELECT);
        }
        String fileName = LocalDateTime.now().format(df);

        File temp = new File(filePath);
        if (temp.exists()) {
            temp.mkdir();
        }

        File localFile = new File(filePath + "\\" + fileName + ".jpg");
        try {
            file.transferTo(localFile); // 把上传的文件保存至本地
        }catch (Exception e) {
            e.printStackTrace();
            throw new JavaWebException(ResultEnum.FILE_UPLOAD_FAIL);
        }
        return fileName + ".jpg";
    }
}
