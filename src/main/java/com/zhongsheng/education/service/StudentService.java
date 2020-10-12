package com.zhongsheng.education.service;

import com.zhongsheng.education.entiy.*;

import java.util.List;

public interface StudentService {

    Student selectStudentByIphone(String phone);
    public Student selectStudent(String snum);
    public  List<Performance> selectPer(String snum);
    public Student selectStudentOne(String snum);
    Integer selectJiaoFeiJinE(String snum);
    public List<Student> selectAllStudent(Integer modules, String keyword,Integer status,Integer page,Integer limit);
    public Integer addScore(String snum,Integer scope);
    int addStudentInfo(Student student, String name);
    List<Area> selectArea();
    List<TableDic> selectQu(Integer id);
    List<TableDic> selectSchool(Integer id);
    String selectNumber(Integer id);
    CampusDic selectCNumber(Integer ca);
    Integer selectXuHao(Integer id);
    public Integer changeScore(String snum,Integer score);
    Integer updateStudent(Student student);
    public Integer addPer(Performance performance);
    public Integer addPerfor(Performance performance);
    public Performance selectPerOne(Integer id);
}
