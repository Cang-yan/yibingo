package com.yibingo.race.dal.putMapper;

import com.yibingo.race.dal.entity.Tuple;


import lombok.Data;
import java.util.Map;
import java.util.Date;

/**
 * 元组PutMapper
 *
 * @author Yang Xin
 * @email little-cy@qq.com
 * @date 2022-05-26 20:03:13
 */
@Data
public class TuplePutMapper{

    private String createUserId;
    private String organizationId;
    private String title;
    private Integer num;

    public static Tuple convertToEntity(TuplePutMapper putMapper){
        Tuple entity = new Tuple();
        entity.setCreateUserId(putMapper.getCreateUserId());
        entity.setOrganizationId(putMapper.getOrganizationId());
        entity.setTitle(putMapper.getTitle());
        entity.setNum(putMapper.getNum());
        return entity;
    }



}