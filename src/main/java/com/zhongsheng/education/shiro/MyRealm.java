package com.zhongsheng.education.shiro;

import com.zhongsheng.education.config.ShiroConfig;
import com.zhongsheng.education.entiy.PermissionInfo;
import com.zhongsheng.education.entiy.User;
import com.zhongsheng.education.service.PermissionService;
import com.zhongsheng.education.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * 自定义realm
 * @author shengwu ni
 */
public class MyRealm extends AuthorizingRealm {
    private static final Logger logger = LoggerFactory.getLogger(MyRealm.class);

    @Resource
    private UserService userService;
    @Resource
    private PermissionService permissionService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        logger.info("开始授权。。。");
        // 获取用户名
        User user = (User) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        // 给该用户设置角色，角色信息存在 user 表中取
        authorizationInfo.setRoles(userService.getRoles(user.getUsername()));
        // 给该用户设置权限，权限信息存在 role 表中取
        List<PermissionInfo> lists = permissionService.getPermissions(user.getUsername());
        //方法3 增强型for循环遍历
        for(PermissionInfo permissionInfo: lists){
            authorizationInfo.addStringPermission(permissionInfo.getSn());
        }
        logger.info("授权详情："+authorizationInfo.getStringPermissions().toString());
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        logger.info("获取user。。。");
        // 根据 Token 获取用户名
        String username = (String) authenticationToken.getPrincipal();
        // 根据用户名从数据库中查询该用户
        User user = userService.getByUsername(username);
        if(user != null) {
            // 把当前用户存到 Session 中
            SecurityUtils.getSubject().getSession().setAttribute("user", user);
            logger.info("shiro中user:"+user);
            // 传入用户名和密码进行身份认证，并返回认证信息
            AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(user,user.getPassword(), "myRealm");
            return authcInfo;
        } else {
            return null;
        }
    }
}
