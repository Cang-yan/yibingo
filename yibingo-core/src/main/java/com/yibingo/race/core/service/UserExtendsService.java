package com.yibingo.race.core.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.base.CaseFormat;
import com.yibingo.race.common.exception.BaseException;
import com.yibingo.race.dal.entity.User;
import com.yibingo.race.dal.filterMapper.UserFilterMapper;
import com.yibingo.race.login.WechatLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 用户表扩展
 *
 * @author Yang Xin
 * @email little-cy@qq.com
 * @date 2022-05-15 18:42:55
 */
@Service
public class UserExtendsService {

    @Autowired
    private UserService userService;


    /*
     * 登录,如果没有用户记录就添加一个
     * */
    public String login(String code) {
        try {
           /* JSONObject jsonObject = WechatLogin.getToken(code);
            JSONObject userInfo = WechatLogin.getLoginInfo(jsonObject);
            String openId = jsonObject.getString("openid" );
            String userId;
            if (userInfo == null || openId == null) throw new BaseException("授权失败" );
            //todo 考虑一下  创建元组成员的时候，需要设置头像的值，以及这里需要考虑虚账号变成实账号的情况
            UserFilterMapper userFilterMapper = new UserFilterMapper();
            userFilterMapper.openId = openId;
            User user = getListByFilter(userFilterMapper).stream().findFirst().orElse(null);
            if (user == null) {
                user = new User();
                user.setUnionId(userInfo.getString("unionid" ));
                user.setOpenId(openId);
                user.setName(userInfo.getString("nickname" ));
                user.setHead(userInfo.getString("headimgurl" ));
                userService.save(user);
            }
            return user.getId();*/

        } catch (Exception e) {
            throw new BaseException("LOGIN_FAILED" );
        }

        return " ";
    }

    public List<User> getListByFilter(UserFilterMapper filterMapper) {

        QueryWrapper<User> wrapper = new QueryWrapper<>();

        if (filterMapper.orderBy != null) {
            for (String orderBy : filterMapper.orderBy) {
                int desc = orderBy.indexOf("desc" );
                int asc = orderBy.indexOf("asc" );
                if (desc != -1 && asc == -1) {
                    wrapper.orderByDesc(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, orderBy.substring(0, desc - 1)));
                }
                if (desc == -1 && asc != -1) {
                    wrapper.orderByAsc(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, orderBy.substring(0, asc - 1)));
                }
            }
        } else {
            wrapper.orderByDesc("create_time" );
        }

        if (filterMapper.updateTimeFrom != null) wrapper.ge("update_time" , new Date(filterMapper.updateTimeFrom));
        if (filterMapper.updateTimeTo != null) wrapper.le("update_time" , new Date(filterMapper.updateTimeTo));
        if (filterMapper.createTimeFrom != null) wrapper.ge("create_time" , new Date(filterMapper.createTimeFrom));
        if (filterMapper.createTimeTo != null) wrapper.le("create_time" , new Date(filterMapper.createTimeTo));
        if (filterMapper.openId != null) wrapper.eq("open_id" , filterMapper.openId);

        Long page = 1L;
        Long row = -1L;
        if (filterMapper.page != null) page = filterMapper.page;
        if (filterMapper.row != null) row = filterMapper.row;

        Page<User> markPage = new Page<>(page, row);

        Page<User> resultList = userService.page(markPage, wrapper);

        return resultList.getRecords();
    }


}