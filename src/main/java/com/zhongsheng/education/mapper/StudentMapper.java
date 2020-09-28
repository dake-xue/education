package com.zhongsheng.education.mapper;

import com.zhongsheng.education.dao.StudentDao;
import com.zhongsheng.education.entiy.Area;
import com.zhongsheng.education.entiy.CampusDic;
import com.zhongsheng.education.entiy.Student;
import com.zhongsheng.education.entiy.TableDic;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StudentMapper {

    @Select("select * from student where phone=#{phone}")
    public Student selectStudentByIphone(String phone);

    @Select("select * from student where snum=#{snum}")
    public Student selectStudent(String snum);

    @Select("select * from student where sid=#{id}")
    public Student selectStudentID(Integer id);

    @SelectProvider(type = StudentDao.class, method = "selectAllStudent")
    public List<Student> selectAllStudent(String keyword, Integer modules, Integer page, Integer limit);

    @Select("select * from student")
    public List<Student> allStudent();

    @Update("update student set score=score+#{scope} where sid=#{sid}")
    public Integer addScore(Integer sid,Integer scope);

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

}
