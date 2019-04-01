package com.ssm.demo.service.impl;

import com.ssm.demo.dao.UserInfoDao;
import com.ssm.demo.dao.UserRoleDao;
import com.ssm.demo.entity.UserInfo;
import com.ssm.demo.service.UserInfoService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Resource
    UserInfoDao userInfoDao;
    @Resource
    UserRoleDao userRoleDao;


    public String getPermissionByUserName(UserInfo userInfo) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userInfo.getUsername(), userInfo.getPassword());
        System.out.println(token);
        subject.login(token);
        try {
            return "登陆成功";
        } catch (Exception e) {
            return e.getMessage();
        }

    }
}
