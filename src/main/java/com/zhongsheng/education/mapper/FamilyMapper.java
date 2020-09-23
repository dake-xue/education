package com.zhongsheng.education.mapper;

import com.zhongsheng.education.entiy.FamilyInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FamilyMapper {

    @Insert("insert into familyinfo(fname,relation,fphone,sfid) values(#{fname},#{relation},#{fphone},#{sfid})")
    public int addFamilyInfo(FamilyInfo familyinfo);

    @Select("select * from where sfid=#{sid}")
    public List<FamilyInfo> selectFamilyInfo(Integer sid);
}
