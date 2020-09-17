package com.zhongsheng.education.service.impl;

import com.zhongsheng.education.entiy.Student;
import com.zhongsheng.education.entiy.User;
import com.zhongsheng.education.mapper.StudentMapper;
import com.zhongsheng.education.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
   private StudentMapper studentMapper;

    public User selectWho(Integer username, String password){

        return studentMapper.selectWho(username,password);
    };

    public Student selectStudent(Integer uid){
        return studentMapper.selectStudent(uid);
    };

    public List<Student> selectAllStudent(String classes, String sname, String major){
        return studentMapper.selectAllStudent(classes, sname, major);
    };

    public Student addScore(Integer sid,Integer scope){
        return studentMapper.addScore(sid,scope);
    }
}
