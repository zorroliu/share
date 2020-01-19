package com.ld.vue.utils;

import java.util.Random;

/**
 * @Author: liud
 * @Description: 随机码生成工具
 * @Date: 20:38 2019/11/15
*/
public class RandomCreatUtil {
    /**
     * 生成指定位数的随机数字、字母码
     * @param number
     * @return
     */
    public static String codeCreate(int number) {
        String buf[] = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L",
                "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z",
                "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l",
                "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
                "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        String result = "";
        for (int i = 0; i < number; i++) {
            Random random = new Random();
            int randomDigit = random.nextInt(62);
            result = result + buf[randomDigit];
        }
        return result;
    }

    /**
     * 生成指定位数的随机数字码
     * @param number
     * @return
     */
    public static String AccountGenerated(int number){
        String buf[] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        String result = "";
        for (int i = 0; i < number; i++) {
            Random random = new Random();
            int randomDigit = random.nextInt(10);
            result = result + buf[randomDigit];
        }
        return result;
    }
}
