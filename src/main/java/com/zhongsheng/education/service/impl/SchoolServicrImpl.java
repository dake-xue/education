package com.zhongsheng.education.service.impl;

import com.zhongsheng.education.entiy.SchoolInfo;
import com.zhongsheng.education.mapper.SchoolMapper;
import com.zhongsheng.education.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SchoolServicrImpl implements SchoolService {

    @Autowired
    SchoolMapper schoolMapper;

    public SchoolInfo selectSchoolInfo(Integer sid){
        return    schoolMapper.selectSchoolInfo(sid);
    }

    @Override
    public int addSchoolInfo(SchoolInfo schoolInfo) {
        return schoolMapper.addSchoolInfo(schoolInfo);
    }


}
