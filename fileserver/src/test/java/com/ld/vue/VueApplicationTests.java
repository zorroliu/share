package com.ld.vue;

import com.ld.vue.mapper.FileInfoMapper;
import com.ld.vue.model.CommonParam;
import com.ld.vue.utils.MailUtil;
import com.ld.vue.utils.QrCodeUtil;
import com.ld.vue.utils.RandomCreatUtil;
import com.ld.vue.utils.VerifyUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sun.net.www.http.HttpClient;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
class VueApplicationTests {
    @Autowired
    private FileInfoMapper fileInfoMapper;
    @Autowired
    private CommonParam commonParam;

    @Test
    void contextLoads() throws Exception {
        //MailUtil.sendMail("599483010@qq.com","123456");
        // 存放在二维码中的内容
        String text = "http://www.liudlx.cn/blog";
        // 嵌入二维码的图片路径
        //String imgPath = "G:/qrCode/dog.jpg";
        // 生成的二维码的路径及名称
        String destPath = "D:/Test/test.jpg";
        //生成二维码r
        QrCodeUtil.encode(text,destPath);
        // 解析二维码
        String str = QrCodeUtil.decode(destPath);
        // 打印出解析出的内容
        System.out.println(str);
    }

    @Test
    void uploadTest(){
        Integer downloadCodeNumber = commonParam.getDownloadCodeNumber();
        String code = "";
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            list.add(""+i);
        }

        code = createCode(list);
    }

    private String createCode(List<String> list) {
        String code = RandomCreatUtil.AccountGenerated(6);
        if(list.contains(code)) {
            createCode(list);
        }
        return code;
    }
    @Test
    public void restUrl() {

    }
}
