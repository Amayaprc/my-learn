package com.prc.springbootshiro.shiro;

import com.prc.springbootshiro.pojo.dto.UserDto;
import com.prc.springbootshiro.pojo.entity.User;
import com.prc.springbootshiro.service.PermissionService;
import com.prc.springbootshiro.service.RoleService;
import com.prc.springbootshiro.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MyRealm extends AuthorizingRealm {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        logger.info("调用了授权方法!");
        //获取当前登录的用户
        UserDto userInfo = (UserDto) principalCollection.getPrimaryPrincipal();
        List<String> roles = userInfo.getRoles();
        List<String> permissions = userInfo.getPermissions();

        //通过SimpleAuthenticationInfo做授权
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

        if (null != roles && roles.size()>0){
            //添加角色
            simpleAuthorizationInfo.addRoles(roles);
        }
        if (null != permissions && permissions.size()>0){
            //添加权限
            simpleAuthorizationInfo.addStringPermissions(permissions);
        }
        return simpleAuthorizationInfo;
    }

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        logger.info("调用了认证方法!");
        //UsernamePasswordToken用于存放提交的登录信息
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;

        String username = token.getUsername();
        User user = userService.findByUserNam(username);
        logger.debug("用户登录认证！用户信息user：" + user);
        if (null != user) {
            //查询当前登录用户角色
            List<String> roles = roleService.findRole(user.getUserid());
            //查询当前登录用户权限
            List<String> permissions = permissionService.findPermission(user.getUserid());
            UserDto userInfo = new UserDto(user,roles,permissions);
            return new SimpleAuthenticationInfo(userInfo,user.getPassword(),ByteSource.Util.bytes(username),getName());
        }else {
            return null;
        }
    }
}
