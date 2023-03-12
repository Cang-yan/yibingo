package com.yibingo.race.dal.detailMapper;

import com.yibingo.race.dal.entity.User;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.Date;


/**
 * 用户表DetailMapper
 *
 * @author Yang Xin
 * @email little-cy@qq.com
 * @date 2022-06-22 10:53:16
 */
@Data
public class UserDetailMapper{


    public static Map<String,Object> buildMap(User entity){
        Map<String,Object> map = new HashMap<>();
        map.put("id",entity.getId());
        map.put("createTime",entity.getCreateTime());
        map.put("updateTime",entity.getUpdateTime());
        map.put("unionId",entity.getUnionId());
        map.put("openId",entity.getOpenId());
        map.put("name",entity.getName());
        map.put("head",entity.getHead());
        map.put("downloadNum",entity.getDownloadNum());
        return map;
    }








}