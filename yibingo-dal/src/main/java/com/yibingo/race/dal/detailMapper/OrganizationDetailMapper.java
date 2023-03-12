package com.yibingo.race.dal.detailMapper;

import com.yibingo.race.dal.entity.Organization;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.Date;


/**
 * 群组DetailMapper
 *
 * @author Yang Xin
 * @email little-cy@qq.com
 * @date 2022-05-26 20:03:13
 */
@Data
public class OrganizationDetailMapper{


    public static Map<String,Object> buildMap(Organization entity){
        Map<String,Object> map = new HashMap<>();
        map.put("id",entity.getId());
        map.put("createTime",entity.getCreateTime());
        map.put("updateTime",entity.getUpdateTime());
        map.put("title",entity.getTitle());
        map.put("createUserId",entity.getCreateUserId());
        map.put("type",entity.getType());
        map.put("notes",entity.getNotes());
        map.put("head",entity.getHead());
        return map;
    }








}