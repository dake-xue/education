package com.zhongsheng.education.dao;

import com.zhongsheng.education.entiy.Bill;

public class TableDao {
    //今日新增学生
    public String selectAreaByAid(Integer aid) {
        StringBuffer sql = new StringBuffer("select * from area");

        //省
        if (aid!=0) {
            sql.append(" where aid = " + aid + " ");
        }

        return sql.toString();
    }
}
