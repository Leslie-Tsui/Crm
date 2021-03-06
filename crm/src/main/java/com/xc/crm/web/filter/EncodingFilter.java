package com.xc.crm.web.filter;

import javax.servlet.*;
import java.io.IOException;
public class EncodingFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("进入到过滤字符编码的过滤器");
//       过滤post请求中文参数编码
        servletRequest.setCharacterEncoding("utf-8");
//        过滤响应流响应中文乱码
        servletResponse.setContentType("text/html;charset=utf-8");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }


}
