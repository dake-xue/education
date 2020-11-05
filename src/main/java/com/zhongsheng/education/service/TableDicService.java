package com.zhongsheng.education.service;


import com.zhongsheng.education.entiy.RoleVo;
import com.zhongsheng.education.entiy.TableDic;


import java.util.List;

public interface TableDicService{

    List<TableDic> searchAll(TableDic tableDic);

    TableDic searchOne(TableDic tableDic);

    Integer update(TableDic tableDic);

    Integer add(TableDic tableDic);

    Integer delete(TableDic tableDic);

    TableDic selectCampusId(String campus);

    List<TableDic> campusSelectSchool(Integer id);

    Integer addRole(RoleVo roleVo);

    TableDic searchOneByName(TableDic tableDic);
}
