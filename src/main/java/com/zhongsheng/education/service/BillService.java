package com.zhongsheng.education.service;

import com.zhongsheng.education.entiy.Bill;

import java.util.ArrayList;
import java.util.List;

public interface BillService {
    Integer addBillInfo(Bill bill);
    List<Bill> selectBill(String snum);
    ArrayList<String> peopleCounts(Bill bill);
    ArrayList<String> moneyCounts(Bill bill);
    Integer people(Bill bill);
    Integer money(Bill bill);
}
