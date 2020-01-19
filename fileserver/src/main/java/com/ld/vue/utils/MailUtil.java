package com.ld.vue.utils;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.util.MailSSLSocketFactory;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

/**
 * @Author: liud
 * @Description: 邮箱验证工具类
 * @Date: 2020/1/9
*/
public class MailUtil {
    /**
     * 发送邮箱信息
     * @param sendTo
     * @param sendCode
     */
    public static void sendMail(String sendTo,String sendCode){
        String to = sendTo;

        String from = "1767317358@qq.com";

        String password="cxackhebqpgnbaeb";

        String host = "smtp.qq.com";

        Properties properties = System.getProperties();

        properties.setProperty("mail.smtp.host", host);

        properties.put("mail.smtp.auth", "true");
        MailSSLSocketFactory sf = null;
        try {
            sf = new MailSSLSocketFactory();
        } catch (GeneralSecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        sf.setTrustAllHosts(true);
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.ssl.socketFactory", sf);
        Session session = Session.getDefaultInstance(properties,new Authenticator(){
            @Override
            public PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication(from, password);
            }
        });

        try{
            MimeMessage message = new MimeMessage(session);

            message.setFrom(new InternetAddress(from));

            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            message.setSubject("验证码");

            //普通文本文件
//            message.setText("您本次操作的验证码为:"+sendCode);

            //html文本
            message.setContent("<span>您本次操作的验证码为:<span><br><h1>"+sendCode+"</h1>(此验证码5分钟内使用有效)",
                    "text/html;charset=UTF-8" );

            Transport.send(message);
        }catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
