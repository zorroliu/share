package com.ld.vue.config;

import com.ld.vue.filter.AccessControlAllowOriginFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: liud
 * @Description: 代码注册过滤器
 * @Date: 2020/1/12 16:40
 */
@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean reqResFilter1() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();

        AccessControlAllowOriginFilter filter = new AccessControlAllowOriginFilter();

        filterRegistrationBean.setFilter(filter);
        //配置过滤规则
        filterRegistrationBean.addUrlPatterns("/*");
        //设置init参数
        filterRegistrationBean.addInitParameter("test","filterparam");
        //设置过滤器名称
        filterRegistrationBean.setName("crossOriginFilter");
        //执行次序
        filterRegistrationBean.setOrder(1);

        return filterRegistrationBean;
    }

}
