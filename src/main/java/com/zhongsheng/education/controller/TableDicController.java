package com.zhongsheng.education.controller;

import com.alibaba.fastjson.JSON;
import com.zhongsheng.education.entiy.TableDic;
import com.zhongsheng.education.service.TableDicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/table")
public class TableDicController {

    @Autowired
    private TableDicService tableDicService;

    @RequestMapping("/all")
    public String all(TableDic tableDic){
        List<TableDic> tableDicList = tableDicService.searchAll(tableDic);
        return JSON.toJSONString(tableDicList);
    }
}
