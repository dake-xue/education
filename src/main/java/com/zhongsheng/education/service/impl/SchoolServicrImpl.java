package com.zhongsheng.education.service.impl;

import com.zhongsheng.education.entiy.Schoolinfo;
import com.zhongsheng.education.mapper.SchoolMapper;
import com.zhongsheng.education.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolServicrImpl implements SchoolService {

    @Autowired
    SchoolMapper schoolMapper;

    public List<Schoolinfo> selectInfoInfo(Integer sid){
     return    schoolMapper.selectInfoInfo(sid);
    };

}
