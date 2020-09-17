package com.zhongsheng.education.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TeacherMapper {
    @Select("select tname from teacher where id=#{uid}")
    public String selectTeacherName(Integer uid);
}
