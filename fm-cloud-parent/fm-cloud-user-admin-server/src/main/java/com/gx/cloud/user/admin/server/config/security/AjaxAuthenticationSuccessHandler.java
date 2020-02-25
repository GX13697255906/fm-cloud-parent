package com.gx.cloud.user.admin.server.config.security;

import com.alibaba.fastjson.JSON;
import com.gx.cloud.common.exception.ExceptionCode;
import com.gx.cloud.common.util.ResultVO;
import com.gx.cloud.common.util.UtilAccessAddress;
import com.gx.cloud.user.admin.server.utils.UtilRedis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: guoxun
 * @date: 2018/10/15 16:12
 * @description: 用户登录成功时返回给前端的数据
 */

@Component
public class AjaxAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Value("${token.expirationSeconds}")
    private int expirationSeconds;

    @Value("${token.validTime}")
    private int validTime;

    @Autowired
    private UtilRedis utilRedis;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        String ip = UtilAccessAddress.getIpAddress(request);
        Map<String,Object> map = new HashMap<>();
        map.put("ip",ip);

        SelfUserDetails userDetails = (SelfUserDetails) authentication.getPrincipal();
        String jwtToken = JwtTokenUtil.generateToken(userDetails.getUsername(), expirationSeconds,map);
        //刷新时间
        Integer expire = validTime*24*60*60*1000;
        //获取请求的IP地址
        utilRedis.setTokenRefresh(jwtToken, userDetails.getUsername(), ip);
        response.getWriter().write(JSON.toJSONString(ResultVO.result(ExceptionCode.USER_LOGIN_SUCCESS,jwtToken,true)));
    }
}
