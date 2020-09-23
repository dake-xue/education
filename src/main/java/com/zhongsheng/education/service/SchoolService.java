package com.zhongsheng.education.service;

import com.zhongsheng.education.entiy.SchoolInfo;

import java.util.List;

public interface SchoolService {

    public List<SchoolInfo> selectInfoInfo(Integer sid);

    int addSchoolInfo(SchoolInfo schoolInfo);
}
