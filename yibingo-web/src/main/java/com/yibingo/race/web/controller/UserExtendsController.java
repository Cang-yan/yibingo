package com.yibingo.race.web.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.yibingo.race.dal.entity.User;
import com.yibingo.race.core.service.UserExtendsService;
import com.yibingo.race.dal.filterMapper.UserFilterMapper;
import com.yibingo.race.common.utils.Result;



/**
 * 用户表扩展
 *
 * @author Yang Xin
 * @email little-cy@qq.com
 * @date 2022-06-22 10:53:16
 */
@Api(
        value = "user_extends",
        tags = "用户表扩展管理"
)
@RestController
@RequestMapping("core/user/extends")
public class UserExtendsController {

    @Autowired
    private  UserExtendsService userExtendsService;

    @ApiOperation(
            value = "搜索条件查询",
            notes = "搜索条件查询"
    )
    @RequestMapping(
            value = "/search",
            method = RequestMethod.GET
    )
    @ResponseBody
    public Map<String,Object> getListByFilter(@RequestParam(value = "updateTimeFrom", required = false)Long updateTimeFrom,
                             @RequestParam(value = "updateTimeTo", required = false) Long updateTimeTo,
                             @RequestParam(value = "createTimeFrom", required = false) Long createTimeFrom,
                             @RequestParam(value = "createTimeTo", required = false) Long createTimeTo,
                             @RequestParam(value = "page", required = false, defaultValue = "1") Long page,
                             @RequestParam(value = "row", required = false, defaultValue = "10") Long row,
                             @RequestParam(value = "orderBy", required = false) List<String> orderBy
    ) {
            UserFilterMapper mapper = new UserFilterMapper();
        mapper.updateTimeFrom = updateTimeFrom;
        mapper.updateTimeTo = updateTimeTo;
        mapper.createTimeFrom=createTimeFrom;
        mapper.createTimeTo=createTimeTo;
        mapper.page = page;
        mapper.row = row;
        mapper.orderBy = orderBy;
        return Result.success(userExtendsService.getListByFilter(mapper)).map();
    }






}