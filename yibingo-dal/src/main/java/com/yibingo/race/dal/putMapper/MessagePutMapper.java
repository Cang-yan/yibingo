package com.yibingo.race.dal.putMapper;

import com.yibingo.race.dal.entity.Message;


import lombok.Data;
import java.util.Map;
import java.util.Date;

/**
 * 消息模板PutMapper
 *
 * @author Yang Xin
 * @email little-cy@qq.com
 * @date 2022-05-26 20:03:13
 */
@Data
public class MessagePutMapper{

    private String relationId;
    private String relationUserId;
    private String title;
    private String content;
    private Integer type;

    public static Message convertToEntity(MessagePutMapper putMapper){
        Message entity = new Message();
        entity.setRelationId(putMapper.getRelationId());
        entity.setRelationUserId(putMapper.getRelationUserId());
        entity.setTitle(putMapper.getTitle());
        entity.setContent(putMapper.getContent());
        entity.setType(putMapper.getType());
        return entity;
    }



}