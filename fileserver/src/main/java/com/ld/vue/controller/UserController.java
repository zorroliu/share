package com.ld.vue.controller;

import com.ld.vue.pojo.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: liud
 * @Description: TODO
 * @Date: 2020/1/11 22:02
 */
@RestController
public class UserController {
    @PostMapping("/login")
    public String login(User user) {
        return "success";
    }
    @GetMapping("/logout")
    public String logout() {
        System.out.println("退出登录");

        return "";
    }
}
