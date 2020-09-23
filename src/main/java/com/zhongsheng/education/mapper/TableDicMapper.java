package com.zhongsheng.education.mapper;

import com.zhongsheng.education.entiy.TableDic;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface TableDicMapper {

    @Select("select * from ${tableName}")
    List<TableDic> searchAll(TableDic tableDic);

    @Select("select * from #{tableName} where id = #{id}")
    TableDic searchOne(TableDic tableDic);

    @Update("update #{tableName} set name=#{name} where id = #{id}")
    Integer update(TableDic tableDic);

    @Insert("insert into #{tableName} (name) values (#{name})")
    Integer add(TableDic tableDic);


}
