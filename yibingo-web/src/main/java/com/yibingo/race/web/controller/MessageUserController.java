package com.yibingo.race.web.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.yibingo.race.dal.entity.MessageUser;
import com.yibingo.race.core.service.MessageUserService;
import com.yibingo.race.dal.filterMapper.MessageUserFilterMapper;
import com.yibingo.race.dal.putMapper.MessageUserPutMapper;
import com.yibingo.race.common.utils.Result;



/**
 * 通知用户表
 *
 * @author Yang Xin
 * @email little-cy@qq.com
 * @date 2022-05-26 20:03:13
 */
@Api(
        value = "messageuser",
        tags = "通知用户表"
)
@RestController
@RequestMapping("core/messageuser")
public class MessageUserController {
    @Autowired
    private MessageUserService messageUserService;



    @ApiOperation(
            value = "通知用户表查询",
            notes = "通知用户表查询"
    )
    @RequestMapping(
            value = "/info",
            method = RequestMethod.GET
    )
    @ResponseBody
//    @RequiresPermissions("core:messageuser:info")
    public Map<String,Object> info(@RequestParam String id) {
            MessageUser messageUser = messageUserService.getById(id);

        return Result.success(messageUser).map();
    }

    /**
     * 保存
     */
    @ApiOperation(
            value = "通知用户表保存",
            notes = "通知用户表保存"
    )
    @RequestMapping(
            value = "/save",
            method = RequestMethod.POST
    )
    @ResponseBody
//    @RequiresPermissions("core:messageuser:save")
    public Map<String,Object> save(@RequestBody MessageUserPutMapper messageUserPutMapper){
        messageUserService.save(MessageUserPutMapper.convertToEntity(messageUserPutMapper));

        return Result.success().map();
    }

    /**
     * 修改
     */
    @ApiOperation(
            value = "通知用户表修改",
            notes = "通知用户表修改"
    )
    @RequestMapping(
            value = "/update",
            method = RequestMethod.POST
    )
    @ResponseBody
//    @RequiresPermissions("core:messageuser:update")
    public Map<String,Object> update(@RequestBody MessageUser messageUser){
        messageUserService.updateById(messageUser);

        return Result.success().map();
    }

    /**
     * 删除
     */

    @ApiOperation(
            value = "通知用户表批量删除",
            notes = "通知用户表批量删除"
    )
    @RequestMapping(
            value = "/delete",
            method = RequestMethod.POST
    )
    @ResponseBody
//    @RequiresPermissions("core:messageuser:delete")
    public Map<String,Object> delete(@RequestBody List<String> ids){
        messageUserService.removeByIds(ids);

        return Result.success().map();
    }




}
