package com.yibingo.race.dal.putMapper;

import com.yibingo.race.dal.entity.ImgLabel;


import lombok.Data;
import java.util.Map;
import java.util.Date;

/**
 * 标签要求上传的图片PutMapper
 *
 * @author Yang Xin
 * @email little-cy@qq.com
 * @date 2022-05-26 20:03:13
 */
@Data
public class ImgLabelPutMapper{

    private String imgTitile;

    public static ImgLabel convertToEntity(ImgLabelPutMapper putMapper){
        ImgLabel entity = new ImgLabel();
        entity.setImgTitile(putMapper.getImgTitile());
        return entity;
    }



}