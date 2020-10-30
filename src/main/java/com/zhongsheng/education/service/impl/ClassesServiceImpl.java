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

    public List<Classes> selectClasses(Integer area) {
        return classesMapper.selectClasses(area);
    }

    public Integer addScore(Integer aid, String name, Integer classmoney) {

        return classesMapper.addScore(aid, name, classmoney);
    }

    public Integer deleteScore(Integer id) {
        return classesMapper.deleteScore(id);
    }

    public Integer updateScore(Integer id, String name, Integer classmoney) {
        return classesMapper.updateScore(id, name, classmoney);
    }
  public   List<Classes> selectClassMoney(Integer id){
        return classesMapper.selectClassMoney(id);
  };


};



