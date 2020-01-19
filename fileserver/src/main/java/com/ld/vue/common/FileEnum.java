package com.ld.vue.common;

/**
 * 文件操作枚举
 * @author liud
 */
public enum FileEnum implements BaseEnum {
    FILE_UPLOAD_SUCCESS("100","文件上传成功!"),
    FILE_DOWNLOAD_SUCCESS("101","文件下载成功!"),
    FILE_UPLOAD_IS_NULL("102","上传文件为空！"),
    File_is_FIND("104","对应文件未找到!"),
    FILE_IS_VALID("105","文件有效，可以进行下载"),
    DOWNLOADCODE_IS_NOT_VALID("106","下载码无效！")
    ;

    FileEnum(String code,String value){
        this.code = code;
        this.value = value;
    }
    private String code;

    private String value;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String getKey() {
        return code;
    }

    @Override
    public String getValue() {
        return value;
    }
}
