package com.zhongsheng.education.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zhongsheng.education.entiy.*;
import com.zhongsheng.education.service.CustomerService;
import com.zhongsheng.education.util.LayuiData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @ResponseBody
    @GetMapping("/openid")
    public Token openid(String code, String appid, String secret, String grant_type) {
        System.out.println("code:" + code + "appId:" + appid + "secret:" + secret);

        Token token = Httpclient.doGetTestWayOne(code, appid, secret, grant_type);

        return token;
    }

    //录入报名信息
    @CrossOrigin
    @GetMapping("/addSignUp")
    @ResponseBody
    public Integer addSignUp(Customer customer) {
        Integer i = customerService.addSignUp(customer);
        return i;
    }

    //查看报名信息
    @RequestMapping("/selectSignUp")
    @ResponseBody
    public LayuiData selectSignUp(@RequestParam(value = "page", required = true, defaultValue = "1") int page, @RequestParam(value = "limit", required = true, defaultValue = "10") int limit, Integer area) {
        Page pagehelper = PageHelper.startPage(page, limit);
        List<Customer> customerList = customerService.selectSignUp();

        LayuiData layuiData = new LayuiData();
        layuiData.setCode(0);
        layuiData.setMsg("");
        layuiData.setCount((int) pagehelper.getTotal());
        layuiData.setData(customerList);
        return layuiData;
    }

    //新增备注
    @RequestMapping("/updateSignUp")
    @ResponseBody
    public Integer updateSignUp(Integer id, String remark) {
        Integer integer = customerService.updateSignUp(id, remark);
        return integer;
    }

    //修改状态为已联系
    @RequestMapping("/updateStatus")
    @ResponseBody
    public Integer updateStatus(Integer id) {
        Integer integer = customerService.updateStatus(id);
        return integer;
    }

    //查询所有专科专业
    @RequestMapping("/selectJuniorCollege")
    @ResponseBody
    public LayuiData selectJuniorCollege(@RequestParam(value = "page", required = true, defaultValue = "1") int page, @RequestParam(value = "limit", required = true, defaultValue = "10") int limit, Integer area) {
        Page pagehelper = PageHelper.startPage(page, limit);
        List<JuniorCollege> juniorCollegeList = customerService.selectJuniorCollege();
        for (JuniorCollege j : juniorCollegeList) {
            List<RegularCollege> regularColleges = customerService.selectRegularCollegeId(j.getId());
            j.setCount(regularColleges.size());
        }

        LayuiData layuiData = new LayuiData();
        layuiData.setCode(0);
        layuiData.setMsg("");
        layuiData.setCount((int) pagehelper.getTotal());
        layuiData.setData(juniorCollegeList);
        return layuiData;
    }

    //修改专科专业
    @RequestMapping("/UpdateMajor")
    @ResponseBody
    public String UpdateMajor(JuniorCollege juniorCollege) throws Exception {
        Integer integer = customerService.UpdateMajor(juniorCollege);
        if (integer == 1) {
            return "yes";
        } else {
            return "no";
        }
    }

    //添加专科专业
    @RequestMapping("/addMajor")
    @ResponseBody
    public String addMajor(String name) throws Exception {
        Integer integer = customerService.addMajor(name);
        if (integer == 1) {
            return "yes";
        } else {
            return "no";
        }
    }
    //删除专科
    @RequestMapping("/deleteMajor")
    @ResponseBody
    public String deleteMajor(Integer id) throws Exception {
        Integer integer = customerService.deleteMajor(id);
        if (integer == 1) {
            return "yes";
        } else {
            return "no";
        }
    }

    //查询本科专业
    @RequestMapping("/selectRegularCollege")
    public String selectArea(Integer id, Model model) {
        model.addAttribute("id", id);
        return "allRegular";
    }
    @RequestMapping("/allregular")
    @ResponseBody
    public LayuiData allregular(@RequestParam(value = "page", required = true, defaultValue = "1") int page, @RequestParam(value = "limit", required = true, defaultValue = "10") int limit, Integer junior) {
        Page pagehelper = PageHelper.startPage(page, limit);
        List<RegularCollege> regularColleges = customerService.selectRegularCollegeId(junior);
        for (RegularCollege j : regularColleges) {
            //学校字段
            String school=j.getSchool();
            //判断学校不为空
            if (school != null && !school.equals("")) {
               Integer a=school.split(",").length;
                j.setCount(a);
            }else{
                j.setCount(0);
            }

        }
        LayuiData layuiData = new LayuiData();
        layuiData.setCode(0);
        layuiData.setMsg("");
        layuiData.setCount((int) pagehelper.getTotal());
        layuiData.setData(regularColleges);
        return layuiData;
    }
    //删除本科
    @RequestMapping("/deleteRegular")
    @ResponseBody
    public String deleteRegular(Integer id) throws Exception {
        Integer integer = customerService.deleteRegular(id);
        if (integer == 1) {
            return "yes";
        } else {
            return "no";
        }
    }
    //添加本科
    @RequestMapping("/addRegular")
    @ResponseBody
    public String addRegular(RegularCollege regular) throws Exception {
        Integer integer = customerService.addRegular(regular);
        if (integer == 1) {
            return "yes";
        } else {
            return "no";
        }
    }



    //添加学校
    @ResponseBody
    @RequestMapping("/addSchool")
    public String addSchool(Integer id,Integer school) {
            //查出当前本科的学校
            RegularCollege regularColleges = customerService.selectSchool(id);
            //新增学校添加进去
         Integer integer=customerService.setSchool(school, regularColleges.getSchool(), id);
        if (integer == 1) {
            return "yes";
        } else {
            return "no";
        }
    }

    //查询所有考试科目
    @RequestMapping("/allSubject")
    @ResponseBody
    public LayuiData allSubject(@RequestParam(value = "page", required = true, defaultValue = "1") int page, @RequestParam(value = "limit", required = true, defaultValue = "10") int limit) {
        Page pagehelper = PageHelper.startPage(page, limit);
        List<subject> subjects = customerService.selectSubject();

        LayuiData layuiData = new LayuiData();
        layuiData.setCode(0);
        layuiData.setMsg("");
        layuiData.setCount((int) pagehelper.getTotal());
        layuiData.setData(subjects);
        return layuiData;
    }
    //查询考试科目
    @RequestMapping("/selectSubject")
    @ResponseBody
    public List<subject> selectSubject() {
        List<subject> areaList =  customerService.selectSubject();
        return areaList;
    }


    //添加考试科目
    @RequestMapping("/addSubject")
    @ResponseBody
    public String addSubject(String subjectname) throws Exception {
        Integer integer = customerService.addSubject(subjectname);
        if (integer == 1) {
            return "yes";
        } else {
            return "no";
        }
    }

    //修改考试科目
    @RequestMapping("/updateSubject")
    @ResponseBody
    public String UpdateSubject(subject subject) throws Exception {
        Integer integer = customerService.UpdateSubject(subject);
        if (integer == 1) {
            return "yes";
        } else {
            return "no";
        }
    }

    //删除考试科目
    @RequestMapping("/deleteSubject")
    @ResponseBody
    public String deleteSubject(Integer sid) throws Exception {
        Integer integer = customerService.deleteSubject(sid);
        if (integer == 1) {
            return "yes";
        } else {
            return "no";
        }
    }


    //查询学校
    @RequestMapping("/selectSchool")
    public String selectSchool(Integer id,Model model) {
        model.addAttribute("id",id);
        return "allSchool";
    }
    @RequestMapping("/allSchool")
    @ResponseBody
    public LayuiData allSchool(@RequestParam(value = "page", required = true, defaultValue = "1") int page, @RequestParam(value = "limit", required = true, defaultValue = "10") int limit, Integer id) {

        RegularCollege r=customerService.selectSchool(id);
        Page pagehelper = PageHelper.startPage(page, limit);
        List<School> schoolList=customerService.allSchool(r.getSchool());
        for (School j : schoolList) {
            List<YearData> yearData = customerService.selectYearDataId(j.getId());
            j.setCount(yearData.size());
        }

        LayuiData layuiData = new LayuiData();
        layuiData.setCode(0);
        layuiData.setMsg("");
        layuiData.setCount((int) pagehelper.getTotal());
        layuiData.setData(schoolList);
        return layuiData;
    }
    @RequestMapping("/selectSchoolInfo")
    @ResponseBody
    public List<School> selectSchoolInfo() {
        List<School> areaList =  customerService.allSchoolInfo();
        return areaList;
    }

    //删除学校
    @RequestMapping("/deleteSchool")
    @ResponseBody
    public String deleteSchool(Integer id) throws Exception {
        Integer integer = customerService.deleteSchool(id);
        if (integer == 1) {
            return "yes";
        } else {
            return "no";
        }
    }

    //查询所有学校
    @RequestMapping("/allSchoolInfo")
    @ResponseBody
    public LayuiData allSchoolInfo(@RequestParam(value = "page", required = true, defaultValue = "1") int page, @RequestParam(value = "limit", required = true, defaultValue = "10") int limit) {

        Page pagehelper = PageHelper.startPage(page, limit);
        List<School> schoolList=customerService.allSchoolInfo();
        for (School j : schoolList) {
            List<YearData> yearData = customerService.selectYearDataId(j.getId());
            j.setCount(yearData.size());
        }

        LayuiData layuiData = new LayuiData();
        layuiData.setCode(0);
        layuiData.setMsg("");
        layuiData.setCount((int) pagehelper.getTotal());
        layuiData.setData(schoolList);
        return layuiData;
    }

    //所有学校——新增
    @RequestMapping("/addSchoolInfo")
    @ResponseBody
    public String addSchoolInfo(School school) throws Exception {
        Integer integer = customerService.addSchoolInfo(school);
        if (integer == 1) {
            return "yes";
        } else {
            return "no";
        }
    }

    //查询历年数据
    @RequestMapping("/selectData")
    public String selectData(Integer id,Model model) {
        model.addAttribute("id",id);
        return "allData";
    }
    @RequestMapping("/allData")
    @ResponseBody
    public LayuiData allData(@RequestParam(value = "page", required = true, defaultValue = "1") int page, @RequestParam(value = "limit", required = true, defaultValue = "10") int limit, Integer id) {

        Page pagehelper = PageHelper.startPage(page, limit);
        List<YearData> yearData=customerService.selectYearDataId(id);

        LayuiData layuiData = new LayuiData();
        layuiData.setCode(0);
        layuiData.setMsg("");
        layuiData.setCount((int) pagehelper.getTotal());
        layuiData.setData(yearData);
        return layuiData;
    }

    //添加历年数据
    @RequestMapping("/addYearData")
    @ResponseBody
    public String addYearData(YearData yearData) throws Exception {
        Integer integer = customerService.addYearData(yearData);
        if (integer == 1) {
            return "yes";
        } else {
            return "no";
        }
    }

    //删除历年数据
    @RequestMapping("/deleteYearData")
    @ResponseBody
    public String deleteYearData(Integer id) throws Exception {
        Integer integer = customerService.deleteYearData(id);
        if (integer == 1) {
            return "yes";
        } else {
            return "no";
        }
    }

}
