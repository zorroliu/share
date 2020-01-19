package com.ld.vue.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Author: liud
 * @Description: 常用配置参数类
 * @Date: 2020/1/15 23:49
 */
@Component
@PropertySource("classpath:config/commonparam.properties")
public class CommonParam {
    @Value("${downloadCodeNumber}")
    private Integer downloadCodeNumber;
    @Value("${fileDir}")
    private String fileDir;

    public Integer getDownloadCodeNumber() {
        return downloadCodeNumber;
    }

    public void setDownloadCodeNumber(Integer downloadCodeNumber) {
        this.downloadCodeNumber = downloadCodeNumber;
    }

    public String getFileDir() {
        return fileDir;
    }

    public void setFileDir(String fileDir) {
        this.fileDir = fileDir;
    }
}
