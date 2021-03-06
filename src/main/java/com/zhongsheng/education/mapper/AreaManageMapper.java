package com.zhongsheng.education.mapper;

import com.zhongsheng.education.dao.TableDao;
import com.zhongsheng.education.entiy.Area;
import com.zhongsheng.education.entiy.CampusDic;
import com.zhongsheng.education.entiy.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AreaManageMapper {

    @Insert("insert into area(aname) values(#{name}) ")
    public Integer addArea(String name);

    @Update("update  area set anum=#{anum} where aid=#{id} ")
    public Integer updateArea(Integer id, String anum);

    @Select("select * from area where aname=#{name}")
    public Area selectArea(String name);

    @SelectProvider(type = TableDao.class, method = "selectAreaByAid")
    List<Area> selectAreaByAid(Integer aid);

    @Select("select * from area")
    List<Area> allArea();

    @Select("SELECT cnum FROM campus_dic WHERE aid=#{id} ORDER BY cnum DESC LIMIT 0,1")
    public Integer selectCnum(Integer id);

    @Insert("insert into campus_dic(aid,name,cnum) values(#{aid},#{name},#{num}) ")
    public Integer addCampus(Integer aid, String num, String name);

    @Insert("insert into training_school_dic(name,campus_id) values(#{name},#{id}) ")
    public Integer addSchool(Integer id, String name);

    @Update("update  area set aname=#{aname} where aid=#{aid} ")
    public Integer toUpdateArea(Area area);

    @Update("update  campus_dic set name=#{name} where id=#{id} ")
    public Integer toUpdateCampus(CampusDic campusDic);

    @Update("update  training_school_dic set name=#{name} where id=#{id} ")
    public Integer toUpdateSchool(CampusDic campusDic);

    @Select("select * from student where schoolid=#{schoolid} and status !=0")
    public List<Student> selectStudent(Integer schoolid);
}
