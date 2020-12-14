package com.zhongsheng.education.service.impl;

import com.zhongsheng.education.entiy.Order;
import com.zhongsheng.education.mapper.OrderMapper;
import com.zhongsheng.education.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public Integer addOrder(Order order) {
        return orderMapper.addOrder(order);
    }

    @Override
    public Order searchByOrderNum(String order_number) {
        return orderMapper.searchByOrderNum(order_number);
    }

    @Override
    public Integer updateStatus(Order order) {
        return orderMapper.updateStatus(order);
    }

    @Override
    public Order searchOrderBySnum(String snum) {
        return orderMapper.searchOrderBySnum(snum);
    }

    @Override
    public Integer deleteOrders(String phone) {
        return orderMapper.deleteOrders(phone);
    }
}
