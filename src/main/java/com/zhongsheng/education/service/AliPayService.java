package com.zhongsheng.education.service;

import com.alipay.api.AlipayApiException;
import com.zhongsheng.education.entiy.Order;

import javax.servlet.http.HttpServletRequest;

public interface AliPayService  {

    String aliPay(Order order)throws AlipayApiException;

    String aliNotify(HttpServletRequest request) throws Exception;

    String aliReturn(HttpServletRequest request) throws Exception;

}
