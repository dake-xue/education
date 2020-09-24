package com.zhongsheng.education.service;

import com.zhongsheng.education.entiy.SchoolInfo;

public interface SchoolService {

    public SchoolInfo selectSchoolInfo(Integer sid);

    int addSchoolInfo(SchoolInfo schoolInfo);
}
