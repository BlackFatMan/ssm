package com.ssm.demo.controller;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/swagger")
@Api(value = "testController",description = "swagger测试接口")
public class SwaggerDemoController {
    /*
     * @ApiOperation(value = "接口说明", httpMethod ="接口请求方式", response ="接口返回参数类型", notes ="接口发布说明"
     *
     * @ApiParam(required = "是否必须参数", name ="参数名称", value ="参数具体描述"
     */
    @RequestMapping(value = "/test",produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "测试",httpMethod = "GET",notes = "在没有会话、没有签名的情况下，进入方法体")
    public String swaggerTest(String a,String b){

        return "swagger测试接口";
    }
}
