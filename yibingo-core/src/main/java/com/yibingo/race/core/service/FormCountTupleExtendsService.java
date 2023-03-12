package com.yibingo.race.core.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.base.CaseFormat;
import com.yibingo.race.dal.entity.FormCountTuple;
import com.yibingo.race.dal.filterMapper.FormCountTupleFilterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 表单元组统计扩展
 *
 * @author Yang Xin
 * @email little-cy@qq.com
 * @date 2022-05-18 16:44:25
 */
@Service
public class FormCountTupleExtendsService {

    @Autowired
    private FormCountTupleService formCountTupleService;

    public List<FormCountTuple> getListByFilter(FormCountTupleFilterMapper filterMapper) {

        QueryWrapper<FormCountTuple> wrapper = new QueryWrapper<>();

        if (filterMapper.orderBy != null) {
            for (String orderBy : filterMapper.orderBy) {
                int desc = orderBy.indexOf("desc" );
                int asc = orderBy.indexOf("asc" );
                if (desc != -1 && asc == -1) {
                    wrapper.orderByDesc(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, orderBy.substring(0, desc - 1)));
                }
                if (desc == -1 && asc != -1) {
                    wrapper.orderByAsc(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, orderBy.substring(0, asc - 1)));
                }
            }
        } else {
            wrapper.orderByDesc("create_time" );
        }

        if (filterMapper.updateTimeFrom != null) wrapper.ge("update_time" , new Date(filterMapper.updateTimeFrom));
        if (filterMapper.updateTimeTo != null) wrapper.le("update_time" , new Date(filterMapper.updateTimeTo));
        if (filterMapper.createTimeFrom != null) wrapper.ge("create_time" , new Date(filterMapper.createTimeFrom));
        if (filterMapper.createTimeTo != null) wrapper.le("create_time" , new Date(filterMapper.createTimeTo));
        if (filterMapper.formId != null) wrapper.eq("form_id" , filterMapper.formId);
        if (filterMapper.formIdIn != null) wrapper.in("form_id" , filterMapper.formIdIn);
        if (filterMapper.tupleId != null) wrapper.eq("tuple_id" , filterMapper.tupleId);
        if (filterMapper.tupleIdIn != null) wrapper.in("tuple_id" , filterMapper.tupleIdIn);

        Long page = 1L;
        Long row = -1L;
        if (filterMapper.page != null) page = filterMapper.page;
        if (filterMapper.row != null) row = filterMapper.row;

        Page<FormCountTuple> markPage = new Page<>(page, row);

        Page<FormCountTuple> resultList = formCountTupleService.page(markPage, wrapper);

        return resultList.getRecords();
    }


}