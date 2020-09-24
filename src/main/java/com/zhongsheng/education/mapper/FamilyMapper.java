package com.zhongsheng.education.mapper;

import com.zhongsheng.education.entiy.FamilyInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface FamilyMapper {

    @Insert("insert into familyinfo(fname,relation,fphone,sfid) values(#{fname},#{relation},#{fphone},#{sfid})")
    public int addFamilyInfo(FamilyInfo familyinfo);

    @Select("select * from familyinfo where sfid=#{sid}")
    public FamilyInfo selectFamilyInfo(Integer sid);
}
