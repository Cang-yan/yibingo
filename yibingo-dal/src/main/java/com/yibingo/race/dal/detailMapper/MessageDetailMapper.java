package com.yibingo.race.dal.detailMapper;

import com.yibingo.race.dal.entity.Message;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.Date;


/**
 * 消息模板DetailMapper
 *
 * @author Yang Xin
 * @email little-cy@qq.com
 * @date 2022-05-26 20:03:13
 */
@Data
public class MessageDetailMapper{


    public static Map<String,Object> buildMap(Message entity){
        Map<String,Object> map = new HashMap<>();
        map.put("id",entity.getId());
        map.put("createTime",entity.getCreateTime());
        map.put("updateTime",entity.getUpdateTime());
        map.put("relationId",entity.getRelationId());
        map.put("relationUserId",entity.getRelationUserId());
        map.put("title",entity.getTitle());
        map.put("content",entity.getContent());
        map.put("type",entity.getType());
        return map;
    }








}