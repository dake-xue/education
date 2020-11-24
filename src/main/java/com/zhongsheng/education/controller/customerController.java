package com.zhongsheng.education.controller;

import com.zhongsheng.education.entiy.Httpclient;
import com.zhongsheng.education.entiy.Token;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("customer")
public class customerController {

    @ResponseBody
    @GetMapping("/openid")
    public Token openid(String code, String appid, String secret, String grant_type){
        System.out.println("code:"+code+"appId:"+appid+"secret:"+secret);

        Token token= Httpclient.doGetTestWayOne(code,appid,secret,grant_type);

        return token;
    }



}
