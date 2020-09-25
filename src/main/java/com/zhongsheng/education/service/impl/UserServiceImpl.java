package com.zhongsheng.education.service.impl;

import com.github.pagehelper.PageHelper;
import com.zhongsheng.education.entiy.User;
import com.zhongsheng.education.mapper.UserMapper;
import com.zhongsheng.education.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public User selectWho(User user) {
        return userMapper.selectWho(user);
    }

    @Override
    public List<User> selectAllUser(Integer page,Integer limit) {
        PageHelper.startPage(page,limit);
        return userMapper.selectAllUser();
    }


    @Override
    public Integer addUser(User user) {
        return userMapper.addUser(user);
    }

    @Override
    public User selectUserId(Integer whoid) {
        return userMapper.selectUserId(whoid);
    };
}
