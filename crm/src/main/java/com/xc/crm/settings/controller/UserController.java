package com.xc.crm.settings.controller;

import com.xc.crm.settings.domain.User;
import com.xc.crm.settings.service.UserService;
import com.xc.crm.settings.service.serviceImpl.UserServiceImpl;
import com.xc.crm.utils.MD5Util;
import com.xc.crm.utils.PrintJson;
import com.xc.crm.utils.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//@WebServlet(name = "UserController",urlPatterns = "/settings/user/login.do")
public class UserController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String path=request.getServletPath();//取到url-patterns的路径
        System.out.println(path+"这是path ");
        if("/settings/user/login.do".equals(path))
        {
            //执行login方法
            login(request,response);
        }
    }

private void login(HttpServletRequest request,HttpServletResponse response){
    System.out.println("进入到登录验证");
    String loginAct =request.getParameter("loginAct");
    String loginPwd =request.getParameter("loginPwd");

//把密码转换成密文形式
    loginPwd= MD5Util.getMD5(loginPwd);

//接收浏览器端的ip地址
    String ip=request.getRemoteAddr();
    System.out.println("接收到浏览器的ip地址:"+ip);

//记住，未来的业务层开发统一使用代理类的接口对象
    UserService userService= (UserService) ServiceFactory.getService(new UserServiceImpl());

    try {
        User user =userService.login(loginAct,loginPwd,ip);
        //将返回的user保存在session域中
        request.getSession().setAttribute("user",user);
        System.out.println(user);
//执行到这里，表示登录成功 "success":true
        PrintJson.printJsonFlag(response,true);

    }catch (Exception e){
        e.printStackTrace();
//如果登录失败，说明登录失败，为controller层跑出了异常
        /*
         "success":{false,msg}
         */

        String msg=e.getMessage();
        /*
        我们现在作为controller，需要为ajax请求提供多项信息
        可以有两种手段处理：
            （1）将多项信息打包成为map，将map解析为json串
            （2）创建一个展现值的对象（vo）
                        private boolean success;
                        private String msg;
             如果对于展现的信息将来还会有大量的使用，我们创建一个vo类，使用方便
             如果少量使用，我们使用map就可以了
        */

        Map<String,Object> map=new HashMap<String,Object>();
        map.put("success",false);
        map.put("msg",msg);
        PrintJson.printJsonObj(response,map);

    }


    }


}
