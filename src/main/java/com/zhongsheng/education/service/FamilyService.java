package com.zhongsheng.education.service;

import com.zhongsheng.education.entiy.Familyinfo;
import java.util.List;

public interface FamilyService {
    public List<Familyinfo> selectFamilyInfo(Integer sid);
}
