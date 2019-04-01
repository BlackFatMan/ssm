package com.ssm.demo.realm;

import com.ssm.demo.dao.UserInfoDao;
import com.ssm.demo.dao.UserPermessionDao;
import com.ssm.demo.dao.UserRoleDao;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class CustomRealm extends AuthorizingRealm {



    @Resource
    private UserInfoDao userInfoDao;
    @Resource
    private UserRoleDao userRoleDao;
    @Resource
    private UserPermessionDao permessionDao;


    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取到用户名
        String userName = (String) principalCollection.getPrimaryPrincipal();
        Set<String> rolesByUserName = userRoleDao.getRolesByUserName(userName);
        Set<String> permessionByUserName = permessionDao.getPermessionByUserName(userName);

        if (rolesByUserName==null&&permessionByUserName==null)
            return null;

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setRoles(rolesByUserName);
        simpleAuthorizationInfo.setStringPermissions(permessionByUserName);

        return simpleAuthorizationInfo;
    }

    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String userName = (String) authenticationToken.getPrincipal();
        String password = userInfoDao.getPasswordByUserName(userName);
        if (password == null) {
            return null;
        }
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(userName, password, "customRealm");
        //加盐
        simpleAuthenticationInfo.setCredentialsSalt(ByteSource.Util.bytes(userName));
        return simpleAuthenticationInfo;
    }

}
