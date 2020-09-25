package com.zhongsheng.education.util;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.zhongsheng.education.entiy.Student;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataUtil {

    public static String layuiData(List list){
        PageInfo<Student> pageInfo = new PageInfo(list);
        Map< String,Object> map = new HashMap();
        //状态码 0成功  1失败
        map.put("code", 0);
        //信息
        map.put("msg", "");
        //分页总条数
        map.put("count",pageInfo.getTotal());
        //数据
        map.put("data",list);

        return JSON.toJSONString(map);
    }
}
