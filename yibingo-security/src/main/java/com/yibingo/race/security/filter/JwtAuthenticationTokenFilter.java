package com.yibingo.race.security.filter;

import com.alibaba.fastjson.JSON;

import com.yibingo.race.common.utils.Result;
import com.yibingo.race.security.common.GenericResponse;
import com.yibingo.race.security.common.ServiceError;
import com.yibingo.race.security.entity.User;
import com.yibingo.race.security.service.SelfUserDetailsService;
import com.yibingo.race.security.util.JwtTokenUtil;
import com.yibingo.race.security.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

/**
 * @author: jamesluozhiwei
 * @description: 确保在一次请求只通过一次filter，而不需要重复执行
 */
@Component
@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Value("${token.expirationMilliSeconds}")
    private long expirationMilliSeconds;

    @Autowired
    SelfUserDetailsService userDetailsService;

    @Autowired
    RedisUtil redisUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        response.setCharacterEncoding("utf-8");
        if (null == authHeader || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request,response);//token格式不正确
            return;
        }
        String authToken = authHeader.substring("Bearer ".length());

        String subject = JwtTokenUtil.parseToken(authToken);//获取在token中自定义的subject，用作用户标识，用来获取用户权限

        //获取redis中的token信息

        if (!redisUtil.hasKey(authToken)){
            //token 不存在 返回错误信息
            response.getWriter().write(JSON.toJSONString(Result.failed(ServiceError.GLOBAL_ERR_NO_SIGN_IN.getCode(),ServiceError.GLOBAL_ERR_NO_SIGN_IN.getMsg())));
            return;
        }

        //获取缓存中的信息(根据自己的业务进行 todo 拓展)
        HashMap<String,Object> hashMap = (HashMap<String, Object>) redisUtil.hget(authToken);
        //从tokenInfo中取出用户信息
        User user = new User();
        user.setId(Long.parseLong(hashMap.get("id").toString())).setAuthorities((Set<? extends GrantedAuthority>) hashMap.get("authorities"));
        if (null == hashMap){
            //用户信息不存在或转换错误，返回错误信息
            response.getWriter().write(JSON.toJSONString(Result.failed(ServiceError.GLOBAL_ERR_NO_SIGN_IN.getCode(),ServiceError.GLOBAL_ERR_NO_SIGN_IN.getMsg())));
            return;
        }
        //更新token过期时间
        redisUtil.setKeyExpire(authToken,expirationMilliSeconds);
        //将信息交给security
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request,response);
    }
}