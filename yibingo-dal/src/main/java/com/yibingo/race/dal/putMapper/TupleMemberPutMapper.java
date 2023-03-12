package com.yibingo.race.dal.putMapper;

import com.yibingo.race.dal.entity.TupleMember;


import lombok.Data;
import java.util.Map;
import java.util.Date;

/**
 * 元组成员信息PutMapper
 *
 * @author Yang Xin
 * @email little-cy@qq.com
 * @date 2022-05-26 20:03:13
 */
@Data
public class TupleMemberPutMapper{

    private String organizationId;
    private String tupleId;
    private String userId;
    private String name;
    private Integer isFake;
    private String head;

    public static TupleMember convertToEntity(TupleMemberPutMapper putMapper){
        TupleMember entity = new TupleMember();
        entity.setOrganizationId(putMapper.getOrganizationId());
        entity.setTupleId(putMapper.getTupleId());
        entity.setUserId(putMapper.getUserId());
        entity.setName(putMapper.getName());
        entity.setIsFake(putMapper.getIsFake());
        entity.setHead(putMapper.getHead());
        return entity;
    }



}