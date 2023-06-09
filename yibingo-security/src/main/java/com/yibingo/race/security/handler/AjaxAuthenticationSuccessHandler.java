package com.yibingo.race.security.handler;

import com.alibaba.fastjson.JSON;
import com.yibingo.race.common.utils.Result;
import com.yibingo.race.security.common.GenericResponse;
import com.yibingo.race.security.common.ServiceError;
import com.yibingo.race.security.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: jamesluozhiwei
 * @description: 用户登录成功时返回给前端的数据
 */
@Component
@Slf4j
public class AjaxAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        //自定义login，不走这里、若使用security的formLogin则自己添加业务实现(生成token、存储token等等)
        response.getWriter().write(JSON.toJSONString(Result.success(" ",ServiceError.NORMAL.getMsg())));
    }
}