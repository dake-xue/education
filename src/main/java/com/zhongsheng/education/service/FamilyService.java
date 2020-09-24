package com.zhongsheng.education.service;

import com.zhongsheng.education.entiy.FamilyInfo;

public interface FamilyService {
    public FamilyInfo selectFamilyInfo(Integer sid);
    int addFamilyInfo(FamilyInfo familyinfo);
}
