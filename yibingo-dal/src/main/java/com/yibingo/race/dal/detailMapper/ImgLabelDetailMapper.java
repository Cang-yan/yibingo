package com.yibingo.race.dal.detailMapper;

import com.yibingo.race.dal.entity.ImgLabel;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.Date;


/**
 * 标签要求上传的图片DetailMapper
 *
 * @author Yang Xin
 * @email little-cy@qq.com
 * @date 2022-05-26 20:03:13
 */
@Data
public class ImgLabelDetailMapper{


    public static Map<String,Object> buildMap(ImgLabel entity){
        Map<String,Object> map = new HashMap<>();
        map.put("id",entity.getId());
        map.put("createTime",entity.getCreateTime());
        map.put("updateTime",entity.getUpdateTime());
        map.put("imgTitile",entity.getImgTitile());
        return map;
    }








}