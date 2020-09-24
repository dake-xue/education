package com.zhongsheng.education.mapper;

import com.zhongsheng.education.entiy.SchoolInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SchoolMapper {

    @Select("select * from schoolinfo where ssid=#{sid}")
    public SchoolInfo selectSchoolInfo(Integer sid);

    @Insert("insert into schoolinfo(scname,screlation,scphone,ssid) values(#{scname},#{screlation},#{scphone},#{ssid})")
    int addSchoolInfo(SchoolInfo schoolInfo);
}
