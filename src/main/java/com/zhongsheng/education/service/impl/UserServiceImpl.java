package com.zhongsheng.education.service.impl;

import com.zhongsheng.education.entiy.User;
import com.zhongsheng.education.mapper.UserMapper;
import com.zhongsheng.education.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public User test() {
        return userMapper.test();
    }
}
