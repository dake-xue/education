package com.zhongsheng.education.service;


import com.zhongsheng.education.entiy.TableDic;


import java.util.List;

public interface TableDicService{

    List<TableDic> searchAll(TableDic tableDic);

    TableDic searchOne(TableDic tableDic);

    Integer update(TableDic tableDic);

    Integer add(TableDic tableDic);
}
