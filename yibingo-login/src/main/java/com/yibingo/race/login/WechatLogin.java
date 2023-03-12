package com.yibingo.race.login;

import com.alibaba.fastjson.JSONObject;

import java.io.IOException;

/**
 * @description:
 * @author: Yang Xin
 * @time: 2022/5/16 15:39
 */
public class WechatLogin {
    /*
     *第一步，前端调接口，获取code
     *
     * */


    /*
     * 第二步，获取微信授权信息
     * */
   /* public static JSONObject getToken(String code) throws IOException {
        //通过第一步获得的code获取微信授权信息
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + AuthUtil.APPID + "&secret="
                + AuthUtil.APPSECRET + "&code=" + code + "&grant_type=authorization_code";
        JSONObject jsonObject = AuthUtil.doGetJson(url);
        return jsonObject;
    }*/

    /*
     * 第三步，实现登录，获取用户信息
     * */
   /* public static JSONObject getLoginInfo(JSONObject jsonObject) throws IOException {

        String openid = jsonObject.getString("openid");
        String token = jsonObject.getString("access_token");
        String infoUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=" + token + "&openid=" + openid
                + "&lang=zh_CN";
        JSONObject userInfo = AuthUtil.doGetJson(infoUrl);
        return userInfo;
    }*/

}
