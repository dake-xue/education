package com.zhongsheng.education.mapper;

import com.zhongsheng.education.entiy.Schoolinfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SchoolMapper {

    @Select("select * from schoolinfo where ssid=#{sid}")
    public List<Schoolinfo> selectInfoInfo(Integer sid);
}
