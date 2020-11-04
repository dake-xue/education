package com.zhongsheng.education.service;

import com.zhongsheng.education.entiy.Classes;

import java.util.List;

public interface ClassesService {
    List<Classes> selectClasses(Integer area);
    List<Classes> selectClassMoney(String name);
    Integer addScore(Integer aid, String name, Integer classmoney);

    Integer deleteScore(Integer id);

    Integer updateScore(Integer id, String name, Integer classmoney);
}
