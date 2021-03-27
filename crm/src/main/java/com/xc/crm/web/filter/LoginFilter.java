package com.xc.crm.web.filter;

import com.xc.crm.settings.domain.User;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class LoginFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("进入到是否登录的验证");

        HttpServletRequest request= (HttpServletRequest) servletRequest;
        HttpServletResponse response= (HttpServletResponse) servletResponse;
        HttpSession httpSession= request.getSession();
        User user= (User) httpSession.getAttribute("user");
//        如果user不为空，说明登录过，放行
        if (user!=null){
            filterChain.doFilter(servletRequest, servletResponse);
        }
        else{
//            需要先去登录
            /*
            * 重定向到登录页
            *
            *重定向的路径怎么写？
            *       答：在实际项目开发中，对于路径的使用，不论是前端还是后端的操作，应该一律使用绝对路径
            *           关于转发和重定向的路径的写法如下：
            *               转发：使用的是一种特殊的绝对路径的方式，这种绝对路径前面不加/项目名，这种路径也叫内部路径，/login.jsp
            *               重定向：使用的是传统绝对路径的写法，前面必须以/项目名开头，后面跟具体的资源路径  /crm/login.jsp
            *为什么使用重定向，使用转发不行吗？
            *       答：转发之后，路径会停留在老路径上，而不是跳转之后最新资源的路径
            *           我们应该在为用户跳转到登录页之后，将浏览器的地址栏应该自动设置为当前登录页的路径
            * */

            if("/login.jsp".equals(request.getServletPath())||"/settings/user/login.do".equals(request.getServletPath())){          //放行login.jsp和login.do

                filterChain.doFilter(servletRequest, servletResponse);

            }
else{
                response.sendRedirect(request.getContextPath() + "/login.jsp");

            }  }


    }
}
