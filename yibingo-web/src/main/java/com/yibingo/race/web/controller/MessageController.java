package com.yibingo.race.web.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.yibingo.race.dal.entity.Message;
import com.yibingo.race.core.service.MessageService;
import com.yibingo.race.dal.filterMapper.MessageFilterMapper;
import com.yibingo.race.dal.putMapper.MessagePutMapper;
import com.yibingo.race.common.utils.Result;



/**
 * 消息模板
 *
 * @author Yang Xin
 * @email little-cy@qq.com
 * @date 2022-05-26 20:03:13
 */
@Api(
        value = "message",
        tags = "消息模板"
)
@RestController
@RequestMapping("core/message")
public class MessageController {
    @Autowired
    private MessageService messageService;



    @ApiOperation(
            value = "消息模板查询",
            notes = "消息模板查询"
    )
    @RequestMapping(
            value = "/info",
            method = RequestMethod.GET
    )
    @ResponseBody
//    @RequiresPermissions("core:message:info")
    public Map<String,Object> info(@RequestParam String id) {
            Message message = messageService.getById(id);

        return Result.success(message).map();
    }

    /**
     * 保存
     */
    @ApiOperation(
            value = "消息模板保存",
            notes = "消息模板保存"
    )
    @RequestMapping(
            value = "/save",
            method = RequestMethod.POST
    )
    @ResponseBody
//    @RequiresPermissions("core:message:save")
    public Map<String,Object> save(@RequestBody MessagePutMapper messagePutMapper){
        messageService.save(MessagePutMapper.convertToEntity(messagePutMapper));

        return Result.success().map();
    }

    /**
     * 修改
     */
    @ApiOperation(
            value = "消息模板修改",
            notes = "消息模板修改"
    )
    @RequestMapping(
            value = "/update",
            method = RequestMethod.POST
    )
    @ResponseBody
//    @RequiresPermissions("core:message:update")
    public Map<String,Object> update(@RequestBody Message message){
        messageService.updateById(message);

        return Result.success().map();
    }

    /**
     * 删除
     */

    @ApiOperation(
            value = "消息模板批量删除",
            notes = "消息模板批量删除"
    )
    @RequestMapping(
            value = "/delete",
            method = RequestMethod.POST
    )
    @ResponseBody
//    @RequiresPermissions("core:message:delete")
    public Map<String,Object> delete(@RequestBody List<String> ids){
        messageService.removeByIds(ids);

        return Result.success().map();
    }




}
