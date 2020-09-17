package com.zhongsheng.education.mapper;

import com.zhongsheng.education.entiy.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("select * from user")
    public User test();
}
