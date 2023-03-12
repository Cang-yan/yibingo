package com.yibingo.race.dal.putMapper;

import com.yibingo.race.dal.entity.Vip;


import lombok.Data;
import java.util.Map;
import java.util.Date;

/**
 * 会员权益表PutMapper
 *
 * @author Yang Xin
 * @email little-cy@qq.com
 * @date 2022-05-26 20:03:13
 */
@Data
public class VipPutMapper{

    private Integer status;
    private Integer downloadAdd;
    private String expireTime;
    private String userId;

    public static Vip convertToEntity(VipPutMapper putMapper){
        Vip entity = new Vip();
        entity.setStatus(putMapper.getStatus());
        entity.setDownloadAdd(putMapper.getDownloadAdd());
        entity.setExpireTime(putMapper.getExpireTime());
        entity.setUserId(putMapper.getUserId());
        return entity;
    }



}