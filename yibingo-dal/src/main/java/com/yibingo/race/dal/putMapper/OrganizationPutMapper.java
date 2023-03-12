package com.yibingo.race.dal.putMapper;

import com.yibingo.race.dal.entity.Organization;


import lombok.Data;
import java.util.Map;
import java.util.Date;

/**
 * 群组PutMapper
 *
 * @author Yang Xin
 * @email little-cy@qq.com
 * @date 2022-05-26 20:03:13
 */
@Data
public class OrganizationPutMapper{

    private String title;
    private String createUserId;
    private Integer type;
    private String notes;
    private String head;

    public static Organization convertToEntity(OrganizationPutMapper putMapper){
        Organization entity = new Organization();
        entity.setTitle(putMapper.getTitle());
        entity.setCreateUserId(putMapper.getCreateUserId());
        entity.setType(putMapper.getType());
        entity.setNotes(putMapper.getNotes());
        entity.setHead(putMapper.getHead());
        return entity;
    }



}