package com.yibingo.race.dal.putMapper;

import com.yibingo.race.dal.entity.MessageUser;


import lombok.Data;
import java.util.Map;
import java.util.Date;

/**
 * 通知用户表PutMapper
 *
 * @author Yang Xin
 * @email little-cy@qq.com
 * @date 2022-05-26 20:03:13
 */
@Data
public class MessageUserPutMapper{

    private String userId;
    private String messageId;
    private Integer status;
    private Integer applyHandle;

    public static MessageUser convertToEntity(MessageUserPutMapper putMapper){
        MessageUser entity = new MessageUser();
        entity.setUserId(putMapper.getUserId());
        entity.setMessageId(putMapper.getMessageId());
        entity.setStatus(putMapper.getStatus());
        entity.setApplyHandle(putMapper.getApplyHandle());
        return entity;
    }



}