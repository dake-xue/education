package com.zhongsheng.education.dao;

public class StudentDao {

    public String selectAllStudent(String modules,String keyword) {
        StringBuffer sql = new StringBuffer("select * from student where 1=1 ");
        //班级
        if (null != modules && modules.equals("1")) {
            sql.append(" and classes like '%" + keyword + "%'");
        }
        //姓名
        if (null != modules && modules.equals("2")) {
            sql.append(" and sname like '%" + keyword + "%'");
        }
        //专业
        if (null != modules && modules.equals("3")) {
            sql.append(" and major like '%" + keyword + "%'");
        }
        return sql.toString();
    }
}
