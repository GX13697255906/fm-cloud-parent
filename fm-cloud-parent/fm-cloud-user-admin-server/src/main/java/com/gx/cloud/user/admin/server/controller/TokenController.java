package com.gx.cloud.user.admin.server.controller;


import com.gx.cloud.common.util.DateUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Date;
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
@Slf4j
@RestController
@Api(value = "登录验证",tags = "登录验证")
public class TokenController {

    @ApiOperation(value = "获取权限令牌",notes = "获取权限令牌")
    @RequestMapping(value = "/login/token",method = RequestMethod.POST)
    public String getLoginToken(@RequestParam("username") String username,@RequestParam("password") String password) throws Exception{
        log.info("用户 "+username+" 进行登录 时间：{}", DateUtils.dateToString(new Date()));
        return null;
    }

    /**
     * 生成 oauth2 token
     * @param userName
     * @param password
     * @param type
     * @return
     */
//    public OAuth2AccessToken getToken(String userName, String password, String type) throws Exception {
//        DmsOAuth2ClientDetails clientDetails = clientProperties.getOauth2().get("admin");
//        // 使用oauth2密码模式登录.
//        Map<String, String> postParameters = new HashMap<>();
//        postParameters.put("username", userName);
//        postParameters.put("password", password);
//        postParameters.put("client_id", clientDetails.getClientId());
//        postParameters.put("client_secret", clientDetails.getClientSecret());
//        postParameters.put("grant_type", "password");
//        // 添加参数区分,第三方登录
//        postParameters.put("login_type", type);
//        return DmsHelper.createAccessToken(endpoints, postParameters);
//    }

}
