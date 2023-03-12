package com.yibingo.race.dal.putMapper;

import com.yibingo.race.dal.entity.OrganizationSpecial;


import lombok.Data;
import java.util.Map;
import java.util.Date;

/**
 * 特殊群组PutMapper
 *
 * @author Yang Xin
 * @email little-cy@qq.com
 * @date 2022-05-26 20:03:13
 */
@Data
public class OrganizationSpecialPutMapper{

    private String title;

    public static OrganizationSpecial convertToEntity(OrganizationSpecialPutMapper putMapper){
        OrganizationSpecial entity = new OrganizationSpecial();
        entity.setTitle(putMapper.getTitle());
        return entity;
    }



}