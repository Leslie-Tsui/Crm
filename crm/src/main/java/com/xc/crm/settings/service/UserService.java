package com.xc.crm.settings.service;

import com.xc.crm.exception.LoginException;
import com.xc.crm.settings.domain.User;

public interface UserService {

    User login(String logAct,String logPwd,String ip) throws LoginException;

}
