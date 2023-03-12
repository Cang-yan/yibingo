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
 * 表单发布
 *
 * @author Yang Xin
 * @email little-cy@qq.com
 * @date 2022-06-28 19:35:54
 */
@Data
@TableName(value = "form",autoResultMap = true )
public class Form implements Serializable{
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
    * 创建人Id
    */
    private String createUserId;
    /**
    * 是否结束，0是没结束 1是结束
    */
    private Integer isEnd;
    /**
    * 表单标题
    */
    private String title;
    /**
    * 备注
    */
    private String notes;
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
    * 是否要健康码
    */
    private Integer healthyCode;
    /**
    * 是否要行程卡
    */
    private Integer travelCard;
    /**
    * 选择群组id
    */
    private String organizationId;
    /**
    * 开始时间
    */
    private Date beginTime;
    /**
    * 结束时间
    */
    private Date endTime;
    /**
    * 提醒方式，枚举
    */
    private Integer attentionMode;
    /**
    * 核酸要求
    */
    private Integer acidRequirement;
    /**
    * 定时任务的剩余提醒次数
    */
    private Integer scheduleRemind;

}
