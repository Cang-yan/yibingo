package com.yibingo.race.dal.filterMapper;

import java.util.List;

/**
 *
 * @author Yang Xin
 * @email little-cy@qq.com
 * @date 2022-05-18 16:44:25
 */
public class FormCountTupleFilterMapper{
    public Long updateTimeFrom = null;

    public Long updateTimeTo = null;


    public Long createTimeFrom = null;

    public Long createTimeTo = null;

    public List<String> orderBy = null;

    public Long page = null;

    public Long row = null;

    public String formId;

    public List<String> formIdIn;

    public String tupleId;

    public List<String> tupleIdIn;

}