package com.yibingo.race.security.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.yibingo.race.common.utils.Result;
import com.yibingo.race.core.service.UserExtendsService;
import com.yibingo.race.core.service.UserService;
import com.yibingo.race.dal.filterMapper.UserFilterMapper;
import com.yibingo.race.security.common.ServiceError;
import com.yibingo.race.security.entity.User;
import com.yibingo.race.security.service.WeChatService;
import com.yibingo.race.security.util.Jcode2SessionUtil;
import com.yibingo.race.security.util.JwtTokenUtil;
import com.yibingo.race.security.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 微信业务实现类
 */
@Service
@Slf4j
public class WeChatServiceImpl implements WeChatService {

    @Value("${weChat.appid}")
    private String appid;

    @Value("${weChat.secret}")
    private String secret;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private UserExtendsService userExtendsService;

    @Override
    public Map<String, Object> wxLogin(String code, String nickname, String head) throws Exception {
        JSONObject sessionInfo = JSONObject.parseObject(jcode2Session(code));

        Assert.notNull(sessionInfo, "code 无效");

        //Assert.isTrue(0 == sessionInfo.getInteger("errcode"),sessionInfo.getString("errmsg"));

        // 获取用户唯一标识符 openid成功
        String openId = sessionInfo.getString("openid");
        //String unionId = sessionInfo.getString("unionId");

        //todo 自定义用户数据
        UserFilterMapper userFilterMapper = new UserFilterMapper();
        userFilterMapper.openId = openId;
        com.yibingo.race.dal.entity.User userEntity = userExtendsService.getListByFilter(userFilterMapper).stream().findFirst().orElse(null);

        if (userEntity == null) {
            userEntity = new com.yibingo.race.dal.entity.User();
            userEntity.setOpenId(openId);
            //  userEntity.setUnionId(unionId);
            userEntity.setHead(head);
            userEntity.setName(nickname);
            userService.save(userEntity);
        }
        // 模拟从数据库获取用户信息
        User user = new User();
        user.setId(Long.valueOf(userEntity.getId()));
        Set authoritiesSet = new HashSet();
        // 模拟从数据库中获取用户权限
        authoritiesSet.add(new SimpleGrantedAuthority("test:add"));
        authoritiesSet.add(new SimpleGrantedAuthority("test:list"));
        authoritiesSet.add(new SimpleGrantedAuthority("ddd:list"));
        user.setAuthorities(authoritiesSet);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("id", user.getId().toString());
        hashMap.put("authorities", authoritiesSet);
        String token = JwtTokenUtil.generateToken(user);
        redisUtil.hset(token, hashMap);

        Map<String, Object> returnMap = new HashMap<>();
        returnMap.put("token", token);
        returnMap.put("userId", userEntity.getId());
        return Result.success(returnMap, ServiceError.NORMAL.getMsg()).map();
    }

    /**
     * 登录凭证校验
     *
     * @param code
     * @return
     * @throws Exception
     */
    private String jcode2Session(String code) throws Exception {
        String sessionInfo = Jcode2SessionUtil.jscode2session(appid, secret, code, "authorization_code");//登录grantType固定
        log.info(sessionInfo);
        return sessionInfo;
    }
}
