package com.ld.vue;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @Author: liud
 * @Description: 可以替代web.xml,不用配置web.xml就可以使用servlet、filter等
 * @Date: 2020/1/18 22:33
 */
public class ServletInitializer extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(VueApplication.class);
    }
}
