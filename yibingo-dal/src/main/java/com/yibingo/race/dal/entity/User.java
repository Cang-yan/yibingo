package com.yibingo.race.dal.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import com.yibingo.race.common.mybatis.handler.JacksonTypeHandler;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

import java.util.Map;


/**
 * 用户表
 *
 * @author Yang Xin
 * @email little-cy@qq.com
 * @date 2022-06-22 10:53:16
 */
@Data
@TableName(value = "user",autoResultMap = true )
public class User implements Serializable{
private static final long serialVersionUID=1L;

    /**
    * 
    */
    @TableId
    private String id;
    /**
    * 创建时间
    */
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /**
    * 更新时间
    */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    /**
    * 平台union_id
    */
    private String unionId;
    /**
    * 平台OpenId
    */
    private String openId;
    /**
    * 姓名
    */
    private String name;
    /**
    * 头像
    */
    private String head;
    /**
    * 剩余可下载次数
    */
    private Integer downloadNum;

}
