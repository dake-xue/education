package com.zhongsheng.education.controller;

import com.alibaba.fastjson.JSON;
import com.zhongsheng.education.entiy.RoleVo;
import com.zhongsheng.education.entiy.TableDic;
import com.zhongsheng.education.service.TableDicService;
import com.zhongsheng.education.util.MyUtil;
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
        List<TableDic> tableDicList = tableDicService.searchAll(tableDic);
        return JSON.toJSONString(tableDicList);
    }
    //查询所有（返回layui表格数据模式）
    @RequestMapping("/tableAll")
    public String tableAll(TableDic tableDic){
        List<TableDic> tableDicList = tableDicService.searchAll(tableDic);
        return MyUtil.layuiData(tableDicList);
    }
    //查询单个
    @RequestMapping("/searchOne")
    public String searchOne(TableDic tableDic){
        TableDic table = tableDicService.searchOne(tableDic);
        return JSON.toJSONString(table);
    }
    //修改
    @RequestMapping("/update")
    public String update(TableDic tableDic){
        Integer i =  tableDicService.update(tableDic);
        return ""+i;
    }
    //删除
    @RequestMapping("/delete")
    public String delete(TableDic tableDic){
        Integer i = tableDicService.delete(tableDic);
        return ""+i;
    }

    //添加权限
    @RequestMapping("/addRole")
    public String addRole(RoleVo roleVo){
        Integer i = tableDicService.addRole(roleVo);
        return ""+i;
    }

    //根据校区查询学校
    @RequestMapping("/campusSelectSchool")
    @ResponseBody
    public List<TableDic> campusSelectSchool(String campus){
        TableDic tableDic=tableDicService.selectCampusId(campus);
        List<TableDic> tableDicList=tableDicService.campusSelectSchool(tableDic.getId());
        return tableDicList;
    }
}
