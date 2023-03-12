package com.yibingo.race.web.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.yibingo.race.dal.entity.Vip;
import com.yibingo.race.core.service.VipService;
import com.yibingo.race.dal.filterMapper.VipFilterMapper;
import com.yibingo.race.dal.putMapper.VipPutMapper;
import com.yibingo.race.common.utils.Result;



/**
 * 会员权益表
 *
 * @author Yang Xin
 * @email little-cy@qq.com
 * @date 2022-05-26 20:03:13
 */
@Api(
        value = "vip",
        tags = "会员权益表"
)
@RestController
@RequestMapping("core/vip")
public class VipController {
    @Autowired
    private VipService vipService;



    @ApiOperation(
            value = "会员权益表查询",
            notes = "会员权益表查询"
    )
    @RequestMapping(
            value = "/info",
            method = RequestMethod.GET
    )
    @ResponseBody
//    @RequiresPermissions("core:vip:info")
    public Map<String,Object> info(@RequestParam String id) {
            Vip vip = vipService.getById(id);

        return Result.success(vip).map();
    }

    /**
     * 保存
     */
    @ApiOperation(
            value = "会员权益表保存",
            notes = "会员权益表保存"
    )
    @RequestMapping(
            value = "/save",
            method = RequestMethod.POST
    )
    @ResponseBody
//    @RequiresPermissions("core:vip:save")
    public Map<String,Object> save(@RequestBody VipPutMapper vipPutMapper){
        vipService.save(VipPutMapper.convertToEntity(vipPutMapper));

        return Result.success().map();
    }

    /**
     * 修改
     */
    @ApiOperation(
            value = "会员权益表修改",
            notes = "会员权益表修改"
    )
    @RequestMapping(
            value = "/update",
            method = RequestMethod.POST
    )
    @ResponseBody
//    @RequiresPermissions("core:vip:update")
    public Map<String,Object> update(@RequestBody Vip vip){
        vipService.updateById(vip);

        return Result.success().map();
    }

    /**
     * 删除
     */

    @ApiOperation(
            value = "会员权益表批量删除",
            notes = "会员权益表批量删除"
    )
    @RequestMapping(
            value = "/delete",
            method = RequestMethod.POST
    )
    @ResponseBody
//    @RequiresPermissions("core:vip:delete")
    public Map<String,Object> delete(@RequestBody List<String> ids){
        vipService.removeByIds(ids);

        return Result.success().map();
    }




}
