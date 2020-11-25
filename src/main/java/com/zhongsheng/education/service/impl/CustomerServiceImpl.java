package com.zhongsheng.education.service.impl;

import com.zhongsheng.education.entiy.Customer;
import com.zhongsheng.education.mapper.CustomerMapper;
import com.zhongsheng.education.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerMapper customerMapper;

    @Override
    public Integer addSignUp() {
        return customerMapper.addSignUp();
    }

    public List<Customer> selectSignUp(){
        return customerMapper.selectSignUp();
    };

}
