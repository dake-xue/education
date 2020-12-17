package com.zhongsheng.education.mapper;

import com.zhongsheng.education.entiy.Student;
import com.zhongsheng.education.entiy.WinterStu;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface WinterStudentMapper {

    @Select("select * from winter_student where status != 0 and sname like '%${sname}%'")
    List<WinterStu> selectAll(String sname);

    @Insert("insert into winter_student(sname,sex,phone,idcard,schoolname,jiaofeijine) value (#{sname},#{sex},#{phone},#{idcard},#{schoolname},#{jiaofeijine})")
    Integer addWinterStudent(Student student);
    @Insert("insert into winter_student(sname,sex,phone,idcard,schoolname,jiaofeijine,status) value (#{sname},#{sex},#{phone},#{idcard},#{schoolname},#{jiaofeijine},1)")
    Integer addWinterStudent1(Student student);

    @Select("select * from winter_student where phone = #{phone}")
    WinterStu selectWinterStudentByPhone(String phone);

    @Update("update winter_student set status = 1 where phone = #{phone} ")
    Integer updateWinterStatus(String phone);


}
