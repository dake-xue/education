package com.zhongsheng.education.service.impl;

import com.zhongsheng.education.entiy.Classes;
import com.zhongsheng.education.mapper.ClassesMapper;
import com.zhongsheng.education.service.ClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassesServiceImpl implements ClassesService {
    @Autowired
    ClassesMapper classesMapper;

    @Override
    public List<Classes> selectClasses(Integer area) {
        return classesMapper.selectClasses(area);
    }
    @Override
    public List<Classes> selectCourse(Integer area) {
        return classesMapper.selectCourse(area);
    }

    @Override
    public List<Classes> selectClassMoney(String name) {
        return classesMapper.selectClassMoney(name);
    }
    @Override
    public Integer addScore(Integer aid, String name, Integer classmoney) {

        return classesMapper.addScore(aid, name, classmoney);
    }

    @Override
    public Integer addCourse(Integer aid, String name){

        return classesMapper.addCourse(aid, name);
    }
    @Override
    public Integer deleteScore(Integer id) {
        return classesMapper.deleteScore(id);
    }
    @Override
    public Integer updateScore(Integer id, String name, Integer classmoney) {
        return classesMapper.updateScore(id, name, classmoney);
    }
    @Override
    public Integer deleteCourse(Integer id) {
        return classesMapper.deleteCourse(id);
    }
    @Override
    public Integer updateCourse(Integer id, String name) {
        return classesMapper.updateCourse(id, name);
    }
}



