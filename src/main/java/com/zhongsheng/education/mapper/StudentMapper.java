package com.zhongsheng.education.mapper;

import com.zhongsheng.education.entiy.Student;
import com.zhongsheng.education.entiy.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface StudentMapper {

    @Select("select * from user where username=${username} and password=${password}")
    public User selectWho(Integer username,String password);

    @Select("select tname from teacher where id=#{uid}")
    public String selectTeacher(Integer uid);

    @Select("select * from student where sid=#{uid}")
    public Student selectStudent(Integer uid);
}
