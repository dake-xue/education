package com.zhongsheng.education.service;

import com.zhongsheng.education.entiy.*;

import java.util.List;

public interface StudentService {

    public User selectWho(Integer username,String password);
    public Student selectStudent(String snum);
    public Student selectStudentID(Integer id);
    public List<Student> selectAllStudent(String keyword,Integer modules);
    public Integer addScore(Integer sid,Integer scope);
    List<Student> allStudent();
    int addStudentInfo(Student student);
    List<Area> selectArea();
    List<TableDic> selectQu(Integer id);
    List<TableDic> selectSchool(Integer id);
    String selectNumber(Integer id);
    CampusDic selectCNumber(Integer ca);
    Integer selectXuHao(Integer id);
}
