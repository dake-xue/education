package com.zhongsheng.education.service;

import com.zhongsheng.education.entiy.*;

import java.util.List;

public interface StudentService {

    public Student selectStudent(String snum);
    Integer selectJiaoFeiJinE(String snum);
    public List<Student> selectAllStudent(String keyword,Integer modules,Integer page,Integer limit);
    public Integer addScore(String snum,Integer scope);
    int addStudentInfo(Student student);
    List<Area> selectArea();
    List<TableDic> selectQu(Integer id);
    List<TableDic> selectSchool(Integer id);
    String selectNumber(Integer id);
    CampusDic selectCNumber(Integer ca);
    Integer selectXuHao(Integer id);
    public Integer changeScore(String snum,Integer score);
}
