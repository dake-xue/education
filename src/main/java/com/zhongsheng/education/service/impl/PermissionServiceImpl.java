package com.zhongsheng.education.service.impl;

import com.zhongsheng.education.entiy.PermissionInfo;
import com.zhongsheng.education.mapper.PermissionMapper;
import com.zhongsheng.education.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;
    @Override
    public List<PermissionInfo> getAll() {
        return permissionMapper.getAll();
    }

    @Override
    public List<PermissionInfo> getPermissions(String username) {
        return permissionMapper.getPermissions(username);
    }

    @Override
    public PermissionInfo searchPerById(Integer id) {
        return permissionMapper.searchPerById(id);
    }

    @Override
    public Integer addPermission(PermissionInfo permissionInfo) {
        return permissionMapper.addPermission(permissionInfo);
    }

    @Override
    public Integer updatePermission(PermissionInfo permissionInfo) {
        return permissionMapper.updatePermission(permissionInfo);
    }

    @Override
    public Integer addRoleAndPermission(Integer roleid, Integer perid) {
        return permissionMapper.addRoleAndPermission(roleid,perid);
    }
}
