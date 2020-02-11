package com.gx.cloud.user.admin.server.config.security;

import com.alibaba.fastjson.JSON;
import com.gx.cloud.common.exception.ExceptionCode;
import com.gx.cloud.common.util.ResultVO;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: guoxun
 * @date: 2018/10/15 16:43
 * @description: 无权访问
 */

@Component
public class AjaxAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.getWriter().write(JSON.toJSONString(ResultVO.result(ExceptionCode.USER_NO_ACCESS,false)));
    }
}
