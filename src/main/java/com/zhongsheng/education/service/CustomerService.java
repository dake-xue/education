package com.zhongsheng.education.service;

import com.zhongsheng.education.entiy.Customer;

import java.util.List;

public interface CustomerService {
    Integer addSignUp();
    List<Customer> selectSignUp();

}
