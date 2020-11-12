package com.zhongsheng.education.service;

import com.zhongsheng.education.entiy.Classes;

import java.util.List;

public interface ClassesService {
    List<Classes> selectClasses(Integer area);
    List<Classes> selectCourse(Integer area);
    List<Classes> selectClassMoney(String name);
    Integer addScore(Integer aid, String name, Integer classmoney);
    Integer addCourse(Integer aid, String name);
    Integer deleteScore(Integer id);
    Integer updateScore(Integer id, String name, Integer classmoney);
    Integer deleteCourse(Integer id);
    Integer updateCourse(Integer id, String name);
}
