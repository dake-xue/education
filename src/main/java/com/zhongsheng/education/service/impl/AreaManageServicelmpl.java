package com.zhongsheng.education.service.impl;

import com.zhongsheng.education.entiy.Area;
import com.zhongsheng.education.entiy.CampusDic;
import com.zhongsheng.education.mapper.AreaManageMapper;
import com.zhongsheng.education.service.AreaManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AreaManageServicelmpl implements AreaManageService {
    @Autowired
    AreaManageMapper areaManageMapper;

    public Integer addArea(String name) {
        return areaManageMapper.addArea(name);
    }

    ;

    public Integer updateArea(Integer id, String anum) {
        return areaManageMapper.updateArea(id, anum);
    }

    ;

    public Area selectArea(String name) {
        return areaManageMapper.selectArea(name);
    }

    ;

    public Integer selectCnum(Integer id) {
        return areaManageMapper.selectCnum(id);
    }

    public Integer addCampus(Integer aid, String num, String name) {
        return areaManageMapper.addCampus(aid,num,name);
    }

    public Integer  addSchool(Integer id,String name){
        return areaManageMapper.addSchool( id, name);
    };

    public Integer  toUpdateArea(Area area){
        return areaManageMapper.toUpdateArea(area);
    };

    public Integer toUpdateCampus(CampusDic campusDic){
        return areaManageMapper.toUpdateCampus(campusDic);
    };

    public Integer toUpdateSchool(CampusDic campusDic){
        return areaManageMapper.toUpdateSchool(campusDic);
    };
}
