package com.zhongsheng.education.service.impl;

import com.zhongsheng.education.entiy.TableDic;
import com.zhongsheng.education.mapper.TableDicMapper;
import com.zhongsheng.education.service.TableDicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TableDicServiceImpl implements TableDicService {

    @Autowired
    private TableDicMapper tableDicMapper;

    @Override
    public List<TableDic> searchAll(TableDic tableDic) {
        return tableDicMapper.searchAll(tableDic);
    }

    @Override
    public TableDic searchOne(TableDic tableDic) {
        return tableDicMapper.searchOne(tableDic);
    }

    @Override
    public Integer update(TableDic tableDic) {
        return tableDicMapper.update(tableDic);
    }

    @Override
    public Integer add(TableDic tableDic) {
        return tableDicMapper.add(tableDic);
    }
}
