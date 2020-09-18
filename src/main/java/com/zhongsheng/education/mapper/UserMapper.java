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

    //添加用户
    @Insert("insert into user(username,password,whoid) values(#{username},#{password},#{whoid})")
    public Integer addUser(Integer username,String password,Integer whoid);

    //修改密码
    @Update("update user set password = #{password} where uid = #{uid} ")
    int updateUserPassword(User user);

    //查询新用户id
    @Select("select * from user whoid=#{whoid} order by uid desc limit 0,1")
    public User selectUserId(Integer whoid);

}
