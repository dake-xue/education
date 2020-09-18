package com.zhongsheng.education.service;

import com.zhongsheng.education.entiy.Schoolinfo;

import java.util.List;

public interface SchoolService {

    public List<Schoolinfo> selectInfoInfo(Integer sid);
}
