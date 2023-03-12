package com.yibingo.race.core.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yibingo.race.dal.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yibingo.race.dal.entity.TupleMember;
import com.yibingo.race.dal.filterMapper.TupleMemberFilterMapper;
import com.google.common.base.CaseFormat;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;


import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 元组成员信息扩展
 *
 * @author Yang Xin
 * @email little-cy@qq.com
 * @date 2022-05-15 18:42:55
 */
@Service
public class TupleMemberExtendsService {

    @Autowired
    TupleMemberService tupleMemberService;


    @Autowired
    UserService userService;

    /*
    * 通过成员信息，拿到用户信息
    * */
    public List<User> getUserByMember(List<TupleMember> tupleMembers){

        List<String> userIdList = tupleMembers.stream().map(TupleMember::getUserId).collect(Collectors.toList());
        List<User> userList = userService.listByIds(userIdList);

        return userList;
    }



    public List<TupleMember> getListByFilter(TupleMemberFilterMapper filterMapper) {

        QueryWrapper<TupleMember> wrapper = new QueryWrapper<>();

        if (filterMapper.orderBy != null) {
            for (String orderBy : filterMapper.orderBy) {
                int desc = orderBy.indexOf("desc");
                int asc = orderBy.indexOf("asc");
                if (desc != -1 && asc == -1) {
                    wrapper.orderByDesc(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, orderBy.substring(0, desc - 1)));
                }
                if (desc == -1 && asc != -1) {
                    wrapper.orderByAsc(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, orderBy.substring(0, asc - 1)));
                }
            }
        } else {
            wrapper.orderByDesc("create_time");
        }

        if (filterMapper.updateTimeFrom != null) wrapper.ge("update_time" , new Date(filterMapper.updateTimeFrom));
        if (filterMapper.updateTimeTo != null) wrapper.le("update_time" , new Date(filterMapper.updateTimeTo));
        if (filterMapper.createTimeFrom != null) wrapper.ge("create_time" , new Date(filterMapper.createTimeFrom));
        if (filterMapper.createTimeTo != null) wrapper.le("create_time" , new Date(filterMapper.createTimeTo));
        if (filterMapper.userId != null) wrapper.eq("user_id",filterMapper.userId);
        if (filterMapper.organizationId != null) wrapper.eq("organization_id",filterMapper.organizationId);
        if (filterMapper.tupleId != null) wrapper.eq("tuple_id",filterMapper.tupleId);
        if (filterMapper.tupleIdIn != null) wrapper.in("tuple_id",filterMapper.tupleIdIn);

        Long page = 1L;
        Long row = -1L;
        if (filterMapper.page != null) page = filterMapper.page;
        if (filterMapper.row != null) row = filterMapper.row;

        Page<TupleMember> markPage = new Page<>(page, row);

        Page<TupleMember> resultList = tupleMemberService.page(markPage, wrapper);

        return resultList.getRecords();
    }


}