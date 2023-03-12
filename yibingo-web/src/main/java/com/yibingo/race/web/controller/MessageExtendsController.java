package com.yibingo.race.web.controller;

import com.yibingo.race.common.utils.Result;
import com.yibingo.race.core.service.MessageExtendsService;
import com.yibingo.race.core.service.enums.MessageEnum;
import com.yibingo.race.dal.filterMapper.MessageFilterMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * 消息模板扩展
 *
 * @author Yang Xin
 * @email little-cy@qq.com
 * @date 2022-05-15 18:42:55
 */
@Api(
        value = "message_extends",
        tags = "消息模板扩展管理"
)
@RestController
@RequestMapping("core/message/extends")
public class MessageExtendsController {

    @Autowired
    private MessageExtendsService messageExtendsService;

    @ApiOperation(
            value = "申请入群",
            notes = "申请入群"
    )
    @RequestMapping(
            value = "/apply",
            method = RequestMethod.GET
    )
    @ResponseBody
    public Map<String, Object> createApplyMessage(@RequestParam String organizationId,
                                                  @RequestParam String userId,
                                                  @RequestParam String nameInTuple,
                                                  @RequestParam String tupleId) {
        messageExtendsService.createApplyMessage(organizationId, userId, nameInTuple, tupleId);
        return Result.success().map();

    }

    @ApiOperation(
            value = "创建提醒",
            notes = "创建提醒"
    )
    @RequestMapping(
            value = "/remind",
            method = RequestMethod.GET
    )
    @ResponseBody
    public Map<String, Object> createRemindMessage(@RequestParam String formId){
        messageExtendsService.createRemindMessage(formId);
        return Result.success().map();
    }

    @ApiOperation(
            value = "查询未读消息数",
            notes = "查询未读消息数"
    )
    @RequestMapping(
            value = "/unread",
            method = RequestMethod.GET
    )
    @ResponseBody
    public Map<String, Object>  getUnreadMessage (@RequestParam String userId){
        return Result.success(
                messageExtendsService.getUnreadMessage(userId)
        ).map();
    }

    @ApiOperation(
            value = "查询申请消息",
            notes = "查询申请消息"
    )
    @RequestMapping(
            value = "/get/apply",
            method = RequestMethod.GET
    )
    @ResponseBody
    public Map<String, Object> getApplyMessage( @RequestParam String userId){
        return Result.success(
                messageExtendsService.getMessage(MessageEnum.APPLY,userId)
        ).map();
    }

    @ApiOperation(
            value = "查询提醒消息",
            notes = "查询提醒消息"
    )
    @RequestMapping(
            value = "/get/remind",
            method = RequestMethod.GET
    )
    @ResponseBody
    public Map<String, Object> getRemindMessage( @RequestParam String userId){
        return Result.success(
                messageExtendsService.getMessage(MessageEnum.REMIND,userId)
        ).map();
    }

    @ApiOperation(
            value = "查询异常消息",
            notes = "查询异常消息"
    )
    @RequestMapping(
            value = "/get/warn",
            method = RequestMethod.GET
    )
    @ResponseBody
    public Map<String, Object> getWarnMessage( @RequestParam String userId){
        return Result.success(
                messageExtendsService.getMessage(MessageEnum.WARN,userId)
        ).map();
    }

    @ApiOperation(
            value = "搜索条件查询",
            notes = "搜索条件查询"
    )
    @RequestMapping(
            value = "/search",
            method = RequestMethod.GET
    )
    @ResponseBody
    public Map<String, Object> getListByFilter(@RequestParam(value = "updateTimeFrom", required = false) Long updateTimeFrom,
                                               @RequestParam(value = "updateTimeTo", required = false) Long updateTimeTo,
                                               @RequestParam(value = "createTimeFrom", required = false) Long createTimeFrom,
                                               @RequestParam(value = "createTimeTo", required = false) Long createTimeTo,
                                               @RequestParam(value = "page", required = false, defaultValue = "1") Long page,
                                               @RequestParam(value = "row", required = false, defaultValue = "10") Long row,
                                               @RequestParam(value = "orderBy", required = false) List<String> orderBy
    ) {
        MessageFilterMapper mapper = new MessageFilterMapper();
        mapper.updateTimeFrom = updateTimeFrom;
        mapper.updateTimeTo = updateTimeTo;
        mapper.createTimeFrom = createTimeFrom;
        mapper.createTimeTo = createTimeTo;
        mapper.page = page;
        mapper.row = row;
        mapper.orderBy = orderBy;
        return Result.success(messageExtendsService.getListByFilter(mapper)).map();
    }


}