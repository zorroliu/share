package com.ld.vue.service;

import com.ld.vue.pojo.FileInfo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author liud
 */
public interface FileService {
    /**
     * 文件上传
     * @param file
     * @param req
     * @return
     * @throws Exception
     */
    String upload(MultipartFile file, HttpServletRequest req) throws Exception;

    /**
     * 文件下载
     * @param download
     * @param resp
     * @return
     */
    String download(String download, HttpServletResponse resp);

    /**
     * 通过下载码查询文件信息
     * @param code
     * @return
     */
    FileInfo selectFileByCode(String code);

    String downloadVerify(String downloadCode);
}
