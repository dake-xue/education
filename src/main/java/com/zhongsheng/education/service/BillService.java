package com.zhongsheng.education.service;

import com.zhongsheng.education.entiy.Bill;

import java.util.List;

public interface BillService {
    Integer addBillInfo(Bill bill);
    List<Bill> selectBill(String snum);
    Bill selectBill1(String snum);
}
