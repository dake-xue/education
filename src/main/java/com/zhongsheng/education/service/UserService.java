package com.zhongsheng.education.service;

import com.zhongsheng.education.entiy.User;

import java.util.List;

public interface UserService {
   User selectWho(User user);
   List<User> selectAllUser(Integer page,Integer limit);
   public Integer addUser(User user);
   public User selectUserId(Integer whoid);
}
