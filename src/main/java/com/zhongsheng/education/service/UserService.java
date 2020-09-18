package com.zhongsheng.education.service;

import com.zhongsheng.education.entiy.User;

public interface UserService {
   User selectAllUser();
   public Integer addUser(Integer username,String password,Integer whoid);
   public User selectUserId(Integer whoid);
}
