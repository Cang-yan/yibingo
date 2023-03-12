package com.yibingo.race.dal.putMapper;

import com.yibingo.race.dal.entity.TupleTemp;


import lombok.Data;
import java.util.Map;
import java.util.Date;

/**
 * 暂时元组PutMapper
 *
 * @author Yang Xin
 * @email little-cy@qq.com
 * @date 2022-05-26 20:06:52
 */
@Data
public class TupleTempPutMapper{

    private String organizationId;
    private String createUserId;
    private String titile;
    private Integer num;

    public static TupleTemp convertToEntity(TupleTempPutMapper putMapper){
        TupleTemp entity = new TupleTemp();
        entity.setOrganizationId(putMapper.getOrganizationId());
        entity.setCreateUserId(putMapper.getCreateUserId());
        entity.setTitile(putMapper.getTitile());
        entity.setNum(putMapper.getNum());
        return entity;
    }



}