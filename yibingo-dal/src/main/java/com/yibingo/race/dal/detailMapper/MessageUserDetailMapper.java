package com.yibingo.race.dal.detailMapper;

import com.yibingo.race.dal.entity.MessageUser;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.Date;


/**
 * 通知用户表DetailMapper
 *
 * @author Yang Xin
 * @email little-cy@qq.com
 * @date 2022-05-26 20:03:13
 */
@Data
public class MessageUserDetailMapper{


    public static Map<String,Object> buildMap(MessageUser entity){
        Map<String,Object> map = new HashMap<>();
        map.put("id",entity.getId());
        map.put("createTime",entity.getCreateTime());
        map.put("updateTime",entity.getUpdateTime());
        map.put("userId",entity.getUserId());
        map.put("messageId",entity.getMessageId());
        map.put("status",entity.getStatus());
        map.put("applyHandle",entity.getApplyHandle());
        return map;
    }








}