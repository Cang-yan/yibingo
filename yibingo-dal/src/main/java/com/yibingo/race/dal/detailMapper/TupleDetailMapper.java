package com.yibingo.race.dal.detailMapper;

import com.yibingo.race.dal.entity.Tuple;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.Date;


/**
 * 元组DetailMapper
 *
 * @author Yang Xin
 * @email little-cy@qq.com
 * @date 2022-05-26 20:03:13
 */
@Data
public class TupleDetailMapper{


    public static Map<String,Object> buildMap(Tuple entity){
        Map<String,Object> map = new HashMap<>();
        map.put("id",entity.getId());
        map.put("createTime",entity.getCreateTime());
        map.put("updateTime",entity.getUpdateTime());
        map.put("createUserId",entity.getCreateUserId());
        map.put("organizationId",entity.getOrganizationId());
        map.put("title",entity.getTitle());
        map.put("num",entity.getNum());
        return map;
    }








}