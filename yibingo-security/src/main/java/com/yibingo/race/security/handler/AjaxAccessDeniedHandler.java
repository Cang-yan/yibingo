package com.yibingo.race.security.handler;

import com.alibaba.fastjson.JSON;

import com.yibingo.race.common.utils.Result;
import com.yibingo.race.security.common.GenericResponse;
import com.yibingo.race.security.common.ServiceError;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: jamesluozhiwei
 * @description: 无权访问
 */
@Component
public class AjaxAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(JSON.toJSONString(Result.failed(ServiceError.GLOBAL_ERR_NO_AUTHORITY.getCode(),ServiceError.GLOBAL_ERR_NO_AUTHORITY.getMsg())));
    }
}