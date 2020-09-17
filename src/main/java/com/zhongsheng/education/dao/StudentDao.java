package com.zhongsheng.education.dao;

public class StudentDao {

    public String selectAllStudent(String classes,String sname,String major) {
        StringBuffer sql = new StringBuffer("select * from student where 1=1 ");
        //班级
        if (classes != null && " ".equals(classes)) {
            sql.append(" and classes like '%" + classes + "%'");
        }
        //姓名
        if (sname != null && " ".equals(sname)) {
            sql.append(" and snamelike '%" + sname + "%'");
        }
        //专业
        if (major != null && " ".equals(major)) {
            sql.append(" and major like '%" + major + "%'");
        }
        return sql.toString();
    }
}
