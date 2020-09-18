package com.zhongsheng.education.mapper;

import com.zhongsheng.education.dao.StudentDao;
import com.zhongsheng.education.entiy.Student;
import com.zhongsheng.education.entiy.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

@Mapper
public interface StudentMapper {

    @Select("select * from user where username=${username} and password=${password}")
    public User selectWho(Integer username,String password);

    @Select("select * from student where sid=#{uid}")
    public Student selectStudent(Integer uid);

    @SelectProvider(type = StudentDao.class, method = "selectAllStudent")
    public List<Student> selectAllStudent(String modules,String keyword);

    @Select("update student set score=score+#{scope} where sid=#{sid}")
    public Integer addScore(Integer sid,Integer scope);
}
