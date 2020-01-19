package com.ld.vue.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: liud
 * @Description: TODO
 * @Date: 2020/1/8 16:29
 */
public class HttpUtils {
    public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if(!VerifyUtil.isEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = ip.indexOf(",");

            if(index != -1){
                return ip.substring(0,index);
            }else{
                return ip;
            }
        }
        ip = request.getHeader("X-Real-IP");

        if(!VerifyUtil.isEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
            return ip;
        }

        return request.getRemoteAddr();
    }
}
