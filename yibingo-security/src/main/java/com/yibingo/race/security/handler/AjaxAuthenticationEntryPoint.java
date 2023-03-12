package com.yibingo.race.security.handler;

import com.alibaba.fastjson.JSON;
import com.yibingo.race.common.utils.Result;
import com.yibingo.race.security.common.GenericResponse;
import com.yibingo.race.security.common.ServiceError;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: jamesluozhiwei
 * @description: 用户未登录时返回给前端的数据
 */
@Component
public class AjaxAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");
        response.getWriter().write(JSON.toJSONString(Result.failed(ServiceError.GLOBAL_ERR_NO_SIGN_IN.getCode(),ServiceError.GLOBAL_ERR_NO_SIGN_IN.getMsg())));
    }
}
