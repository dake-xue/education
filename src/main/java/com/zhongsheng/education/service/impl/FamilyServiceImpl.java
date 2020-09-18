package com.zhongsheng.education.service.impl;

import com.zhongsheng.education.entiy.Familyinfo;
import com.zhongsheng.education.mapper.FamilyMapper;
import com.zhongsheng.education.service.FamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FamilyServiceImpl implements FamilyService {
    @Autowired
    FamilyMapper familyMapper;

    public List<Familyinfo> selectFamilyInfo(Integer sid){
        return familyMapper.selectFamilyInfo(sid);
    };
}
