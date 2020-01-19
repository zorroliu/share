package com.ld.vue.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: liud
 * @Description: 解决跨域问题
 * @Date: 2020/1/12 13:09
 */
//@WebFilter(urlPatterns = "/*")
public class AccessControlAllowOriginFilter implements Filter {
    private static String param;
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) resp;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Allow-Credentials", "true");

        chain.doFilter(req, response);
    }

    @Override
    public void destroy() {

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException{
        param = filterConfig.getInitParameter("test");
    }
}
