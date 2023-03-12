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
 * 表单记录
 *
 * @author Yang Xin
 * @email little-cy@qq.com
 * @date 2022-05-26 20:03:13
 */
@Data
@TableName(value = "form_records",autoResultMap = true )
public class FormRecords implements Serializable{
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
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    /**
    * 表单Id
    */
    private String formId;
    /**
    * 组织Id
    */
    private String organizationId;
    /**
    * 元组id
    */
    private String tupleId;
    /**
    * 用户id
    */
    private String userId;
    /**
    * 完成情况 0未完成 1已完成 2有异常
    */
    private Integer status;
    /**
    * 交文本标签组合方式
    */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private Map<String,Object> labelCombination;
    /**
    * 交图片的标签组合
    */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private Map<String,Object> labelCombinationImg;
    /**
    * 健康码url
    */
    private String healthyCodeUrl;
    /**
    * 行程卡url
    */
    private String travelCardUrl;

}
