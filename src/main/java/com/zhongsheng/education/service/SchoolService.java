package com.zhongsheng.education.service;

import com.zhongsheng.education.entiy.SchoolInfo;

public interface SchoolService {

    public SchoolInfo selectSchoolInfo(String snum);

    int addSchoolInfo(SchoolInfo schoolInfo);

    public Integer updateSchool(SchoolInfo schoolInfo);
}
