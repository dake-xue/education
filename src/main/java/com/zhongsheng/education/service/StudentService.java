package com.zhongsheng.education.service;

import com.zhongsheng.education.entiy.*;

import java.util.List;

public interface StudentService {

    Student selectStudentByIphone(String phone);
    public Student selectStudent(String snum);
    public  List<Performance> selectPer(String snum);
    public Student selectStudentOne(String snum);
    String selectJiaoFeiJinE(String snum);
    public List<Student> selectAllStudent(Integer schoolid,SearchVo searchVo);
    public Integer addScore(String snum,Integer scope);
    Student addStudentInfo(Student student, String name);
    Integer addStudentTwo(Student student);
    Integer addWinterStudent(Student student);
    List<Area> selectArea();
    List<TableDic> selectQu(Integer id);
    List<TableDic> selectSchool(Integer id);
    String selectNumber(Integer id);
    CampusDic selectCNumber(Integer ca);
    TableDic selectSchoolId(String name);
    Integer selectXuHao(Integer id);
    public Integer changeScore(String snum,Integer score);
    Integer updateStudent(Student student);
    public Integer addPer(Performance performance);
    public Integer addPerfor(Performance performance);
    public Performance selectPerOne(Integer id);
    Integer updateStatus(Student student);
    public Student selectStudentBySnameAndIphone(Student student);
    List<Student> searchStuByCamp(String name,Integer page, Integer limit);
    List<WinterStu> allWinterStudent(Integer page, Integer limit);
    WinterStu selectWinterStudentByPhone(String id);
    Integer updateWinterStatus(String phone);
}
