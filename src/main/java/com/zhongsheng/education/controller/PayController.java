package com.zhongsheng.education.controller;


import com.alipay.api.AlipayApiException;
import com.zhongsheng.education.entiy.Order;
import com.zhongsheng.education.entiy.Student;
import com.zhongsheng.education.entiy.WinterStu;
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
import java.io.PrintWriter;

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
    public void notifyAlipay(HttpServletRequest request,HttpServletResponse response) {
        PrintWriter writer = null;
        try {
            String str=  aliPayService.aliNotify(request);
            logger.info("异步回调："+str);
            writer = response.getWriter();
            writer.write(str); //一定要打印success
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (writer != null) {
                writer.close();
            }
        }
        logger.info("----notify-----");
    }

    @GetMapping("/return_url")
    public String returnAlipay(HttpServletRequest request) {
        try {
           String sNum = aliPayService.aliReturn(request);
           if(sNum.length()<11){
               Student student = studentService.selectStudent(sNum);
               request.setAttribute("student",student);
               return "stuToStudentDetails";
           }else {
               WinterStu student =  studentService.selectWinterStudentByPhone(sNum);
               request.setAttribute("student",student);
               return "winterStudentDetails";
           }

        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("----return-----");
        return "error";
    }
}
