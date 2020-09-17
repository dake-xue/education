package com.zhongsheng.education.service.impl;

import com.zhongsheng.education.entiy.Student;
import com.zhongsheng.education.entiy.User;
import com.zhongsheng.education.mapper.StudentMapper;
import com.zhongsheng.education.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
   private StudentMapper studentMapper;

    public User selectWho(Integer username, String password){
        System.out.println("service");
        return studentMapper.selectWho(username,password);
    };

    public String selectTeacher(Integer uid){
        return studentMapper.selectTeacher(uid);
    };

    public Student selectStudent(Integer uid){
        return studentMapper.selectStudent(uid);
    };
}
