package com.zhongsheng.education.service.impl;

import com.zhongsheng.education.mapper.TeacherMapper;
import com.zhongsheng.education.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private  TeacherMapper teacherMapper;
    @Override
    public String selectTeacherName(Integer uid) {
        return teacherMapper.selectTeacherName(uid);
    }
}
