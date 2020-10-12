package com.zhongsheng.education.controller;

import com.alibaba.fastjson.JSON;
import com.zhongsheng.education.entiy.TableDic;
import com.zhongsheng.education.service.TableDicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/table")
public class TableDicController {

    @Autowired
    private TableDicService tableDicService;

    //查询所有
    @RequestMapping("/all")
    public String all(TableDic tableDic){
        System.out.println(tableDic);
        List<TableDic> tableDicList = tableDicService.searchAll(tableDic);
        return JSON.toJSONString(tableDicList);
    }

    //根据校区查询学校
    @RequestMapping("/campusSelectSchool")
    @ResponseBody
    public List<TableDic> campusSelectSchool(String campus){
        System.out.println("campus--------------"+campus);
        TableDic tableDic=tableDicService.selectCampusId(campus);
        List<TableDic> tableDicList=tableDicService.campusSelectSchool(tableDic.getId());
        return tableDicList;
    }


    //添加省份
    //添加校区
    //添加学校
}
