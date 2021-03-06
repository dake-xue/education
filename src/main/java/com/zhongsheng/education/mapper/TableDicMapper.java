package com.zhongsheng.education.mapper;

import com.zhongsheng.education.entiy.TableDic;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TableDicMapper {

    @Select("select * from ${tableName}")
    List<TableDic> searchAll(TableDic tableDic);

    @Select("select * from ${tableName} where id = #{id}")
    TableDic searchOne(TableDic tableDic);

    @Select("select * from ${tableName} where name = #{name}")
    TableDic searchOneByName(TableDic tableDic);
    @Select("select * from ${tableName} where name = #{name} and campus_id=#{campus_id}")
    TableDic searchSchoolName(TableDic tableDic);

    @Update("update ${tableName} set name=#{name} where id = #{id}")
    Integer update(TableDic tableDic);

    @Insert("insert into ${tableName} (name) values (#{name})")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    Integer add(TableDic tableDic);

    @Delete("delete from ${tableName} WHERE id = #{id}")
    Integer delete(TableDic tableDic);

    @Select("select * from campus_dic where name=#{campus}")
    TableDic selectCampusId(String campus);

    @Select("select * from training_school_dic where campus_id=#{id}")
    List<TableDic> campusSelectSchool(Integer id);


}
