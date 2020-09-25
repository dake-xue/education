package com.zhongsheng.education.mapper;

import com.zhongsheng.education.dao.StudentDao;
import com.zhongsheng.education.entiy.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StudentMapper {

    @Select("select * from user where username=#{username} and password=#{password}")
    public User selectWho(Integer username,String password);

    @Select("select * from student where snum=#{snum}")
    public Student selectStudent(String snum);

    @Select("select * from student where sid=#{id}")
    public Student selectStudentID(Integer id);

    @SelectProvider(type = StudentDao.class, method = "selectAllStudent")
    public List<Student> selectAllStudent(String keyword,Integer modules);

    @Select("select * from student")
    public List<Student> allStudent();

    @Update("update student set score=score+#{scope} where sid=#{sid}")
    public Integer addScore(Integer sid,Integer scope);

    @Insert("insert into student(sid,campus,schoolname,sname,sex,major,phone,classes,money,signupdate,addmoneydate,paymethod,intentionmajor,publiccours,majorcours,campusmanager,campusagent,onlinenumber,offlinenumber,job,homeaddress,idcard,birthday,nation,remarks,examinationtime,admissiontime,subject,department,grade,campusid,number,snum) values(#{sid},#{campus},#{schoolname},#{sname},#{sex},#{major},#{phone},#{classes},#{money},#{signupdate},#{addmoneydate},#{paymethod},#{intentionmajor},#{publiccours},#{majorcours},#{campusmanager},#{campusagent},#{onlinenumber},#{offlinenumber},#{job},#{homeaddress},#{idcard},#{birthday},#{nation},#{remarks},#{examinationtime},#{admissiontime},#{subject},#{department},#{grade},#{campusid},#{number},#{snum})")
    public Integer addStudentInfo(Student student);

    @Select("select * from area")
    public List<Area> selectArea();

    @Select("select * from campus_dic where aid=#{id}")
    public List<TableDic> selectQu(Integer id);
    @Select("select * from training_school_dic where campus_id=#{id}")
    public List<TableDic> selectSchool(Integer id);

    @Select("select anum from area where aid=#{id}")
    public String selectNumber(Integer id);

    @Select("select * from campus_dic where id=#{ca}")
    public CampusDic selectCNumber(Integer ca);

    @Select("select number from student where campusid=#{id} order by number desc limit 0,1")
    public Integer selectXuHao(Integer id);
}
