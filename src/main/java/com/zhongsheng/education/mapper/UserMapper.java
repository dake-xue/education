package com.zhongsheng.education.mapper;

import com.zhongsheng.education.entiy.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select * from user")
    List<User> selectAllUser();

    //添加用户
    @Insert("insert into user(name,username,password,whoid) values(#{name},#{username},#{password},#{whoid})")
    public Integer addUser(User user);

    //修改密码
    @Update("update user set password = #{password} where uid = #{uid} ")
    int updateUserPassword(User user);

    //查询新用户id
    @Select("select * from user whoid=#{whoid} order by uid desc limit 0,1")
    public User selectUserId(Integer whoid);

    @Select("select * from user where username=#{username} and password=#{password}")
    public User selectWho(User user);

}
