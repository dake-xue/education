package com.zhongsheng.education.pdf;

import com.zhongsheng.education.entiy.Bnumber;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class BillNumber {

    public static Bnumber billNumber(){
        Bnumber bnumber=new Bnumber();
        // 获得当前时间
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat format1 = new SimpleDateFormat("yyyyMMddHH");
        // 转换为字符串
        String formatDate = format.format(new Date());
        String formatDate1 = format1.format(new Date());
        // 随机生成文件编号
        int random = new Random().nextInt(10000);
        StringBuffer sr = new StringBuffer();
        sr.append(formatDate1);
        sr.append(random);

        bnumber.setFormatDate(formatDate);
        bnumber.setBillnumber(sr.toString());
        bnumber.setRandom(random);
        return bnumber;
    }
}
