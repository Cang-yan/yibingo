package com.yibingo.race.dal.filterMapper;

import java.util.List;

/**
 *
 * @author Yang Xin
 * @email little-cy@qq.com
 * @date 2022-05-15 18:42:55
 */
public class FormFilterMapper{
    public Long updateTimeFrom = null;

    public Long updateTimeTo = null;


    public Long createTimeFrom = null;

    public Long createTimeTo = null;

    public List<String> orderBy = null;

    public Long page = null;

    public Long row = null;

    public String createUserId;

    public Integer isEnd;

    public Integer scheduleRemindFrom;

    public String organizationId;

}