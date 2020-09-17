package com.zhongsheng.education.service;

import com.zhongsheng.education.entiy.Student;
import com.zhongsheng.education.entiy.User;

public interface StudentService {

    public User selectWho(Integer username,String password);
    public String selectTeacher(Integer uid);
    public Student selectStudent(Integer uid);
}
