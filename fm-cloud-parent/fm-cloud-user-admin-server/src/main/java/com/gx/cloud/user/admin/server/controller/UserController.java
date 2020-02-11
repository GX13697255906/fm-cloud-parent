package com.gx.cloud.user.admin.server.controller;


import com.gx.cloud.user.admin.server.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xun.guo
 * @since 2019-12-31
 */
@RestController
@RequestMapping("/user")
@Api(value = "用户管理",tags = "用户管理")
public class UserController {

    @Autowired
    private UserService userService;

    public Map<String,Object> login(@RequestBody HashMap<String,Object> params){
        return null;

    }

    @ApiOperation(value = "用户注册",notes = "用户注册")
    @RequestMapping(value = "/registery",method = RequestMethod.POST)
    public String registery(@RequestBody HashMap<String,Object> params) throws Exception{
        String result = userService.registery(MapUtils.getString(params,"username"),MapUtils.getString(params,"password"));
        return result;
    }


}

