package com.yibingo.race.dal.putMapper;

import com.yibingo.race.dal.entity.Form;


import lombok.Data;
import java.util.Map;
import java.util.Date;

/**
 * 表单发布PutMapper
 *
 * @author Yang Xin
 * @email little-cy@qq.com
 * @date 2022-06-28 19:35:54
 */
@Data
public class FormPutMapper{

    private String createUserId;
    private Integer isEnd;
    private String title;
    private String notes;
    private Map<String,Object> labelCombination;
    private Map<String,Object> labelCombinationImg;
    private Integer healthyCode;
    private Integer travelCard;
    private String organizationId;
    private Date beginTime;
    private Date endTime;
    private Integer attentionMode;
    private Integer acidRequirement;
    private Integer scheduleRemind;

    public static Form convertToEntity(FormPutMapper putMapper){
        Form entity = new Form();
        entity.setCreateUserId(putMapper.getCreateUserId());
        entity.setIsEnd(putMapper.getIsEnd());
        entity.setTitle(putMapper.getTitle());
        entity.setNotes(putMapper.getNotes());
        entity.setLabelCombination(putMapper.getLabelCombination());
        entity.setLabelCombinationImg(putMapper.getLabelCombinationImg());
        entity.setHealthyCode(putMapper.getHealthyCode());
        entity.setTravelCard(putMapper.getTravelCard());
        entity.setOrganizationId(putMapper.getOrganizationId());
        entity.setBeginTime(putMapper.getBeginTime());
        entity.setEndTime(putMapper.getEndTime());
        entity.setAttentionMode(putMapper.getAttentionMode());
        entity.setAcidRequirement(putMapper.getAcidRequirement());
        entity.setScheduleRemind(putMapper.getScheduleRemind());
        return entity;
    }



}