package com.ld.vue.utils;

import java.util.Collection;
import java.util.Map;

/**
 * @Author: liud
 * @Description: 常用验证工具类
 * @Date: 2020/1/7 18:15
 */
public class VerifyUtil {
    /**
     * 判断是否为空
     * @param obj
     * @return
     */
    public static boolean isEmpty(Object obj) {
        if(null == obj) {
            return true;
        }
        if(obj instanceof String) {
            return "".equals(obj) || ((String)obj).length() == 0;
        }else if(obj instanceof Collection) {
            return ((Collection) obj).size() == 0;
        }else{
            return obj instanceof Map ? ((Map) obj).size() == 0 : false;
        }

    }
}
