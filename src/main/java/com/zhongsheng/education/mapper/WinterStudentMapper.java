package com.zhongsheng.education.mapper;

import com.zhongsheng.education.entiy.Student;
import com.zhongsheng.education.entiy.WinterStu;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface WinterStudentMapper {

    @Select("select * from winter_student where status != 0")
    List<WinterStu> selectAll(Integer page, Integer limit);

    @Insert("insert into winter_student(sname,sex,phone,idcard,schoolname,jiaofeijine) value (#{sname},#{sex},#{phone},#{idcard},#{schoolname},#{jiaofeijine})")
    Integer addWinterStudent(Student student);

    @Select("select * from winter_student where phone = #{phone}")
    WinterStu selectWinterStudentByPhone(String phone);

    @Update("update winter_student set status = 1 where phone = #{phone} ")
    Integer updateWinterStatus(String phone);
}
