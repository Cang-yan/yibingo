package com.yibingo.race.core.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.base.CaseFormat;
import com.yibingo.race.common.exception.BaseException;
import com.yibingo.race.core.service.enums.MessageEnum;
import com.yibingo.race.core.service.enums.MessageUserEnum;
import com.yibingo.race.dal.detailMapper.TupleDetailMapper;
import com.yibingo.race.dal.detailMapper.TupleMemberDetailMapper;
import com.yibingo.race.dal.entity.*;
import com.yibingo.race.dal.filterMapper.OrganizationFilterMapper;
import com.yibingo.race.dal.filterMapper.TupleFilterMapper;
import com.yibingo.race.dal.filterMapper.TupleMemberFilterMapper;
import com.yibingo.race.dal.putMapper.OrganizationPutMapper;
import com.yibingo.race.dal.putMapper.TupleMemberPutMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 群组扩展
 *
 * @author Yang Xin
 * @email little-cy@qq.com
 * @date 2022-05-15 18:42:55
 */
@Service
public class OrganizationExtendsService {

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private TupleMemberExtendsService tupleMemberExtendsService;

    @Autowired
    private TupleMemberService tupleMemberService;

    @Autowired
    private TupleService tupleService;

    @Autowired
    private TupleExtendsService tupleExtendsService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserExtendsService userExtendsService;

    @Autowired
    private MessageExtendsService messageExtendsService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private MessageUserService messageUserService;

    @Autowired
    private FormExtendsService formExtendsService;


    /*
     *查询所有群组
     *
     * */
    public Map<String, Object> getAllOrganization(String userId) {
        OrganizationFilterMapper organizationFilterMapper = new OrganizationFilterMapper();
        organizationFilterMapper.createUserId = userId;
        List<Organization> myOrganization = getListByFilter(organizationFilterMapper);
        if (myOrganization.isEmpty()) myOrganization = new ArrayList<>();
        TupleMemberFilterMapper tupleMemberFilterMapper = new TupleMemberFilterMapper();
        tupleMemberFilterMapper.userId = userId;
        List<TupleMember> tupleMemberList = tupleMemberExtendsService.getListByFilter(tupleMemberFilterMapper);
        List<Organization> joinOrganization = new ArrayList<>();
        if (!tupleMemberList.isEmpty()) {
            List<String> joinOrganizationId = tupleMemberList.stream().map(TupleMember::getOrganizationId).collect(Collectors.toList());
            joinOrganization = organizationService.listByIds(joinOrganizationId);
        }
        Map<String, Object> organizationMap = new HashMap<>();
        organizationMap.put("myOrganization", myOrganization);
        organizationMap.put("joinOrganization", joinOrganization);
        return organizationMap;
    }

    /*
     * 从组织拿元组的list
     * */
    public List<Tuple> getTupleByOrganization(String organizationId) {
        TupleFilterMapper tupleFilterMapper = new TupleFilterMapper();
        tupleFilterMapper.organizationId = organizationId;
        List<Tuple> tupleList = tupleExtendsService.getListByFilter(tupleFilterMapper);
        return tupleList;
    }

    /*
     *从元组拿元组成员的list
     * */
    public List<TupleMember> getMemberByTuple(List<Tuple> tupleList) {
        List<String> tupleIdList = tupleList.stream().map(Tuple::getId).collect(Collectors.toList());
        TupleMemberFilterMapper tupleMemberFilterMapper = new TupleMemberFilterMapper();
        tupleMemberFilterMapper.tupleIdIn = tupleIdList;
        List<TupleMember> tupleMemberList = tupleMemberExtendsService.getListByFilter(tupleMemberFilterMapper);
        return tupleMemberList;
    }

    /*
     * 查询群组的成员组成，都要有但是元组成员挨着
     * */
    //todo 这里估计有能重构优化的地方，到时看吧
    public List<Map<String, Object>> getOrganizationMember(String organizationId) {
        List<Map<String, Object>> endMapList = new ArrayList<>();

        List<Tuple> tupleList = getTupleByOrganization(organizationId);
        if (tupleList.isEmpty()) return new ArrayList<>();
        List<TupleMember> tupleMemberList = getMemberByTuple(tupleList);

        Integer count = tupleMemberList.size();
        for (Tuple modelEntity : tupleList) {
            Map<String, Object> tupleDetailMap = new HashMap<>();
            List<TupleMember> tupleMembers = tupleMemberList.stream().filter(
                    item -> modelEntity.getId() != null && modelEntity.getId().equals(item.getTupleId())
            ).collect(Collectors.toList());
            tupleDetailMap.putAll(TupleDetailMapper.buildMap(modelEntity));
            List<Map<String, Object>> tupleMemberMapList = new ArrayList<>();

            for (TupleMember tupleMember : tupleMembers) {
                Map<String, Object> tupleMemberMap = TupleMemberDetailMapper.buildMap(tupleMember);
                tupleMemberMapList.add(tupleMemberMap);
            }
            tupleDetailMap.put("tupleMember", tupleMemberMapList);
            endMapList.add(tupleDetailMap);
        }
        Map<String, Object> countMap = new HashMap<>();
        countMap.put("count", count);
        endMapList.add(countMap);
        return endMapList;
    }

    /*
     * 获取我所在的元组信息
     * */
    public Map<String, Object> getMyTupleDetailMap(String userId, String organizationId) {
        TupleMemberFilterMapper tupleMemberFilterMapper = new TupleMemberFilterMapper();
        tupleMemberFilterMapper.userId = userId;
        tupleMemberFilterMapper.organizationId = organizationId;
        List<TupleMember> tupleMemberList = tupleMemberExtendsService.getListByFilter(tupleMemberFilterMapper);
        if (tupleMemberList.isEmpty()) new ArrayList<>();
        String tupleId = tupleMemberList.stream().findAny().orElse(null).getTupleId();
        tupleMemberFilterMapper = new TupleMemberFilterMapper();
        tupleMemberFilterMapper.tupleId = tupleId;
        List<TupleMember> tupleMembers = tupleMemberExtendsService.getListByFilter(tupleMemberFilterMapper);


        Map<String, Object> myTurple = new HashMap<>();
        Tuple tuple = tupleService.getById(tupleId);
        myTurple.putAll(TupleDetailMapper.buildMap(tuple));
        List<Map<String, Object>> memberDetailMapList = new ArrayList<>();
        for (TupleMember tupleMember : tupleMembers) {
            int isCreateUser = 0;
            int isMe = 0;
            if (tupleMember.getUserId().equals(tuple.getCreateUserId())) isCreateUser = 1;
            if (tupleMember.getUserId().equals(userId)) isMe = 1;
            Map<String, Object> memberMap = new HashMap<>();
            memberMap.putAll(TupleMemberDetailMapper.buildMap(tupleMember));
            memberMap.put("isCreateUser", isCreateUser);
            memberMap.put("isMe", isMe);

            memberDetailMapList.add(memberMap);
        }
        myTurple.put("memberInfo", memberDetailMapList);
        return myTurple;
    }

    /*
     * 创建群组
     *
     *
     * */
    public void createOrgazition(OrganizationPutMapper organizationPutMapper) {
        Organization organization = OrganizationPutMapper.convertToEntity(organizationPutMapper);
        organizationService.save(organization);
    }
/*
* 申请加入群组-创建元组
* 创建一个暂时的元组，创建一个通知
* */
public void applyCreateTuple(String organizationId, String userId, String tupleTitle){
    /*TupleTemp tupleTemp = new Tuple();
    tupleTemp.setOrganizationId(organizationId);
    tupleTemp.setTitle(tupleTitle);
    tupleTemp.setCreateUserId(userId);
    tupleService.save(tupleTemp);*/
    // 创建一个申请的通知

}



    /*
     * 这里还要修改审批态
     * 审批同意加入群组，但是是创建元组，或者被邀请
     * */
    public void agreeAndCreateTuple(String messageId,String organizationId, String userId, String tupleTitle, String name, String head) {
        Message message = messageService.getById(messageId);
        message.setType(MessageUserEnum.ACCESS.getStatus());
        Tuple tuple = new Tuple();
        tuple.setOrganizationId(organizationId);
        tuple.setTitle(tupleTitle);
        tuple.setCreateUserId(userId);
        tupleService.save(tuple);
        TupleMember tupleMember = new TupleMember();
        tupleMember.setHead(head);
        tupleMember.setUserId(userId);
        tupleMember.setTupleId(tuple.getId());
        tupleMember.setOrganizationId(organizationId);
        tupleMember.setName(name);
        tupleMemberService.save(tupleMember);
        //todo 创建通知，一会再写


    }
    /*
     * 邀请——直接进群
     *
     * */
    public void unlessAgreeAndCreateTuple(String organizationId, String userId, String tupleTitle, String name, String head) {
        TupleMemberFilterMapper tupleMemberFilterMapper = new TupleMemberFilterMapper();
        tupleMemberFilterMapper.organizationId = organizationId;
        tupleMemberFilterMapper.userId = userId;
        List<TupleMember> tupleMemberList = tupleMemberExtendsService.getListByFilter(tupleMemberFilterMapper);
        if (!tupleMemberList.isEmpty()) throw new BaseException("群中已经存在您的信息");
        Tuple tuple = new Tuple();
        tuple.setOrganizationId(organizationId);
        tuple.setTitle(tupleTitle);
        tuple.setCreateUserId(userId);
        tupleService.save(tuple);
        TupleMember tupleMember = new TupleMember();
        tupleMember.setHead(head);
        tupleMember.setUserId(userId);
        tupleMember.setTupleId(tuple.getId());
        tupleMember.setOrganizationId(organizationId);
        tupleMember.setName(name);
        tupleMemberService.save(tupleMember);
        //创建申请通知
        messageExtendsService.createApplyMessage(organizationId, userId, name, tuple.getId());
        formExtendsService.createNewMemberRecords(tupleMember);

    }

    /*
     * 同意入群，但是进入群组
     * 回头问问能不能传这么多
     * */
    public void agreeAndJoinTuple(String organizationId, String userId, String name, String head, String tupleId) {
        TupleMemberFilterMapper tupleMemberFilterMapper = new TupleMemberFilterMapper();
        tupleMemberFilterMapper.organizationId = organizationId;
        tupleMemberFilterMapper.userId = userId;
        List<TupleMember> tupleMemberList = tupleMemberExtendsService.getListByFilter(tupleMemberFilterMapper);
        if (!tupleMemberList.isEmpty()) throw new BaseException("群中已经存在您的信息");
        Organization organization = organizationService.getById(organizationId);
        Tuple tuple = tupleService.getById(tupleId);
        tuple.setNum(tuple.getNum() + 1);
        tupleService.updateById(tuple);
        TupleMember tupleMember = new TupleMember();
        tupleMember.setHead(head);
        tupleMember.setUserId(userId);
        tupleMember.setTupleId(tupleId);
        tupleMember.setOrganizationId(organizationId);
        tupleMember.setName(name);
        tupleMemberService.save(tupleMember);
        // 创建通知 给管理员发
        Message message = new Message();
        message.setRelationId(organizationId);
        message.setTitle(organization.getTitle() + "有新成员加入了！");
        message.setContent(name + "已加入" + tuple.getTitle() + "的组别下，望知悉");
        message.setType(MessageEnum.APPLY.getStatus());
        messageService.save(message);
        MessageUser messageUser = new MessageUser();
        messageUser.setMessageId(message.getId());
        messageUser.setUserId(organization.getCreateUserId());
        messageUserService.save(messageUser);
        formExtendsService.createNewMemberRecords(tupleMember);

    }

    /*
    * 创建 虚账号帮忙填写
    * */

    public void creatFakeMember(TupleMemberPutMapper tupleMemberPutMapper){

        TupleMember tupleMember = TupleMemberPutMapper.convertToEntity(tupleMemberPutMapper);
        tupleMember.setIsFake(1);
        tupleMemberService.save(tupleMember);
        Tuple tuple = tupleService.getById(tupleMember.getId());
        tuple.setNum(tuple.getNum()+1);
        tupleService.updateById(tuple);
        formExtendsService.createNewMemberRecords(tupleMember);
    }



    public List<Organization> getListByFilter(OrganizationFilterMapper filterMapper) {

        QueryWrapper<Organization> wrapper = new QueryWrapper<>();

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

        if (filterMapper.updateTimeFrom != null) wrapper.ge("update_time", new Date(filterMapper.updateTimeFrom));
        if (filterMapper.updateTimeTo != null) wrapper.le("update_time", new Date(filterMapper.updateTimeTo));
        if (filterMapper.createTimeFrom != null) wrapper.ge("create_time", new Date(filterMapper.createTimeFrom));
        if (filterMapper.createTimeTo != null) wrapper.le("create_time", new Date(filterMapper.createTimeTo));
        if (filterMapper.createUserId != null) wrapper.eq("create_user_id", filterMapper.createUserId);


        Long page = 1L;
        Long row = -1L;
        if (filterMapper.page != null) page = filterMapper.page;
        if (filterMapper.row != null) row = filterMapper.row;

        Page<Organization> markPage = new Page<>(page, row);

        Page<Organization> resultList = organizationService.page(markPage, wrapper);

        return resultList.getRecords();
    }


}