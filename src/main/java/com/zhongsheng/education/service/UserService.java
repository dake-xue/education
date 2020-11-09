package com.zhongsheng.education.service;

import com.zhongsheng.education.entiy.User;
import com.zhongsheng.education.entiy.UserVo;

import java.util.List;
import java.util.Set;

public interface UserService {
   User selectWho(User user);
   List<UserVo> selectAllUser(Integer page, Integer limit,String name);
   public Integer addUser(User user);
   public User selectUserId(Integer whoid);
   Integer updatePass(User user);

   Set<String> getRoles(String username);
   User getByUsername(String username);
   Integer updateUserStatus(User user);
    UserVo userDetails(Integer uid);
   Integer selectUsername(String username);
}
