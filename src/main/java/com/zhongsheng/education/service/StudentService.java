package com.zhongsheng.education.service;

import com.zhongsheng.education.entiy.Student;
import com.zhongsheng.education.entiy.User;

import java.util.List;

public interface StudentService {

    public User selectWho(Integer username,String password);
    public Student selectStudent(Integer uid);
    public List<Student> selectAllStudent(String keyword,Integer modules);
    public Integer addScore(Integer sid,Integer scope);
    List<Student> allStudent();
    int addStudentInfo(Student student);

}
