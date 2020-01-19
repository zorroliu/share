package com.ld.vue.utils;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import java.io.IOException;

/**
 * @Author: liud
 * @Description: 手机相关工具类
 * @Date: 2020/1/9 13:34
 */
public class MoblieUtils {
    /**
     * 向指定手机号发送验证码
     * @param sendNumber
     * @param securityCode
     */
    public static void send(String sendNumber,String securityCode){

        HttpClient client = new HttpClient();
        PostMethod post = new PostMethod("http://utf8.api.smschinese.cn");
        //在头文件中设置转码
        post.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");

        NameValuePair[] data = { new NameValuePair("Uid", "zerroliu"),
                new NameValuePair("Key", "d41d8cd98f00b204e980"),
                new NameValuePair("smsMob", sendNumber),
                new NameValuePair("smsText","您本次操作的验证码为"+securityCode+",此验证码3分钟内有效！")};
        post.setRequestBody(data);
        try {
            client.executeMethod(post);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Header[] headers = post.getResponseHeaders();
        int statusCode = post.getStatusCode();  System.out.println("statusCode:"+statusCode);
        for(Header h : headers)
        {System.out.println(h.toString()); }
        String result = null;
        try {
            result = new String(post.getResponseBodyAsString().getBytes("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(result); //打印返回消息状态
        post.releaseConnection();

    }
}
