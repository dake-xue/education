package com.zhongsheng.education.service.impl;

import com.zhongsheng.education.entiy.Bill;
import com.zhongsheng.education.mapper.BillMapper;
import com.zhongsheng.education.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillServiceImpl implements BillService {
    @Autowired
    BillMapper billMapper;


    @Override
    public int addBillInfo(Bill bill) {
        return billMapper.addBillInfo(bill);
    }

}
