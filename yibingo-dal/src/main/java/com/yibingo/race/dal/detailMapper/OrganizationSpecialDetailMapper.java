package com.yibingo.race.dal.detailMapper;

import com.yibingo.race.dal.entity.OrganizationSpecial;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.Date;


/**
 * 特殊群组DetailMapper
 *
 * @author Yang Xin
 * @email little-cy@qq.com
 * @date 2022-05-26 20:03:13
 */
@Data
public class OrganizationSpecialDetailMapper{


    public static Map<String,Object> buildMap(OrganizationSpecial entity){
        Map<String,Object> map = new HashMap<>();
        map.put("id",entity.getId());
        map.put("createTime",entity.getCreateTime());
        map.put("updateTime",entity.getUpdateTime());
        map.put("title",entity.getTitle());
        return map;
    }








}