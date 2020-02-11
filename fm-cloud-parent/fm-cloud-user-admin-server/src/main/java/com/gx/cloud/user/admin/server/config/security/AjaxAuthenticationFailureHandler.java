package com.gx.cloud.user.admin.server.config.security;

import com.alibaba.fastjson.JSON;
import com.gx.cloud.common.exception.ExceptionCode;
import com.gx.cloud.common.util.ResultVO;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: guoxun
 * @date: 2018/10/15 15:31
 * @description: 用户登录失败时返回给前端的数据
 */
@Component
public class AjaxAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.getWriter().write(JSON.toJSONString(ResultVO.result(ExceptionCode.USER_LOGIN_FAILED,false)));
    }
}
