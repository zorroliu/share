package com.ld.vue.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: liud
 * @Description: 日期工具类
 * @Date: 2020/1/8 17:05
 */
public class DateUtils {
    private static final String DEFAULT_SDF = "yyyy-MM-dd";

    public static String format(Date date) {
        return getSdf(DEFAULT_SDF).format(date);
    }

    public static String format(Date date,String format) {
        return getSdf(VerifyUtil.isEmpty(format) ? DEFAULT_SDF : format).format(date);
    }

    public static SimpleDateFormat getSdf(String format) {
        return new SimpleDateFormat(format);
    }
}
