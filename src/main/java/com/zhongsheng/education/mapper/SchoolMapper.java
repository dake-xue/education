package com.zhongsheng.education.mapper;

import com.zhongsheng.education.entiy.SchoolInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SchoolMapper {

    @Select("select * from schoolinfo where ssid=#{snum}")
    public SchoolInfo selectSchoolInfo(String snum);

    @Insert("insert into schoolinfo(scname,screlation,scphone,snum) values(#{scname},#{screlation},#{scphone},#{snum})")
    int addSchoolInfo(SchoolInfo schoolInfo);
}
