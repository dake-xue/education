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
    int addUser1(User user);

    //修改密码
    @Update("update user set password = #{password} where uid = #{uid} ")
    int updateUserPassword(User user);

    //添加用户——学生——2
    @Insert("insert into user(username,password,whoid) values(${phone},${pwd},2) ")
    public Integer addUser(Integer phone,String pwd);

    //查询添加用户id
    @Select("select * from user whoid=2 order by uid desc limit 0,1")
    public User selectUserId();

}
