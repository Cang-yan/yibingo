package com.yibingo.race.security.service;


import com.yibingo.race.security.common.GenericResponse;

import java.util.Map;

/**
 * 微信业务接口
 */
public interface WeChatService {

    /**
     * 小程序登录
     * @param code
     * @return
     */
    Map<String,Object> wxLogin(String code,String nickname,String head)throws Exception;

}
