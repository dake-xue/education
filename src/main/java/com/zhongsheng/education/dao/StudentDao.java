package com.zhongsheng.education.dao;

public class StudentDao {

    public String selectAllStudent(String keyword, Integer modules) {
        StringBuffer sql = new StringBuffer("select * from student where 1=1 ");
        //姓名
        if (modules!=null && modules.equals(1) && !"".equals(keyword) && keyword!="") {
            sql.append(" and  sname like '%" + keyword + "%'");
        }
        //班次
        else if (modules!=null && modules.equals(2) && !"".equals(keyword) && keyword!="") {
            sql.append(" and classes = '" + keyword + " '");
        }
        //年份
        else if (modules!=null && modules.equals(3) && !"".equals(keyword) && keyword!="") {
            sql.append(" and examinationtime = " + keyword + " ");
        }
        //考试科目
        else if (modules!=null && modules.equals(4) && !"".equals(keyword) && keyword!="") {
            sql.append(" and subject ='" + keyword + " '");
        }
        return sql.toString();
    }
}
