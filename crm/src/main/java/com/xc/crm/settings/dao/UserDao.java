package com.xc.crm.settings.dao;

import com.xc.crm.settings.domain.User;
import java.util.List;
import java.util.Map;

public interface UserDao {

    User Login(Map<String,Object> map);

    List<User> getUserList();



}
