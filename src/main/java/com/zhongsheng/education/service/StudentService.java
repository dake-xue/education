package com.zhongsheng.education.service;

import com.zhongsheng.education.entiy.Area;
import com.zhongsheng.education.entiy.CampusDic;
import com.zhongsheng.education.entiy.Student;
import com.zhongsheng.education.entiy.TableDic;

import java.util.List;

public interface StudentService {

    Student selectStudentByIphone(String phone);
    public Student selectStudent(String snum);
    public Student selectStudentOne(String snum);
    Integer selectJiaoFeiJinE(String snum);
    public List<Student> selectAllStudent(String keyword,Integer modules,Integer page,Integer limit);
    public Integer addScore(String snum,Integer scope);
    int addStudentInfo(Student student, String name);
    List<Area> selectArea();
    List<TableDic> selectQu(Integer id);
    List<TableDic> selectSchool(Integer id);
    String selectNumber(Integer id);
    CampusDic selectCNumber(Integer ca);
    Integer selectXuHao(Integer id);
    public Integer changeScore(String snum,Integer score);
}
