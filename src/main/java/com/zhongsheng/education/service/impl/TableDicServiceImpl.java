package com.zhongsheng.education.service.impl;

import com.zhongsheng.education.entiy.PermissionInfo;
import com.zhongsheng.education.entiy.RoleVo;
import com.zhongsheng.education.entiy.TableDic;
import com.zhongsheng.education.mapper.PermissionMapper;
import com.zhongsheng.education.mapper.TableDicMapper;
import com.zhongsheng.education.service.TableDicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TableDicServiceImpl implements TableDicService {
    private static final Logger logger = LoggerFactory.getLogger(TableDicServiceImpl.class);

    @Autowired
    private TableDicMapper tableDicMapper;
    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<TableDic> searchAll(TableDic tableDic) {
        return tableDicMapper.searchAll(tableDic);
    }

    @Override
    public TableDic searchOne(TableDic tableDic) {
        return tableDicMapper.searchOne(tableDic);
    }

    @Override
    public Integer update(TableDic tableDic) {
        return tableDicMapper.update(tableDic);
    }

    @Override
    public Integer add(TableDic tableDic) {
        return tableDicMapper.add(tableDic);
    }

    @Override
    public Integer delete(TableDic tableDic) {
        return tableDicMapper.delete(tableDic);
    }
    @Override
    public TableDic  selectCampusId(String campus){
        return tableDicMapper.selectCampusId(campus);
    };
    @Override
    public List<TableDic> campusSelectSchool(Integer id){
        return tableDicMapper.campusSelectSchool(id);
    }

    @Override
    public Integer addRole(RoleVo roleVo) {
        logger.info("roleVo："+roleVo);
        //添加权限
        PermissionInfo permissionInfo = new PermissionInfo();
        permissionInfo.setPername(roleVo.getPername());
        permissionInfo.setSn(roleVo.getSn());
        permissionInfo.setResource(roleVo.getResource());
        logger.info("permissionInfo："+permissionInfo);
        permissionMapper.addPermission(permissionInfo);
        //添加角色
        TableDic tableDic = new TableDic();
        tableDic.setTableName("role");
        tableDic.setName(roleVo.getName());
        logger.info("role："+tableDic);
        tableDicMapper.add(tableDic);
        //维护两表关系
        return permissionMapper.addRoleAndPermission(tableDic.getId(),permissionInfo.getId());
    }

    @Override
    public TableDic searchOneByName(TableDic tableDic) {
        return tableDicMapper.searchOneByName(tableDic);
    }
    @Override
    public TableDic searchSchoolName(TableDic tableDic) {
        return tableDicMapper.searchSchoolName(tableDic);
    }

}
