package com.zhongsheng.education.controller;


import com.alipay.api.AlipayApiException;
import com.zhongsheng.education.entiy.Order;
import com.zhongsheng.education.entiy.Student;
import com.zhongsheng.education.service.AliPayService;
import com.zhongsheng.education.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequestMapping("/alipay")
@Controller
public class PayController {

    Logger logger = LoggerFactory.getLogger("PayController.class");

    @Autowired
    AliPayService aliPayService;

    @Autowired
    StudentService studentService;

    @RequestMapping(value = "/toPay", method = RequestMethod.GET)
    public void alipay(Order order, HttpServletResponse response) throws AlipayApiException {
        logger.info("order:"+order.toString());
        response.setContentType("text/html;charset=utf-8");
        try {
            response.getWriter().write(aliPayService.aliPay(order));
            response.getWriter().flush();
            response.getWriter().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/notify_url")
    @ResponseBody
    public String notifyAlipay(HttpServletRequest request) {
        try {
            aliPayService.aliNotify(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("----notify-----");
        return "支付异步通知接口";
    }

    @GetMapping("/return_url")
    public String returnAlipay(HttpServletRequest request) {
        try {
           String sNum = aliPayService.aliReturn(request);
           Student student = studentService.selectStudent(sNum);
           request.setAttribute("student",student);
           return "stuToStudentDetails";
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("----return-----");
        return "支付完成以后的回调接口";
    }
}
