package com.xc.crm.workbench.controller;

import com.xc.crm.settings.domain.User;
import com.xc.crm.settings.service.UserService;
import com.xc.crm.settings.service.serviceImpl.UserServiceImpl;
import com.xc.crm.utils.MD5Util;
import com.xc.crm.utils.PrintJson;
import com.xc.crm.utils.ServiceFactory;
import com.xc.crm.utils.UUIDUtil;
import com.xc.crm.workbench.domain.Activity;
import com.xc.crm.workbench.service.ActivityService;
import com.xc.crm.workbench.service.impl.ActivityServiceImpl;

import javax.jws.soap.SOAPBinding;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@WebServlet(name = "UserController",urlPatterns = "/settings/user/login.do")
public class ActivityController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String path=request.getServletPath();//取到url-patterns的路径

        System.out.println("进入到市场活动控制器");
        if("/workbench/activity/getUserList.do".equals(path))
        {
            List<User> userList=getUserList(request,response);

        }
        else if("/workbench/activity/save.do".equals(path)){
            save(request,response);
        }
    }

    private void save(HttpServletRequest request, HttpServletResponse response) {
        String id= UUIDUtil.getUUID();
        String owner=request.getParameter("owner");
        String name=request.getParameter("name");
        String startDate=request.getParameter("startDate");
        String endDate=request.getParameter("endDate");
        String cost=request.getParameter("cost");
        String description=request.getParameter("description");
        //创建时间：当前系统时间
        String createTime=request.getParameter("createTime");
        //创建人，当前登录用户
        String createBy=((User)request.getSession().getAttribute("user")).getName();

        Activity activity=new Activity();
        activity.setId(id);
        activity.setCost(cost);
        activity.setName(name);
        activity.setOwner(owner);
        activity.setStartDate(startDate);
        activity.setEndDate(endDate);
        activity.setDescription(description);
        activity.setCreateTime(createTime);
        activity.setCreateBy(createBy);

        ActivityService activityService= (ActivityServiceImpl) ServiceFactory.getService(new ActivityServiceImpl());

        boolean flag=activityService.save(activity);
        PrintJson.printJsonFlag(response,flag);

        String editTime=request.getParameter("");
        String editBy=request.getParameter("");


    }


    private List<User> getUserList(HttpServletRequest request,HttpServletResponse response){

        System.out.println("取得用户信息列表");

        UserService userService= (UserService) ServiceFactory.getService(new UserServiceImpl());

        List<User> userList=userService.getUserList();

        PrintJson.printJsonObj(response,userList);

        return null;
    }

}
