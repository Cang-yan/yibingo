package com.yibingo.race.dal.detailMapper;

import com.yibingo.race.dal.entity.TupleTemp;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.Date;


/**
 * 暂时元组DetailMapper
 *
 * @author Yang Xin
 * @email little-cy@qq.com
 * @date 2022-05-26 20:06:52
 */
@Data
public class TupleTempDetailMapper{


    public static Map<String,Object> buildMap(TupleTemp entity){
        Map<String,Object> map = new HashMap<>();
        map.put("id",entity.getId());
        map.put("createTime",entity.getCreateTime());
        map.put("updateTime",entity.getUpdateTime());
        map.put("organizationId",entity.getOrganizationId());
        map.put("createUserId",entity.getCreateUserId());
        map.put("titile",entity.getTitile());
        map.put("num",entity.getNum());
        return map;
    }








}