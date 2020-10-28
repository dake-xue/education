package com.zhongsheng.education.service;

import com.zhongsheng.education.entiy.Area;
import com.zhongsheng.education.entiy.CampusDic;
import com.zhongsheng.education.entiy.Student;

import java.util.List;


public interface AreaManageService {

    Integer addArea(String name);

    Integer updateArea(Integer id, String anum);

    Area selectArea(String name);

    Integer selectCnum(Integer id);

    Integer addCampus(Integer aid, String num, String name);

    Integer  addSchool(Integer id,String name);

    Integer  toUpdateArea(Area area);

    Integer toUpdateCampus(CampusDic campusDic);

    Integer toUpdateSchool(CampusDic campusDic);

    List<Area> allArea();

    List<Student> selectStudent(Integer schoolid);
}
