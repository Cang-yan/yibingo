package com.yibingo.race.dal.detailMapper;

import com.yibingo.race.dal.entity.FormCount;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.Date;


/**
 * 表单计数DetailMapper
 *
 * @author Yang Xin
 * @email little-cy@qq.com
 * @date 2022-05-26 20:03:13
 */
@Data
public class FormCountDetailMapper{


    public static Map<String,Object> buildMap(FormCount entity){
        Map<String,Object> map = new HashMap<>();
        map.put("id",entity.getId());
        map.put("createTime",entity.getCreateTime());
        map.put("updateTime",entity.getUpdateTime());
        map.put("formId",entity.getFormId());
        map.put("undone",entity.getUndone());
        map.put("done",entity.getDone());
        map.put("abnormal",entity.getAbnormal());
        return map;
    }








}