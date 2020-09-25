package com.zhongsheng.education.mapper;

import com.zhongsheng.education.entiy.FamilyInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface FamilyMapper {

    @Insert("insert into familyinfo(fname,relation,fphone,snum) values(#{fname},#{relation},#{fphone},#{snum})")
    public int addFamilyInfo(FamilyInfo familyinfo);

    @Select("select * from familyinfo where sfid=#{snum}")
    public FamilyInfo selectFamilyInfo(String snum);
}
