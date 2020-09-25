package com.zhongsheng.education.service;

import com.zhongsheng.education.entiy.Bill;

import java.util.List;

public interface BillService {
    int addBillInfo(Bill bill);
    Integer selectJine(String sid);
    List<Bill> selectBill(String snum);
}
