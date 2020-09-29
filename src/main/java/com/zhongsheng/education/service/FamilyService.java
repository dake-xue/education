package com.zhongsheng.education.service;

import com.zhongsheng.education.entiy.FamilyInfo;

public interface FamilyService {
    public FamilyInfo selectFamilyInfo(String snum);
    int addFamilyInfo(FamilyInfo familyinfo);
    Integer updateFamily(FamilyInfo familyInfo);
}
