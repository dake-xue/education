package com.zhongsheng.education.mapper;

import com.zhongsheng.education.entiy.FamilyInfo;
import com.zhongsheng.education.entiy.SchoolInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface SchoolMapper {

    @Select("select * from schoolinfo where snum=#{snum}")
    public SchoolInfo selectSchoolInfo(String snum);

    @Insert("insert into schoolinfo(scname,screlation,scphone,snum) values(#{scname},#{screlation},#{scphone},#{snum})")
    int addSchoolInfo(SchoolInfo schoolInfo);

    @Update("update schoolinfo set scname=#{scname},screlation=#{screlation},scphone=#{scphone} where scid = #{scid}")
    public Integer updateSchool(SchoolInfo schoolInfo);
}
