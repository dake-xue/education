package com.zhongsheng.education.service;

import com.zhongsheng.education.entiy.Order;

public interface OrderService {

    Integer addOrder(Order order);
    Order searchByOrderNum (String order_number);
    Integer updateStatus(Order order);
    Order searchOrderBySnum (String snum);
    Integer deleteOrders(String phone);
}
