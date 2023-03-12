package com.yibingo.race.security.handler;


import com.alibaba.fastjson.JSON;
import com.yibingo.race.common.utils.Result;
import com.yibingo.race.security.common.GenericResponse;
import com.yibingo.race.security.common.ServiceError;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: jamesluozhiwei
 * @description: 用户登录失败时返回给前端的数据
 */
@Component
public class AjaxAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(JSON.toJSONString(Result.failed(ServiceError.GLOBAL_ERR_NO_CODE.getCode(),ServiceError.GLOBAL_ERR_NO_CODE.getMsg())));
    }

}