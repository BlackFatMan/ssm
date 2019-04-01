package com.ssm.demo.controller;

import com.ssm.demo.entity.UserInfo;
import com.ssm.demo.service.UserInfoService;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@Transactional
@RequestMapping(value = "/user",produces = "application/json;charset=UTF-8")
public class UserInController {

    @Resource
    UserInfoService userInfoService;

    @RequestMapping(value = "/subLogin",method = RequestMethod.POST)
    @ResponseBody
    public String subLogin(UserInfo userInfo){
        return userInfoService.getPermissionByUserName(userInfo);
    }

    @RequiresRoles("manager")
    @RequestMapping(value = "/test")
    @ResponseBody
    public String test(){
        return "管理员角色";
    }

    @RequiresRoles("user")
    @RequestMapping(value = "/test1")
    @ResponseBody
    public String test1(){
        return "普通用户";
    }

    @RequiresPermissions("user:delete")
    @RequestMapping("/userDelete")
    @ResponseBody
    public String test2(){
        return "user:delete";
    }

    @RequiresPermissions("user:update")
    @RequestMapping("/userUpdate")
    @ResponseBody
    public String test3(){
        return "user:update";
    }

    @RequestMapping(value = "/logout")
    @ResponseBody
    public String logout(){
        Subject subject=SecurityUtils.getSubject();
        subject.logout();
        return "登出成功";
    }


}
