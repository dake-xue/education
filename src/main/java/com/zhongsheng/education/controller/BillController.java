package com.zhongsheng.education.controller;

import com.zhongsheng.education.entiy.Bill;
import com.zhongsheng.education.entiy.statistics;
import com.zhongsheng.education.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/BillController")
public class BillController {
    @Autowired
    BillService billService;

    //收入统计
    @RequestMapping(value = "/billStatistics",method = RequestMethod.POST)
    @ResponseBody
    public statistics BillStatistics(Bill bill){

        System.out.println(bill.getArea());
        statistics statistics=new statistics();

        //人数
        statistics.setPeopleCount( billService.peopleCounts(bill));
        //钱数
        statistics.setMoneyCount(billService.moneyCounts(bill));
        //今日人数
        statistics.setPeople( billService.people(bill));
        //今日钱数
        if (billService.money(bill)!=null){
            statistics.setMoney(billService.money(bill));
        }else {
            statistics.setMoney(0);
        }
        if (bill.getArea() !=null){
            System.out.println("省份"+bill.getArea()+"区"+bill.getCampusid()+"校"+bill.getSchoolid());
        }

        System.out.println(statistics.getMoneyCount()+"======"+statistics.getPeopleCount()+"======"+statistics.getPeople()+"======"+statistics.getMoney());
        return statistics;
    }


}
