package com.xc.crm.settings.service;

import com.xc.crm.exception.LoginException;
import com.xc.crm.settings.domain.User;

import java.util.List;

public interface UserService {

    User login(String logAct,String logPwd,String ip) throws LoginException;

    List<User> getUserList();

}
