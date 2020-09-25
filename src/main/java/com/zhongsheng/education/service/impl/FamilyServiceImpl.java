package com.zhongsheng.education.service.impl;

import com.zhongsheng.education.entiy.FamilyInfo;
import com.zhongsheng.education.mapper.FamilyMapper;
import com.zhongsheng.education.service.FamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FamilyServiceImpl implements FamilyService {
    @Autowired
    FamilyMapper familyMapper;

    @Override
    public FamilyInfo selectFamilyInfo(String snum) {
        return familyMapper.selectFamilyInfo(snum);
    }

    @Override
    public int addFamilyInfo(FamilyInfo familyinfo) {
        System.out.println("familyinfo:"+familyinfo);
        return familyMapper.addFamilyInfo(familyinfo);
    }


}
