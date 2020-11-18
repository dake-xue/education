package com.zhongsheng.education.service;

import com.zhongsheng.education.entiy.Classes;

import java.util.List;

public interface ClassesService {
    List<Classes> selectClasses(Integer area);
    List<Classes> selectCourse(Integer area);
    List<Classes> selectClassMoney(String name);
    Integer addScore(Integer aid, String name, String classmoney, Integer classnumber);
    Integer addCourse(Integer aid, String name);
    Integer deleteScore(Integer id);
    Integer updateScore(Integer id, String name,String classmoney,Integer classnumber);
    Integer deleteCourse(Integer id);
    Integer updateCourse(Integer id, String name);
}
