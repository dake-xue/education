package com.zhongsheng.education.service;

import com.zhongsheng.education.entiy.FamilyInfo;
import java.util.List;

public interface FamilyService {
    public List<FamilyInfo> selectFamilyInfo(Integer sid);
    int addFamilyInfo(FamilyInfo familyinfo);
}
