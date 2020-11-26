package com.zhongsheng.education.dao;

import com.zhongsheng.education.entiy.Bill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BillDao {
    Logger logger = LoggerFactory.getLogger(BillDao.class);
    public String peopleCounts(Bill bill) {
        StringBuffer sql = new StringBuffer("SELECT COUNT(*) val,DATE_FORMAT(signupdate,'%m') month FROM student where 1=1 and status !=0 ");
        //省
        if (bill.getArea()!= null && bill.getArea() !=0) {

            sql.append(" and area = '" + bill.getArea() + " '");
        }
        //区
        if (bill.getCampusid() != null && bill.getCampusid() !=0) {
            sql.append(" and campusid = " + bill.getCampusid() + " ");
        }
        //校
        if (bill.getSchoolid() != null && bill.getSchoolid() !=0) {
            sql.append(" and schoolid ='" + bill.getSchoolid() + " '");
        }
        sql.append(" GROUP BY DATE_FORMAT(signupdate,'%Y%m')ORDER BY DATE_FORMAT(signupdate,'%Y%m')");
        return sql.toString();
    }

    public String moneyCounts(Bill bill) {
        StringBuffer sql = new StringBuffer("SELECT SUM(paymentAmount) val,DATE_FORMAT(intotime,'%m') MONTH FROM bill where 1=1 ");
        //省
        if (bill.getArea()!= null && bill.getArea() !=0) {
            sql.append(" and area = '" + bill.getArea() + " '");
        }
        //区
        if (bill.getCampusid() != null && bill.getCampusid() !=0) {
            sql.append(" and campusid = " + bill.getCampusid() + " ");
        }
        //校
        if (bill.getSchoolid() != null && bill.getSchoolid() !=0) {
            sql.append(" and schoolid ='" + bill.getSchoolid() + " '");
        }
        sql.append(" GROUP BY DATE_FORMAT(intotime,'%Y%m')ORDER BY DATE_FORMAT(intotime,'%Y%m')");
      /*  //今天
        if(status!=null && status==5){
            sql.append(" and  TO_DAYS(intotime) = TO_DAYS(NOW())");
        }
        //本周
        if(status!=null && status==6){
            sql.append("  and YEARWEEK( DATE_FORMAT(intotime,'%Y-%m-%d'),1) = YEARWEEK(NOW(),1)");

        }
        //本月
        if(status!=null && status==7){
            sql.append("  AND DATE_FORMAT(intotime,'%Y-%m')=DATE_FORMAT(NOW(),'%Y-%m')");

        }
      */

        return sql.toString();
    }

    //今日新增学生
    public String people(Bill bill) {
        StringBuffer sql = new StringBuffer("SELECT COUNT(*) as people FROM student  WHERE  status !=0 and 1=1 ");

            sql.append(" and  TO_DAYS(signupdate) = TO_DAYS(NOW())");
        //省
        if (bill.getArea()!= null && bill.getArea() !=0) {
            sql.append(" and area = '" + bill.getArea() + " '");
        }
        //区
        if (bill.getCampusid() != null && bill.getCampusid() !=0) {
            sql.append(" and campusid = " + bill.getCampusid() + " ");
        }
        //校
        if (bill.getSchoolid() != null && bill.getSchoolid() !=0) {
            sql.append(" and schoolid ='" + bill.getSchoolid() + " '");
        }

        return sql.toString();
    }
    //今日收入
   public String money(Bill bill) {
        StringBuffer sql = new StringBuffer("select sum(paymentAmount) from bill where 1=1 ");
            sql.append(" and  TO_DAYS(intotime) = TO_DAYS(NOW())");
        //省
        if (bill.getArea()!= null && bill.getArea() !=0) {
            sql.append(" and area = '" + bill.getArea() + " '");
        }
        //区
        if (bill.getCampusid() != null && bill.getCampusid() !=0) {
            sql.append(" and campusid = " + bill.getCampusid() + " ");
        }
        //校
        if (bill.getSchoolid() != null && bill.getSchoolid() !=0) {
            sql.append(" and schoolid ='" + bill.getSchoolid() + " '");
        }
        return sql.toString();
    }

    public String selectStudentInfo(Bill bill){
        StringBuffer sql = new StringBuffer("select s.*,b.billnumber from student s,bill b where status <> 0 and s.snum=b.snum");
        sql.append(" and  TO_DAYS(s.signupdate) = TO_DAYS(NOW())");
        //省
        if (bill.getArea()!= null && bill.getArea() !=0) {
            sql.append(" and s.area = '" + bill.getArea() + " '");
        }
        //区
        if (bill.getCampusid() != null && bill.getCampusid() !=0) {
            sql.append(" and s.campusid = " + bill.getCampusid() + " ");
        }
        //校
        if (bill.getSchoolid() != null && bill.getSchoolid() !=0) {
            sql.append(" and s.schoolid ='" + bill.getSchoolid() + " '");
        }
        return sql.toString();

    };
}
