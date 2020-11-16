package com.zhongsheng.education.dao;

import com.zhongsheng.education.entiy.SearchVo;

public class StudentDao {

    public String selectAllStudent(Integer schoolid, SearchVo searchVo) {
        StringBuffer sql = new StringBuffer("select * from student where 1=1 and status <> 0");
        //今天

        if (searchVo.getStatus() != null && searchVo.getStatus() == 5) {
            sql.append(" and  TO_DAYS(signupdate) = TO_DAYS(NOW())");
        }
        //本周

        if (searchVo.getStatus() != null && searchVo.getStatus() == 6) {
            sql.append("  and YEARWEEK( DATE_FORMAT(signupdate,'%Y-%m-%d'),1) = YEARWEEK(NOW(),1)");

        }
        //本月
        if (searchVo.getStatus() != null && searchVo.getStatus() == 7) {
            sql.append("  AND DATE_FORMAT(signupdate,'%Y-%m')=DATE_FORMAT(NOW(),'%Y-%m')");

        }
        //不同省份
        if (searchVo.getArea() != null && searchVo.getArea() != 0) {
            sql.append(" and area = " + searchVo.getArea());

        }
        //姓名
        if (searchVo.getModules() != null && searchVo.getModules() == 1 && searchVo.getKeyword() != null && searchVo.getKeyword() != "") {
            sql.append(" and  sname like '%" + searchVo.getKeyword() + "%'");
        }
        //班次
        if (searchVo.getModules() != null && searchVo.getModules() == 2 && searchVo.getKeyword() != null && searchVo.getKeyword() != "") {
            sql.append(" and classes = '" + searchVo.getKeyword() + " '");
        }
        //年份
        if (searchVo.getModules() != null && searchVo.getModules() == 3 && searchVo.getKeyword() != null && searchVo.getKeyword() != "") {
            sql.append(" and examinationtime = " + searchVo.getKeyword() + " ");
        }
        //考试科目
        if (searchVo.getModules() != null && searchVo.getModules() == 4 && searchVo.getKeyword() != null && searchVo.getKeyword() != "") {
            sql.append(" and subject ='" + searchVo.getKeyword() + " '");
        }
        if (schoolid != null && !"".equals(schoolid) && schoolid!=0) {
        //身份证
        if (searchVo.getModules() != null && searchVo.getModules() == 1 && searchVo.getKeyword() != null && searchVo.getKeyword() != "") {
            sql.append(" and  idcard = '" + searchVo.getKeyword() + "'");
        }
        if (schoolid != null && !"".equals(schoolid)) {
            sql.append(" and schoolid ='" + schoolid + " '");
        }

        return sql.toString();
    }
}

