package com.zhongsheng.education.mapper;

import com.zhongsheng.education.entiy.Familyinfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FamilyMapper {

    @Insert("insert into familyinfo()")
    public Integer addFamilyInfo();

    @Select("select * from where sfid=#{sid}")
    public List<Familyinfo> selectFamilyInfo(Integer sid);
}
