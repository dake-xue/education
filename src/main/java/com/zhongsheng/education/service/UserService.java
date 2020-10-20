package com.zhongsheng.education.service;

import com.zhongsheng.education.entiy.PermissionInfo;
import com.zhongsheng.education.entiy.User;

import java.util.List;
import java.util.Set;

public interface UserService {
   User selectWho(User user);
   List<User> selectAllUser(Integer page,Integer limit);
   public Integer addUser(User user);
   public User selectUserId(Integer whoid);
   Integer updatePass(User user);

   Set<String> getRoles(String username);
   User getByUsername(String username);
   Integer updateUserStatus(User user);
}
