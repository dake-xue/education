package com.zhongsheng.education.service;

import com.zhongsheng.education.entiy.PermissionInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface PermissionService {

    List<PermissionInfo> getAll();
    List<PermissionInfo> getPermissions(String username);
    PermissionInfo searchPerById(Integer id);
    Integer addPermission(PermissionInfo permissionInfo);
    Integer updatePermission(PermissionInfo permissionInfo);
    Integer addRoleAndPermission(Integer roleid,Integer perid);
}
