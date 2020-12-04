package com.zhongsheng.education.mapper;

import com.zhongsheng.education.entiy.*;
import org.apache.ibatis.annotations.*;

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
    public String setSchool(String c, Integer id);

    @Select("select * from subject")
    public List<subject> selectSubject();

    @Insert("insert into subject(subjectname) values(#{subjectname})")
    public Integer addSubject(String subjectname);

    @Update("update subject set subjectname=#{subjectname} where sid=#{sid}")
    public Integer UpdateSubject(subject subject);

    @Delete("delete from subject where sid=#{sid}")
    public Integer deleteSubject(Integer sid);

    @Select("select s.* from school s where s.id in ( select r.school from regularcollege r where r.id=#{id})")
    public List<School> allSchool(Integer id);

}
