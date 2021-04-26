package com.xc.crm.workbench.service.impl;

import com.xc.crm.utils.SqlSessionUtil;
import com.xc.crm.workbench.dao.ActivityDao;
import com.xc.crm.workbench.domain.Activity;
import com.xc.crm.workbench.service.ActivityService;

public class ActivityServiceImpl implements ActivityService {
     private ActivityDao activityDao= SqlSessionUtil.getSqlSession().getMapper(ActivityDao.class);

     public boolean save(Activity activity){

          boolean flag=true;

          int count = activityDao.save(activity);
          if(count!=1){

               flag=false;

          }
          return flag;

     }



}
