package com.zhongsheng.education.mapper;

import com.zhongsheng.education.entiy.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {

    @Select("select * from user")
    User selectAllUser();

    @Insert("insert into user(username,password,whoid) values(#{username},#{password},#{whoid})")
    int addUser(User user);

    //修改密码
    @Update("update user set password = #{password} where uid = #{uid} ")
    int updateUserPassword(User user);

}
