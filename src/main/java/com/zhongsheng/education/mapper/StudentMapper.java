package com.zhongsheng.education.mapper;

import com.zhongsheng.education.dao.StudentDao;
import com.zhongsheng.education.entiy.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StudentMapper {

    @Select("select * from student where phone=#{phone}")
    public Student selectStudentByIphone(String phone);

    @Select("select * from student where snum=#{snum}")
    public Student selectStudent(String snum);

    @Select("select * from performance  where snum=#{snum}")
    public  List<Performance> selectPer(String snum);

    @Select("select * from bill where snum=#{snum}")
    public  List<Bill> selectJiaoFeiJinE(String snum);

    @SelectProvider(type = StudentDao.class, method = "selectAllStudent")
    public List<Student> selectAllStudent(Integer modules, String keyword,Integer status, Integer page, Integer limit);


    @Update("update student set score=score+#{scope} where snum=#{snum}")
    public Integer addScore(String snum, Integer scope);

    @Insert("insert into student(snum,campus,schoolname,sname,sex,major,phone,classes,money,signupdate,addmoneydate,paymethod,intentionmajor,publiccours,majorcours,campusmanager,campusagent,onlinenumber,offlinenumber,job,homeaddress,idcard,birthday,nation,remarks,examinationtime,admissiontime,subject,department,grade,campusid,number) values(#{snum},#{campus},#{schoolname},#{sname},#{sex},#{major},#{phone},#{classes},#{money},#{signupdate},#{addmoneydate},#{paymethod},#{intentionmajor},#{publiccours},#{majorcours},#{campusmanager},#{campusagent},#{onlinenumber},#{offlinenumber},#{job},#{homeaddress},#{idcard},#{birthday},#{nation},#{remarks},#{examinationtime},#{admissiontime},#{subject},#{department},#{grade},#{campusid},#{number})")
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

    @Update("update student set campus=#{campus},campusid=#{campusid},schoolname=#{schoolname},sname=#{sname},sex=#{sex},major=#{major},phone=#{phone}" +
            ",classes=#{classes},money=#{money},signupdate=#{signupdate},addmoneydate=#{addmoneydate},paymethod=#{paymethod}" +
            ",intentionmajor=#{intentionmajor},publiccours=#{publiccours},majorcours=#{majorcours},campusmanager=#{campusmanager}" +
            ",campusagent=#{campusagent},onlinenumber=#{onlinenumber},offlinenumber=#{offlinenumber},job=#{job}" +
            ",homeaddress=#{homeaddress},idcard=#{idcard},birthday=#{birthday},nation=#{nation}" +
            ",remarks=#{remarks},examinationtime=#{examinationtime},admissiontime=#{admissiontime}" +
            ",subject=#{subject},department=#{department},grade=#{grade} where snum=#{snum}")
    public Integer updateStudent(Student student);

    @Update("update student set score=score-#{scope} where snum=#{snum}")
    public Integer changeScore(String snum, Integer score);

    @Insert("insert into performance(snum,lunshu,classes,englishone,majorone,comment,premark,ptime,attendance) values(#{snum},#{lunshu},#{classes},#{englishone},#{majorone},#{comment},#{premark},#{ptime},#{attendance})")
    public Integer addPer(Performance performance);
    @Update("update performance set englishone=#{englishone},majorone=#{majorone},englishtwo=#{englishtwo},majortwo=#{majortwo},englishthree=#{englishthree},majorthree=#{majorthree},comment=#{comment},premark=#{premark},attendance=#{attendance} where id=#{id}")
    public Integer addPerfor(Performance performance);
    @Select("select * from performance  where id=#{id}")
    public Performance selectPerOne(Integer id);
}
