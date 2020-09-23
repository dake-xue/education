package com.zhongsheng.education.service.impl;

import com.zhongsheng.education.entiy.SchoolInfo;
import com.zhongsheng.education.mapper.SchoolMapper;
import com.zhongsheng.education.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolServicrImpl implements SchoolService {

    @Autowired
    SchoolMapper schoolMapper;

    public List<SchoolInfo> selectInfoInfo(Integer sid){
        return    schoolMapper.selectInfoInfo(sid);
    }

    @Override
    public int addSchoolInfo(SchoolInfo schoolInfo) {
        return schoolMapper.addSchoolInfo(schoolInfo);
    }


}
