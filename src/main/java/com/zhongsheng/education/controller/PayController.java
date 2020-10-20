package com.zhongsheng.education.controller;


import com.alipay.api.AlipayApiException;
import com.zhongsheng.education.entiy.Order;
import com.zhongsheng.education.service.AliPayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/alipay")
@RestController
public class PayController {

    Logger logger = LoggerFactory.getLogger("PayController.class");

    @Autowired
    AliPayService aliPayService;

    @RequestMapping(value = "/toPay", method = RequestMethod.GET)
    public String alipay(Order order) throws AlipayApiException {
        return aliPayService.aliPay(order);
    }

    @PostMapping("/notify_url")
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
            return aliPayService.aliReturn(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("----return-----");
        return "支付完成以后的回调接口";
    }
}
