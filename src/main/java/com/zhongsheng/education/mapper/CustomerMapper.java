package com.zhongsheng.education.mapper;

import com.zhongsheng.education.entiy.*;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface CustomerMapper {

    @Insert("insert into signup(name,phone,qq,schoolname,major,period) values(#{name},#{phone},#{qq},#{schoolname},#{major},#{period})")
    public Integer addSignUp(Customer customer);

    @Select("select * from signup order by id desc")
    public List<Customer> selectSignUp();

    @Update("update  signup set remark=#{remark} where id=#{id}")
    public Integer updateSignUp(Integer id, String remark);

    @Update("update  signup set status=0 where id=#{id}")
    public Integer updateStatus(Integer id);

    @Select("select * from juniorcollege")
    public List<JuniorCollege> selectJuniorCollege();

    @Select("select r.*,s.* from regularcollege  r ,subject  s where r.subject=s.sid and r.junior=#{junior}")
    public List<RegularCollege> selectRegularCollegeId(Integer junior);

    @Update("update juniorcollege set name=#{name} where id=#{id} ")
    public Integer UpdateMajor(JuniorCollege juniorCollege);

    @Insert("insert into juniorcollege(name) values(#{name})")
    public Integer addMajor(String name);

    @Select("select * from regularcollege where id=#{id}")
    public RegularCollege selectSchool(Integer id);

    @Update("update regularcollege set school=#{c} where id=#{id}")
    public Integer setSchool(String c, Integer id);

    @Select("select * from subject")
    public List<subject> selectSubject();

    @Insert("insert into subject(subjectname) values(#{subjectname})")
    public Integer addSubject(String subjectname);

    @Update("update subject set subjectname=#{subjectname} where sid=#{sid}")
    public Integer UpdateSubject(subject subject);

    @Delete("delete from subject where sid=#{sid}")
    public Integer deleteSubject(Integer sid);

    @Select("SELECT s.* FROM school s WHERE s.id IN (${id})")
    public List<School> allSchool(String id);


    @Select("select * from yeardata where sid=#{sid}")
    public List<YearData> selectYearDataId(Integer sid);

    @Insert("insert into yeardata(year,data1,data2,data3,sid) values(#{year},#{data1},#{data2},#{data3},#{sid})")
    public Integer addYearData(YearData yearData);

    @Delete("delete from yeardata where id=#{id}")
    public Integer deleteYearData(Integer id);

    @Delete("delete from school where id=#{id}")
    public   Integer  deleteSchool(Integer id);

    @Delete("delete from juniorcollege where id=#{id}")
    public   Integer  deleteMajor(Integer id);

    @Delete("delete from regularcollege where id=#{id}")
    public   Integer  deleteRegular(Integer id);

    @Select("select * from school")
    public   List<School>  allSchoolInfo();

    @Insert("insert into school(name,status) values(#{name},#{status})")
    public   Integer addSchoolInfo(School school);

    @Insert("insert into regularcollege(name,junior,subject) values(#{name},#{junior},#{subject})")
    public Integer  addRegular(RegularCollege regular);

    @Select("select * from regularcollege where subject=#{sid} and junior=(select id from juniorcollege where name=#{name})")
    public List<RegularCollege> xSelectJunior(Integer sid,String name);

    @Select("SELECT s.* FROM regularcollege r,SUBJECT s WHERE r.subject=s.sid AND junior=(SELECT id FROM juniorcollege WHERE NAME=#{name}) GROUP BY r.subject")
    public List<subject>  xSelectJunior1(String name);


    @Select("SELECT * FROM school WHERE  id in (${id}) and status=#{status}")
    public List<School>  xSelectSchool(String id,String status);


    @Select("select * from yeardata where sid=#{sid}")
    public ArrayList<YearData> selectYearDataId1(Integer sid);
}
