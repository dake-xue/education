package com.zhongsheng.education.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zhongsheng.education.entiy.Bill;
import com.zhongsheng.education.entiy.Student;
import com.zhongsheng.education.entiy.statistics;
import com.zhongsheng.education.service.BillService;
import com.zhongsheng.education.service.StudentService;
import com.zhongsheng.education.util.LayuiData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/BillController")
public class BillController {
    @Autowired
    BillService billService;
    @Autowired
    StudentService studentService;

    //收入统计
    @RequestMapping(value = "/billStatistics",method = RequestMethod.POST)
    @ResponseBody
    public statistics BillStatistics(Bill bill){

        statistics statistics=new statistics();

        //人数
        statistics.setPeopleCount( billService.peopleCounts(bill));
        //钱数
        statistics.setMoneyCount(billService.moneyCounts(bill));
        statistics s= billService.people(bill);
        //今日人数
        statistics.setPeople(s.getPeople());
        //今日钱数
        if (s.getMoney()!=null){
            statistics.setMoney(s.getMoney());
        }else {
            statistics.setMoney(0);
        }
        if (bill.getArea() !=null){
            System.out.println("省份"+bill.getArea()+"区"+bill.getCampusid()+"校"+bill.getSchoolid());
        }

        System.out.println(statistics.getMoneyCount()+"======"+statistics.getPeopleCount()+"======"+statistics.getPeople()+"======"+statistics.getMoney());
        return statistics;
    }

    @RequestMapping("/StudentInfo")
    @ResponseBody
    public LayuiData allStudentInfo(Bill bill, @RequestParam(value = "page", required = true, defaultValue = "1") int page, @RequestParam(value = "limit", required = true, defaultValue = "6") int limit)throws Exception{
        System.out.println(page+"-----------------"+limit);
        Page pagehelper= PageHelper.startPage(page,limit);
        List<Student> studentList = billService.selectStudentInfo(bill);
        //查询已交学费
        for (Student s:studentList){
            s.setJiaofeijine(billService.selectJiaoFeiJinE(s.getSnum()));
            Integer i=s.getMoney();
            Integer q=s.getJiaofeijine();
            Integer c=i-q;
            s.setWeijiaokuan(c);
        }
        LayuiData layuiData=new LayuiData();
        layuiData.setCode(0);
        layuiData.setMsg("");
        layuiData.setCount((int)pagehelper.getTotal());
        layuiData.setData(studentList);
        return  layuiData;
    }
}
