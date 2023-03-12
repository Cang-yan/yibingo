package com.yibingo.race.web.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.yibingo.race.dal.entity.OrganizationSpecial;
import com.yibingo.race.core.service.OrganizationSpecialService;
import com.yibingo.race.dal.filterMapper.OrganizationSpecialFilterMapper;
import com.yibingo.race.dal.putMapper.OrganizationSpecialPutMapper;
import com.yibingo.race.common.utils.Result;



/**
 * 特殊群组
 *
 * @author Yang Xin
 * @email little-cy@qq.com
 * @date 2022-05-26 20:03:13
 */
@Api(
        value = "organizationspecial",
        tags = "特殊群组"
)
@RestController
@RequestMapping("core/organizationspecial")
public class OrganizationSpecialController {
    @Autowired
    private OrganizationSpecialService organizationSpecialService;



    @ApiOperation(
            value = "特殊群组查询",
            notes = "特殊群组查询"
    )
    @RequestMapping(
            value = "/info",
            method = RequestMethod.GET
    )
    @ResponseBody
//    @RequiresPermissions("core:organizationspecial:info")
    public Map<String,Object> info(@RequestParam String id) {
            OrganizationSpecial organizationSpecial = organizationSpecialService.getById(id);

        return Result.success(organizationSpecial).map();
    }

    /**
     * 保存
     */
    @ApiOperation(
            value = "特殊群组保存",
            notes = "特殊群组保存"
    )
    @RequestMapping(
            value = "/save",
            method = RequestMethod.POST
    )
    @ResponseBody
//    @RequiresPermissions("core:organizationspecial:save")
    public Map<String,Object> save(@RequestBody OrganizationSpecialPutMapper organizationSpecialPutMapper){
        organizationSpecialService.save(OrganizationSpecialPutMapper.convertToEntity(organizationSpecialPutMapper));

        return Result.success().map();
    }

    /**
     * 修改
     */
    @ApiOperation(
            value = "特殊群组修改",
            notes = "特殊群组修改"
    )
    @RequestMapping(
            value = "/update",
            method = RequestMethod.POST
    )
    @ResponseBody
//    @RequiresPermissions("core:organizationspecial:update")
    public Map<String,Object> update(@RequestBody OrganizationSpecial organizationSpecial){
        organizationSpecialService.updateById(organizationSpecial);

        return Result.success().map();
    }

    /**
     * 删除
     */

    @ApiOperation(
            value = "特殊群组批量删除",
            notes = "特殊群组批量删除"
    )
    @RequestMapping(
            value = "/delete",
            method = RequestMethod.POST
    )
    @ResponseBody
//    @RequiresPermissions("core:organizationspecial:delete")
    public Map<String,Object> delete(@RequestBody List<String> ids){
        organizationSpecialService.removeByIds(ids);

        return Result.success().map();
    }




}
