package com.ld.vue.service.impl;

import com.ld.vue.common.FileEnum;
import com.ld.vue.exception.BusinessException;
import com.ld.vue.mapper.FileInfoMapper;
import com.ld.vue.model.CommonParam;
import com.ld.vue.pojo.FileInfo;
import com.ld.vue.pojo.FileInfoExample;
import com.ld.vue.service.FileService;
import com.ld.vue.utils.DateUtils;
import com.ld.vue.utils.RandomCreatUtil;
import com.ld.vue.utils.VerifyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

/**
 * @Author: liud
 * @Description: 文件相关业务层
 * @Date: 2019/11/15 18:40
 */
@Service("fileService")
@Transactional(rollbackFor = Exception.class)
public class FileServiceImpl implements FileService {
    private Logger log = LoggerFactory.getLogger("fileop");

    @Autowired
    private FileInfoMapper fileInfoMapper;
    @Autowired
    private CommonParam commonParam;

    @Override
    public String upload(MultipartFile file, HttpServletRequest req) throws Exception {
        /***
         * 下载码返回规则
         * 1.先查询数据库，判断是否有可用的下载码
         *      有则直接使用
         * 2.没有则生成下载码
         *      生成时，需判断在数据库有没有这个下载码
         *      没有直接使用，有则继续生成，知道可以使用为止
         */
        if(VerifyUtil.isEmpty(file)) {
            return "";
        }
        String downloadCode = "";
        log.info("----------------------"+ DateUtils.format(new Date(),"yyyy-MM-dd HH:mm;ss") +"----------------------");
        log.info("操作类型：上传");
        //文件名
        String fileName = file.getOriginalFilename();
        log.info("文件名："+fileName);
        //文件大小
        Double fileSize = VerifyUtil.isEmpty(file.getSize()) ? 0d : (double) file.getSize();
        log.info("文件大小："+fileSize);
        try {
            FileInfo fileInfo = new FileInfo();

            //查询所有可用的下载码
            List<String> isValidCodeList = fileInfoMapper.selectEnableCode();

            List<String> allCodeList = fileInfoMapper.selectAllCode();
            //如果可用下载码不为空，则取第一条为作为下载码
            if(!VerifyUtil.isEmpty(isValidCodeList)) {
                downloadCode = isValidCodeList.get(0);

                fileInfo = selectFileByCode(downloadCode);
                fileInfo.setIsValid(0);
            }else {
                //如果没有查询到一条下载码的数据,则表示数据库数据为空，直接生成下载码
                if(VerifyUtil.isEmpty(allCodeList)) {
                    downloadCode = RandomCreatUtil.codeCreate(commonParam.getDownloadCodeNumber());
                }else {
                    downloadCode = createCode(allCodeList);
                }

            }
            fileInfo.setDownloadCode(downloadCode);
            fileInfo.setFileName(fileName);
            fileInfo.setFileSize(fileSize);
            //判断Id是否为空，为空插入，反之更新
            if(VerifyUtil.isEmpty(fileInfo.getId())) {
                //保存实体
                fileInfoMapper.insert(fileInfo);
            }else {
                //更新实体
                fileInfoMapper.updateByPrimaryKey(fileInfo);
            }


            file.transferTo(new File(commonParam.getFileDir()+fileName));

            log.info("下载码："+downloadCode);
            log.info("上传状态：成功！");
        } catch (Exception e) {
            e.printStackTrace();
            log.info("上传状态：失败！");
            log.error("错误原因："+e.getMessage());
            throw new Exception();
        }

        log.info("-------------------------------End-----------------------------");
        return downloadCode;
    }

    @Override
    public String download(String downloadCode, HttpServletResponse resp) {
        FileInfo fileInfo = selectFileByCode(downloadCode);
        //判断下载码是否可找到对应的文件
        if(VerifyUtil.isEmpty(fileInfo)) {
            return FileEnum.File_is_FIND.getKey();
        }

        String fileName = fileInfo.getFileName();
        File file = new File(commonParam.getFileDir() + fileName);

        if(VerifyUtil.isEmpty(file)) {
            throw new BusinessException("文件未找到！");
        }
        FileInputStream in = null;
        OutputStream out = null;

        try {

            resp.setContentType("application/force-download");
            // 设置文件名，fileName是下载文件的文件名
            resp.setHeader("Content-Disposition",
                    "attachment;fileName="+ URLEncoder.encode(fileName, "UTF-8"));
            resp.setHeader("Access-Control-Expose-Headers","Content-Disposition");

            in = new FileInputStream(file);
            int len = 0;
            byte[] buffer = new byte[1024];
            out = resp.getOutputStream();
            while ((len = in.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("文件下载异常！");
        }finally {
            try {
                if(null != in) {
                    in.close();
                }
                if(null != out) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //删除文件
        file.delete();

        //将下载码更新为可用状态
        fileInfo.setIsValid(1);
        fileInfoMapper.updateByPrimaryKey(fileInfo);

        return FileEnum.FILE_DOWNLOAD_SUCCESS.getKey();
    }

    @Override
    public FileInfo selectFileByCode(String code) {
        FileInfoExample e = new FileInfoExample();
        FileInfoExample.Criteria or = e.or();
        or.andDownloadCodeEqualTo(code);
        List<FileInfo> fileInfos = fileInfoMapper.selectByExample(e);
        return VerifyUtil.isEmpty(fileInfos) ? null : fileInfos.get(0);
    }

    @Override
    public String downloadVerify(String downloadCode) {
        FileInfo fileInfo = selectFileByCode(downloadCode);
        //判断下载码是否可找到对应的文件
        if(VerifyUtil.isEmpty(fileInfo)) {
            return FileEnum.DOWNLOADCODE_IS_NOT_VALID.getKey();
        }
        File file = new File(commonParam.getFileDir() + fileInfo.getFileName());
        if(VerifyUtil.isEmpty(file)) {
            return FileEnum.File_is_FIND.getKey();
        }

        return FileEnum.FILE_IS_VALID.getKey();
    }

    /**
     * 递归生成下载码
     * @param list
     */
    private String createCode(List<String> list) {
        String code = RandomCreatUtil.codeCreate(commonParam.getDownloadCodeNumber());
        if(list.contains(code)) {
            createCode(list);
        }

        return code;
    }

}
