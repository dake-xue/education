package com.zhongsheng.education.dao;

public class StudentDao {

    public String selectAllStudent(Integer modules, String keyword, Integer status, Integer page, Integer limit) {
        StringBuffer sql = new StringBuffer("select * from student where 1=1 and status <> 0");
        //今天
        if (status != null && status == 5) {
            sql.append(" and  TO_DAYS(signupdate) = TO_DAYS(NOW())");
        }
        //本周
        if (status != null && status == 6) {
            sql.append("  and YEARWEEK( DATE_FORMAT(signupdate,'%Y-%m-%d'),1) = YEARWEEK(NOW(),1)");

        }
        //本月
        if (status != null && status == 7) {
            sql.append("  AND DATE_FORMAT(signupdate,'%Y-%m')=DATE_FORMAT(NOW(),'%Y-%m')");

        }

        //姓名
        if (modules != null && modules == 1 && keyword != null && keyword != "") {
            sql.append(" and  sname like '%" + keyword + "%'");
            }
            //班次
            if (modules != null && modules == 2 && keyword != null && keyword != "") {
                sql.append(" and classes = '" + keyword + " '");
            }
            //年份
            if (modules != null && modules == 3 && keyword != null && keyword != "") {
                sql.append(" and examinationtime = " + keyword + " ");
            }
            //考试科目
            if (modules != null && modules == 4 && keyword != null && keyword != "") {
                sql.append(" and subject ='" + keyword + " '");
            }


            return sql.toString();
        }
    }

