package com.zhongsheng.education.mapper;

import com.zhongsheng.education.entiy.FamilyInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface FamilyMapper {

    @Insert("insert into familyinfo(fname,relation,fphone,snum) values(#{fname},#{relation},#{fphone},#{snum})")
    public int addFamilyInfo(FamilyInfo familyinfo);

    @Select("select * from familyinfo where snum=#{snum}")
    public FamilyInfo selectFamilyInfo(String snum);

    @Update("update familyinfo set fname=#{fname},relation=#{relation},fphone=#{fphone} where fid = #{fid}")
    public Integer updateFamily(FamilyInfo familyInfo);
}
