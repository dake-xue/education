package com.zhongsheng.education.service;

import com.zhongsheng.education.entiy.Bill;
import com.zhongsheng.education.entiy.Student;
import com.zhongsheng.education.entiy.Statistics;

import java.util.ArrayList;
import java.util.List;

public interface BillService {
    Integer addBillInfo(Bill bill);
    List<Bill> selectBill(String snum);
    ArrayList<String> peopleCounts(Bill bill);
    ArrayList<String> moneyCounts(Bill bill);
    List<Student> selectStudentInfo(Bill bill);
    Integer selectJiaoFeiJinE(String snum);
    Statistics people(Bill bill);
}
