package com.zhongsheng.education.mapper;

import com.zhongsheng.education.entiy.PermissionInfo;
import com.zhongsheng.education.entiy.User;
import com.zhongsheng.education.entiy.UserVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.HashSet;
import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select u.uid,u.name,u.username,u.password,u.roleid,u.status,r.name rolename ,a.aname from user u LEFT JOIN role r on u.roleid = r.id LEFT JOIN area a on a.aid = u.area where u.roleid <> 1")
    List<UserVo> selectAllUser();

    //添加用户
    @Insert("insert into user(name,username,password,roleid,area,campus) values(#{name},#{username},#{password},#{roleid},#{area},#{campus})")
    public Integer addUser(User user);

    //修改密码
    @Update("update user set password = #{password} where uid = #{uid} ")
    Integer updateUserPassword(User user);

    //修改状态
    @Update("update user set status = #{status} where uid = #{uid} ")
    Integer updateUserStatus(User user);

    //查询新用户id
    @Select("select * from user whoid=#{whoid} order by uid desc limit 0,1")
    public User selectUserId(Integer whoid);

    @Select("select * from user where username=#{username} and password=#{password}")
    public User selectWho(User user);

    @Select("select r.name from role r" +
            " LEFT JOIN user u on u.roleid = r.id" +
            " where u.username = #{username}")
    HashSet<String> getRoles(String username);

    @Select("select * from user where username=#{username} and status <> 1")
    User getByUsername(String username);

}
