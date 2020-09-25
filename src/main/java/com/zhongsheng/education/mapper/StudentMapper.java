package com.zhongsheng.education.mapper;

import com.github.pagehelper.Page;
import com.zhongsheng.education.dao.StudentDao;
import com.zhongsheng.education.entiy.Student;
import com.zhongsheng.education.entiy.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StudentMapper {

    @Select("select * from student where sid=#{uid}")
    public Student selectStudent(Integer uid);

    @SelectProvider(type = StudentDao.class, method = "selectAllStudent")
    public List<Student> selectAllStudent(String keyword, Integer modules, Integer page, Integer limit);

    @Select("select * from student")
    public List<Student> allStudent();

    @Update("update student set score=score+#{scope} where sid=#{sid}")
    public Integer addScore(Integer sid,Integer scope);

    @Insert("insert into student(sid,campus,schoolname,sname,sex,major,phone,classes,money,signupdate,addmoneydate,paymethod,intentionmajor,publiccours,majorcours,campusmanager,campusagent,onlinenumber,offlinenumber,job,homeaddress,idcard,birthday,nation,remarks,examinationtime,admissiontime,subject,department) values(#{sid},#{campus},#{schoolname},#{sname},#{sex},#{major},#{phone},#{classes},#{money},#{signupdate},#{addmoneydate},#{paymethod},#{intentionmajor},#{publiccours},#{majorcours},#{campusmanager},#{campusagent},#{onlinenumber},#{offlinenumber},#{job},#{homeaddress},#{idcard},#{birthday},#{nation},#{remarks},#{examinationtime},#{admissiontime},#{subject},#{department})")
    public Integer addStudentInfo(Student student);
}
