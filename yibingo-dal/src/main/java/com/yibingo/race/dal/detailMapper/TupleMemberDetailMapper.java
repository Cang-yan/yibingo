package com.yibingo.race.dal.detailMapper;

import com.yibingo.race.dal.entity.TupleMember;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.Date;


/**
 * 元组成员信息DetailMapper
 *
 * @author Yang Xin
 * @email little-cy@qq.com
 * @date 2022-05-26 20:03:13
 */
@Data
public class TupleMemberDetailMapper{


    public static Map<String,Object> buildMap(TupleMember entity){
        Map<String,Object> map = new HashMap<>();
        map.put("id",entity.getId());
        map.put("createTime",entity.getCreateTime());
        map.put("updateTime",entity.getUpdateTime());
        map.put("organizationId",entity.getOrganizationId());
        map.put("tupleId",entity.getTupleId());
        map.put("userId",entity.getUserId());
        map.put("name",entity.getName());
        map.put("isFake",entity.getIsFake());
        map.put("head",entity.getHead());
        return map;
    }








}