package com.zhongsheng.education.service.impl;

import com.github.pagehelper.PageHelper;
import com.zhongsheng.education.entiy.User;
import com.zhongsheng.education.entiy.UserVo;
import com.zhongsheng.education.mapper.UserMapper;
import com.zhongsheng.education.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public User selectWho(User user) {
        return userMapper.selectWho(user);
    }

    @Override
    public List<UserVo> selectAllUser(Integer page, Integer limit,String name) {
        PageHelper.startPage(page,limit);
        return userMapper.selectAllUser(name);
    }


    @Override
    public Integer addUser(User user) {
        return userMapper.addUser(user);
    }

    @Override
    public User selectUserId(Integer whoid) {
        return userMapper.selectUserId(whoid);
    }

    @Override
    public Integer updatePass(User user) {
        User newUser =  userMapper.selectWho(user);
        if(newUser==null){
            return 0;
        }
        user.setUid(newUser.getUid());
        user.setPassword(user.getNewPassword());
        return userMapper.updateUserPassword(user);
    }

    /**
     * @创建人 xueke
     * @创建时间 2020/9/30
     * @描述 获取角色信息
    */
    @Override
    public Set<String> getRoles(String username) {
        return userMapper.getRoles(username);
    }
    /**
     * @创建人 xueke
     * @创建时间 2020/9/30
     * @描述 根据用户名查询该用户
    */
    @Override
    public User getByUsername(String username) {
        return userMapper.getByUsername(username);
    }

    @Override
    public Integer updateUserStatus(User user) {
        return userMapper.updateUserStatus(user);
    }

    public UserVo userDetails(Integer uid){
        return userMapper.userDetails(uid);
    };
}
