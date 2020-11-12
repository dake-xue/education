package com.zhongsheng.education.mapper;

import com.zhongsheng.education.entiy.Classes;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ClassesMapper {
    @Select("select * from classes_dic where aid=#{area}")
    public List<Classes> selectClasses(Integer area);

    @Select("select * from majorcours_dic where aid=#{area}")
    public List<Classes> selectCourse(Integer area);

    @Insert("insert into classes_dic(name,classmoney,aid) values(#{name},#{classmoney},#{aid}) ")
    public Integer addScore(Integer aid, String name, Integer classmoney);

    @Insert("insert into majorcours_dic(name,aid) values(#{name},#{aid}) ")
    public Integer addCourse(Integer aid, String name);

    @Delete("delete  from classes_dic where id=#{id}")
    public Integer deleteScore(Integer id);

    @Update("update  classes_dic set name=#{name},classmoney=#{classmoney}  where id=#{id}")
    public Integer updateScore(Integer id, String name, Integer classmoney);

    @Delete("delete  from majorcours_dic where id=#{id}")
    public Integer deleteCourse(Integer id);

    @Update("update  majorcours_dic set name=#{name} where id=#{id}")
    public Integer updateCourse(Integer id, String name);

    @Select("select * from classes_dic where name=#{name}")
    public List<Classes> selectClassMoney(String name);
}
