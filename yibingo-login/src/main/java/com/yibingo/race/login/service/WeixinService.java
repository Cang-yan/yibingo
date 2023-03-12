package com.yibingo.race.login.service;

import cn.hutool.core.lang.UUID;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yibingo.race.common.utils.Result;
import com.yibingo.race.login.common.RedisKey;
import com.yibingo.race.login.entity.WXAuth;
import com.yibingo.race.login.util.WeChatUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author crush
 * @since 2021-09-14
 */
@Slf4j
@Service
public class WeixinService {


    public static final String APPID = "wx0a64c3eec086ba36";


    public static final String APPSECRET = "71ac82e112b3d90ac0f31552bfbc1ebd";

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private WeChatUtil weChatUtil;


    public JSONObject getSessionId(String code) {

        String url = "https://api.weixin.qq.com/sns/jscode2session?appid={0}&secret={1}&js_code={2}&grant_type=authorization_code";
        String replaceUrl = url.replace("{0}", APPID).replace("{1}", APPSECRET).replace("{2}", code);
        String res = HttpUtil.get(replaceUrl);
//        log.info("发送链接后获得的数据{}", res);
//        String s = UUID.randomUUID().toString();
//        redisTemplate.opsForValue().set(RedisKey.WX_SESSION_ID + s, res);
//        return s;
        //todo 把判空都改成这样
        if (StringUtils.isEmpty(res)) {
            return null;
        } else {
            return JSONObject.parseObject(res);
        }
    }


    public Result authLogin(WXAuth wxAuth) {
        try {
            String wxRes = weChatUtil.wxDecrypt(wxAuth.getEncryptedData(), wxAuth.getSessionId(), wxAuth.getIv());
            log.info("信息：" + wxRes);
            //WxUserInfo wxUserInfo = JSON.parseObject(wxRes, WxUserInfo.class);
            //这里是做业务操作的，理论上需要查询数据库，看这个用户信息是否存在，存在直接返回登录态，
            // 若不存在即添加进数据库，做持久化。（表建好了，相关依赖也添加了，发现这是demo.就... 你懂的哈）
            // 根据自己需求 更改
            //return Result.success(wxUserInfo);
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.failed();
    }
}