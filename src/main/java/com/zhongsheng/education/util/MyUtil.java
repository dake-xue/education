package com.zhongsheng.education.util;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.zhongsheng.education.entiy.Bill;
import com.zhongsheng.education.entiy.Student;
import com.zhongsheng.education.entiy.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyUtil {

    public static String layuiData(List list){
        PageInfo<Object> pageInfo = new PageInfo(list);
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

    public static String linuxGetPngName(String str) {
        //取得最后一个/的下标
        int index = str.lastIndexOf("/");
        //将字符串转为字符数组
        char[] ch = str.toCharArray();
        //根据 copyValueOf(char[] data, int offset, int count) 取得最后一个字符串
        String lastString = String.copyValueOf(ch, index + 1, ch.length - index - 1);

        return lastString;
    }

    public static String getOrderName() {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String string =  sdf.format(d).toString();
        //(数据类型)(最小值+Math.random()*(最大值-最小值+1))
        String str = (int)(0+Math.random()*(10000-0+1))+"";
        return string+str;
    }

    public static String getNowDate() {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(d);
    }

    public static void setStaAreaAndComp(User user, Bill bill){
        if(bill.getArea()==null){
            bill.setArea(user.getArea());
        }
        if(bill.getCampusid()==null){
            bill.setCampusid(user.getCampus());
        }
    }


    public static boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }


    public static String randomBeginStr(){
        String[] strs = {"08:00","09:00","10:00","11:00","12:00"};
        int random_index = (int) (Math.random()*strs.length);
        return strs[random_index];
    }

    public static String randomEndStr(){
        String[] strs = {"13:00","14:00","15:00","16:00","17:00","18:00",
                "19:00","20:00"};
        int random_index = (int) (Math.random()*strs.length);
        return strs[random_index];
    }


    public static Date getb(String str){
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");// 设置日期格式
        Date now = null;
        Date beginTime = null;
        try {
            now = df.parse(df.format(new Date()));
            beginTime = df.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return  beginTime;
    }

    public static Date gete(String str){
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");// 设置日期格式
        Date now = null;
        Date beginTime = null;
        try {
            now = df.parse(df.format(new Date()));
            beginTime = df.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return  beginTime;
    }

    public static Date get3(){
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");// 设置日期格式
        Date now = null;
        try {
            now = df.parse(df.format(new Date()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return  now;
    }


    public static boolean belongCalendar(Date nowTime, Date beginTime,
                                         Date endTime) {
        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(beginTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }

}
