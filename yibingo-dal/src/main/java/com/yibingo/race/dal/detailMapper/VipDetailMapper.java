package com.yibingo.race.dal.detailMapper;

import com.yibingo.race.dal.entity.Vip;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.Date;


/**
 * 会员权益表DetailMapper
 *
 * @author Yang Xin
 * @email little-cy@qq.com
 * @date 2022-05-26 20:03:13
 */
@Data
public class VipDetailMapper{


    public static Map<String,Object> buildMap(Vip entity){
        Map<String,Object> map = new HashMap<>();
        map.put("id",entity.getId());
        map.put("createTime",entity.getCreateTime());
        map.put("updateTime",entity.getUpdateTime());
        map.put("status",entity.getStatus());
        map.put("downloadAdd",entity.getDownloadAdd());
        map.put("expireTime",entity.getExpireTime());
        map.put("userId",entity.getUserId());
        return map;
    }








}