package com.yibingo.race.web.controller;

import com.yibingo.race.common.utils.Result;
import com.yibingo.race.core.service.OrganizationExtendsService;
import com.yibingo.race.dal.filterMapper.OrganizationFilterMapper;
import com.yibingo.race.dal.putMapper.OrganizationPutMapper;
import com.yibingo.race.dal.putMapper.TupleMemberPutMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * 群组扩展
 *
 * @author Yang Xin
 * @email little-cy@qq.com
 * @date 2022-05-15 18:42:55
 */
@Api(
        value = "organization_extends" ,
        tags = "群组扩展管理"
)
@RestController
@RequestMapping("core/organization/extends" )
public class OrganizationExtendsController {

    @Autowired
    private OrganizationExtendsService organizationExtendsService;

    @ApiOperation(
            value = "查询所有群组" ,
            notes = "查询所有群组"
    )
    @RequestMapping(
            value = "/organization/all" ,
            method = RequestMethod.GET
    )
    @ResponseBody
    public Map<String, Object> getAllOrganization(@RequestParam String userId) {
        return Result.success(
                organizationExtendsService.getAllOrganization(userId)
        ).map();
    }

    @ApiOperation(
            value = "查询群组的成员组成" ,
            notes = "查询群组的成员组成"
    )
    @RequestMapping(
            value = "/organization/member" ,
            method = RequestMethod.GET
    )
    @ResponseBody
    public Map<String, Object> getOrganizationMember(@RequestParam String organizationId) {
        return Result.success(
                organizationExtendsService.getOrganizationMember(organizationId)
        ).map();

    }

    @ApiOperation(
            value = "获取我所在的元组" ,
            notes = "获取我所在的元组"
    )
    @RequestMapping(
            value = "/organization/tuple" ,
            method = RequestMethod.GET
    )
    @ResponseBody
    public Map<String, Object> getMyTuple(@RequestParam String userId, @RequestParam String organizationId) {
        return Result.success(
                organizationExtendsService.getMyTupleDetailMap(userId, organizationId)
        ).map();


    }

    @ApiOperation(
            value = "创建群组" ,
            notes = "创建群组"
    )
    @RequestMapping(
            value = "/organization/create" ,
            method = RequestMethod.POST
    )
    @ResponseBody
    public Map<String, Object> createOrgazition(@RequestBody OrganizationPutMapper organizationPutMapper) {
        organizationExtendsService.createOrgazition(organizationPutMapper);
        return Result.success().map();

    }

    @ApiOperation(
            value = "加入群组-创建元组" ,
            notes = "加入群组-创建元组"
    )
    @RequestMapping(
            value = "/organization/tuple/create" ,
            method = RequestMethod.GET
    )
    @ResponseBody
    public Map<String, Object> unlessAgreeAndCreateTuple(@RequestParam String organizationId,
                                                         @RequestParam String userId,
                                                         @RequestParam String tupleTitle,
                                                         @RequestParam String name,
                                                         @RequestParam String head) {

        organizationExtendsService.unlessAgreeAndCreateTuple(organizationId, userId, tupleTitle, name, head);
        return Result.success().map();
    }

    @ApiOperation(
            value = "加入群组-加入元组" ,
            notes = "加入群组-加入元组"
    )
    @RequestMapping(
            value = "/organization/tuple/join" ,
            method = RequestMethod.GET
    )
    @ResponseBody
    public Map<String, Object> agreeAndJoinTuple(@RequestParam String organizationId,
                                                 @RequestParam String userId,
                                                 @RequestParam String name,
                                                 @RequestParam String head,
                                                 @RequestParam String tupleId) {

        organizationExtendsService.agreeAndJoinTuple(organizationId, userId, name, head,tupleId);
        return Result.success().map();
    }

    @ApiOperation(
            value = "创建虚账号" ,
            notes = "创建虚账号"
    )
    @RequestMapping(
            value = "/organization/create/fakemember" ,
            method = RequestMethod.POST
    )
    @ResponseBody
    public Map<String, Object> creatFakeMember(@RequestBody TupleMemberPutMapper tupleMemberPutMapper){

        organizationExtendsService.creatFakeMember(tupleMemberPutMapper);
        return Result.success().map();
    }

    @ApiOperation(
            value = "搜索条件查询" ,
            notes = "搜索条件查询"
    )
    @RequestMapping(
            value = "/search" ,
            method = RequestMethod.GET
    )
    @ResponseBody
    public Map<String, Object> getListByFilter(@RequestParam(value = "updateTimeFrom" , required = false) Long updateTimeFrom,
                                               @RequestParam(value = "updateTimeTo" , required = false) Long updateTimeTo,
                                               @RequestParam(value = "createTimeFrom" , required = false) Long createTimeFrom,
                                               @RequestParam(value = "createTimeTo" , required = false) Long createTimeTo,
                                               @RequestParam(value = "page" , required = false, defaultValue = "1" ) Long page,
                                               @RequestParam(value = "row" , required = false, defaultValue = "10" ) Long row,
                                               @RequestParam(value = "orderBy" , required = false) List<String> orderBy
    ) {
        OrganizationFilterMapper mapper = new OrganizationFilterMapper();
        mapper.updateTimeFrom = updateTimeFrom;
        mapper.updateTimeTo = updateTimeTo;
        mapper.createTimeFrom = createTimeFrom;
        mapper.createTimeTo = createTimeTo;
        mapper.page = page;
        mapper.row = row;
        mapper.orderBy = orderBy;
        return Result.success(organizationExtendsService.getListByFilter(mapper)).map();
    }


}