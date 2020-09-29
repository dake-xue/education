package com.zhongsheng.education.util;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.zhongsheng.education.entiy.Student;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyUtil {

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

    public static  String getPassWord(String str){
        return  str.substring(str.length() - 6);
    }

    public static String getPngName(String str) {
        //取得最后一个/的下标
        int index = str.lastIndexOf("\\");
        //将字符串转为字符数组
        char[] ch = str.toCharArray();
        //根据 copyValueOf(char[] data, int offset, int count) 取得最后一个字符串
        String lastString = String.copyValueOf(ch, index + 1, ch.length - index - 1);

        return lastString;
    }
}
