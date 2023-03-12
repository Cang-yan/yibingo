package com.yibingo.race.dal.putMapper;

import com.yibingo.race.dal.entity.User;


import lombok.Data;
import java.util.Map;
import java.util.Date;

/**
 * 用户表PutMapper
 *
 * @author Yang Xin
 * @email little-cy@qq.com
 * @date 2022-06-22 10:53:16
 */
@Data
public class UserPutMapper{

    private String unionId;
    private String openId;
    private String name;
    private String head;
    private Integer downloadNum;

    public static User convertToEntity(UserPutMapper putMapper){
        User entity = new User();
        entity.setUnionId(putMapper.getUnionId());
        entity.setOpenId(putMapper.getOpenId());
        entity.setName(putMapper.getName());
        entity.setHead(putMapper.getHead());
        entity.setDownloadNum(putMapper.getDownloadNum());
        return entity;
    }



}