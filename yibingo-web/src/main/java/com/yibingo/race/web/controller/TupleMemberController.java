package com.yibingo.race.web.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.yibingo.race.dal.entity.TupleMember;
import com.yibingo.race.core.service.TupleMemberService;
import com.yibingo.race.dal.filterMapper.TupleMemberFilterMapper;
import com.yibingo.race.dal.putMapper.TupleMemberPutMapper;
import com.yibingo.race.common.utils.Result;



/**
 * 元组成员信息
 *
 * @author Yang Xin
 * @email little-cy@qq.com
 * @date 2022-05-26 20:03:13
 */
@Api(
        value = "tuplemember",
        tags = "元组成员信息"
)
@RestController
@RequestMapping("core/tuplemember")
public class TupleMemberController {
    @Autowired
    private TupleMemberService tupleMemberService;



    @ApiOperation(
            value = "元组成员信息查询",
            notes = "元组成员信息查询"
    )
    @RequestMapping(
            value = "/info",
            method = RequestMethod.GET
    )
    @ResponseBody
//    @RequiresPermissions("core:tuplemember:info")
    public Map<String,Object> info(@RequestParam String id) {
            TupleMember tupleMember = tupleMemberService.getById(id);

        return Result.success(tupleMember).map();
    }

    /**
     * 保存
     */
    @ApiOperation(
            value = "元组成员信息保存",
            notes = "元组成员信息保存"
    )
    @RequestMapping(
            value = "/save",
            method = RequestMethod.POST
    )
    @ResponseBody
//    @RequiresPermissions("core:tuplemember:save")
    public Map<String,Object> save(@RequestBody TupleMemberPutMapper tupleMemberPutMapper){
        tupleMemberService.save(TupleMemberPutMapper.convertToEntity(tupleMemberPutMapper));

        return Result.success().map();
    }

    /**
     * 修改
     */
    @ApiOperation(
            value = "元组成员信息修改",
            notes = "元组成员信息修改"
    )
    @RequestMapping(
            value = "/update",
            method = RequestMethod.POST
    )
    @ResponseBody
//    @RequiresPermissions("core:tuplemember:update")
    public Map<String,Object> update(@RequestBody TupleMember tupleMember){
        tupleMemberService.updateById(tupleMember);

        return Result.success().map();
    }

    /**
     * 删除
     */

    @ApiOperation(
            value = "元组成员信息批量删除",
            notes = "元组成员信息批量删除"
    )
    @RequestMapping(
            value = "/delete",
            method = RequestMethod.POST
    )
    @ResponseBody
//    @RequiresPermissions("core:tuplemember:delete")
    public Map<String,Object> delete(@RequestBody List<String> ids){
        tupleMemberService.removeByIds(ids);

        return Result.success().map();
    }




}
