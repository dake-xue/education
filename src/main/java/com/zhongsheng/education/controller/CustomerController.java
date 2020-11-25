package com.zhongsheng.education.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zhongsheng.education.entiy.Customer;
import com.zhongsheng.education.entiy.Httpclient;
import com.zhongsheng.education.entiy.Token;
import com.zhongsheng.education.service.CustomerService;
import com.zhongsheng.education.util.LayuiData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("customer")
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
    @RequestMapping("/addSignUp")
    @ResponseBody
    public Integer addSignUp() {
        Integer i = customerService.addSignUp();
        return i;
    }

    //查看报名信息
    @RequestMapping("/selectSignUp")
    @ResponseBody
    public LayuiData selectSignUp(@RequestParam(value = "page", required = true, defaultValue = "1") int page, @RequestParam(value = "limit", required = true, defaultValue = "3") int limit, Integer area) {
        Page pagehelper = PageHelper.startPage(page, limit);
        List<Customer> customerList = customerService.selectSignUp();

        LayuiData layuiData = new LayuiData();
        layuiData.setCode(0);
        layuiData.setMsg("");
        layuiData.setCount((int) pagehelper.getTotal());
        layuiData.setData(customerList);
        return layuiData;
    }

}
