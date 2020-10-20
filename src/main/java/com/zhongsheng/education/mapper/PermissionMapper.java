package com.zhongsheng.education.mapper;

import com.zhongsheng.education.entiy.PermissionInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PermissionMapper {

    @Select("select * from permission")
    List<PermissionInfo> getAll();

    @Select("SELECT p.id,p.pername,p.resource,p.sn" +
            " from permission p" +
            " LEFT JOIN role_permission ro on ro.perid = p.id" +
            " LEFT JOIN role r on ro.roleid = r.id" +
            " LEFT JOIN user u on u.roleid = r.id" +
            " where u.username = #{username}")
    List<PermissionInfo> getPermissions(String username);

    @Select("select * from permission where id=#{id}")
    PermissionInfo searchPerById(Integer id);

    @Insert("insert into permission(pername,sn,resource) values(#{pername},#{sn},#{resource})")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    Integer addPermission(PermissionInfo permissionInfo);

    @Update("update permission set pername=#{pername},sn=#{sn},resource=#{resource} where id = #{id}")
    Integer updatePermission(PermissionInfo permissionInfo);

    @Insert("insert into role_permission(roleid,perid) values(#{roleid},#{perid})")
    Integer addRoleAndPermission(Integer roleid,Integer perid);

}
