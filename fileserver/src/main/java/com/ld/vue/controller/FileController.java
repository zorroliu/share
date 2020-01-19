package com.ld.vue.controller;

import com.ld.vue.common.FileEnum;
import com.ld.vue.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: liud
 * @Description: 文件操作控制器
 * @Date: 2020/1/14 22:19
 */
@RestController
public class FileController {
    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    public String upload(MultipartFile file, HttpServletRequest req) {
        if(null == file) {
            return FileEnum.FILE_UPLOAD_IS_NULL.getKey();
        }
        String downloadCode = "";
        try {
            downloadCode = fileService.upload(file, req);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return downloadCode;
    }

    @GetMapping("/download/{downloadCode}")
    public String download(@PathVariable("downloadCode") String downloadCode, HttpServletResponse resp) {
        return fileService.download(downloadCode, resp);
    }

    @PostMapping("/downloadVerify")
    public String downloadVerify(String downloadCode) {
        return fileService.downloadVerify(downloadCode);
    }
}
