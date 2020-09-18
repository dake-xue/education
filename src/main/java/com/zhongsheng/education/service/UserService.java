package com.zhongsheng.education.service;

import com.zhongsheng.education.entiy.User;

public interface UserService {
   User selectAllUser();
   public Integer addUser(Integer phone,String pwd);
   public User selectUserId();
}
