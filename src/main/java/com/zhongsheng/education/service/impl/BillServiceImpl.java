package com.zhongsheng.education.service.impl;

import com.zhongsheng.education.entiy.Bill;
import com.zhongsheng.education.mapper.BillMapper;
import com.zhongsheng.education.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillServiceImpl implements BillService {
    @Autowired
    BillMapper billMapper;


    @Override
    public int addBillInfo(Bill bill) { return billMapper.addBillInfo(bill);
    }

    //查询总金额
    public Integer selectJine(Integer sid){
        List<Bill> bills = billMapper.selectBill(sid);
        Integer m = 0;
        for (int i = 0; i < bills.size(); i++) {
            m += bills.get(i).getPaymentAmount();
        }
        System.out.println("---------------------------------------"+m);
        return m;
    };

    public List<Bill> selectBill(Integer sid){
        return billMapper.selectBill(sid);
    };

}
