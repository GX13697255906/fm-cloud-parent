package com.gx.cloud.user.admin.server.config.security;

import com.alibaba.fastjson.JSON;
import com.gx.cloud.common.exception.ExceptionCode;
import com.gx.cloud.common.util.ResultVO;
import com.gx.cloud.common.util.UtilDate;
import com.gx.cloud.common.util.UtilRedis;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: guoxun
 * @date: 2018/10/16 9:59
 * @description: 登出成功
 */
@Component
@Slf4j
public class AjaxLogoutSuccessHandler implements LogoutSuccessHandler {

    @Autowired
    private UtilRedis utilRedis;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String authHeader = request.getHeader("Authorization");
        if(authHeader != null && authHeader.startsWith("Bearer")){
            final String authToken = authHeader.substring("Bearer".length());
            utilRedis.hset("blacklist",authToken, UtilDate.getTime());
        }
        response.getWriter().write(JSON.toJSONString(ResultVO.result(ExceptionCode.USER_LOGOUT_SUCCESS,true)));
    }
}
