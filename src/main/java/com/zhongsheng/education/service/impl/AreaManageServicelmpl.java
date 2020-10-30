package com.zhongsheng.education.service.impl;

import com.zhongsheng.education.entiy.Area;
import com.zhongsheng.education.entiy.CampusDic;
import com.zhongsheng.education.entiy.Student;
import com.zhongsheng.education.mapper.AreaManageMapper;
import com.zhongsheng.education.service.AreaManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaManageServicelmpl implements AreaManageService {
    @Autowired
    AreaManageMapper areaManageMapper;

    @Override
    public Integer addArea(String name) {
        return areaManageMapper.addArea(name);
    }

    @Override
    public Integer updateArea(Integer id, String anum) {
        return areaManageMapper.updateArea(id, anum);
    }

    @Override
    public Area selectArea(String name) {
        return areaManageMapper.selectArea(name);
    }

    @Override
    public Integer selectCnum(Integer id) {
        return areaManageMapper.selectCnum(id);
    }

    @Override
    public Integer addCampus(Integer aid, String num, String name) {
        return areaManageMapper.addCampus(aid,num,name);
    }

    @Override
    public Integer  addSchool(Integer id,String name){
        return areaManageMapper.addSchool( id, name);
    }

    @Override
    public Integer  toUpdateArea(Area area){
        return areaManageMapper.toUpdateArea(area);
    }

    @Override
    public Integer toUpdateCampus(CampusDic campusDic){
        return areaManageMapper.toUpdateCampus(campusDic);
    }

    @Override
    public Integer toUpdateSchool(CampusDic campusDic){
        return areaManageMapper.toUpdateSchool(campusDic);
    }

    @Override
    public List<Area> allArea() {
        return areaManageMapper.allArea();
    }

    @Override
    public List<Student> selectStudent(Integer schoolid){
      return areaManageMapper.selectStudent(schoolid);
    }

    @Override
    public List<Area> selectAreaByAid(Integer aid) {
        if(aid==0){}
        return areaManageMapper.selectAreaByAid(aid);
    }
}
