package com.zhongsheng.education.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zhongsheng.education.entiy.Bill;
import com.zhongsheng.education.entiy.Statistics;
import com.zhongsheng.education.entiy.Student;
import com.zhongsheng.education.entiy.User;
import com.zhongsheng.education.service.BillService;
import com.zhongsheng.education.service.StudentService;
import com.zhongsheng.education.util.LayuiData;
import com.zhongsheng.education.util.MyUtil;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/BillController")
public class BillController {
    @Autowired
    BillService billService;
    @Autowired
    StudentService studentService;

    /*@RequestMapping("/toAllBill")
    public String BillStatistics(Model model){
        //取出session中的user
        User loginUser = (User) SecurityUtils.getSubject().getPrincipal();
        model.addAttribute("area",loginUser.getArea());
        return "statistics";
    }*/

    //收入统计
    @RequestMapping(value = "/billStatistics",method = RequestMethod.POST)
    @ResponseBody
    public Statistics BillStatistics(Bill bill){
        //取出session中的user
        User loginUser = (User) SecurityUtils.getSubject().getPrincipal();
        //area，comp设置默认值
        MyUtil.setStaAreaAndComp(loginUser,bill);
        Statistics statistics=new Statistics();
        //人数
        statistics.setPeopleCount( billService.peopleCounts(bill));
        //钱数
        statistics.setMoneyCount(billService.moneyCounts(bill));
        Statistics s= billService.people(bill);
        //今日人数
        Statistics s = billService.people(bill);
        statistics.setPeople(s.getPeople());
        //今日钱数
        Statistics m = billService.money(bill);
        if (null != m ) {
            statistics.setMoney(m.getMoney());
        } else {
            statistics.setMoney(0);

        return statistics;
    }

    @RequestMapping("/StudentInfo")
    @ResponseBody
    public LayuiData allStudentInfo(Bill bill, @RequestParam(value = "page", required = true, defaultValue = "1") int page, @RequestParam(value = "limit", required = true, defaultValue = "6") int limit)throws Exception{
        Page pagehelper= PageHelper.startPage(page,limit);
        //取出session中的user
        User loginUser = (User) SecurityUtils.getSubject().getPrincipal();
        //area，comp设置默认值
        MyUtil.setStaAreaAndComp(loginUser,bill);
        List<Student> studentList = billService.selectStudentInfo(bill);
        //查询已交学费

        for(Student stu: studentList){
            stu.setJiaofeijine(billService.selectJiaoFeiJinE(stu.getSnum()));
            BigDecimal money = new BigDecimal(stu.getMoney());
            BigDecimal jfje = new BigDecimal(stu.getJiaofeijine());
            BigDecimal wjje = money.subtract(jfje);
            stu.setWeijiaokuan(wjje.toString());
        }
        LayuiData layuiData=new LayuiData();
        layuiData.setCode(0);
        layuiData.setMsg("");
        layuiData.setCount((int)pagehelper.getTotal());
        layuiData.setData(studentList);
        return  layuiData;
    }
}
