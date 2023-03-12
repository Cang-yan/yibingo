package com.yibingo.race.web.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.yibingo.race.dal.entity.Organization;
import com.yibingo.race.core.service.OrganizationService;
import com.yibingo.race.dal.filterMapper.OrganizationFilterMapper;
import com.yibingo.race.dal.putMapper.OrganizationPutMapper;
import com.yibingo.race.common.utils.Result;



/**
 * 群组
 *
 * @author Yang Xin
 * @email little-cy@qq.com
 * @date 2022-05-26 20:03:13
 */
@Api(
        value = "organization",
        tags = "群组"
)
@RestController
@RequestMapping("core/organization")
public class OrganizationController {
    @Autowired
    private OrganizationService organizationService;



    @ApiOperation(
            value = "群组查询",
            notes = "群组查询"
    )
    @RequestMapping(
            value = "/info",
            method = RequestMethod.GET
    )
    @ResponseBody
//    @RequiresPermissions("core:organization:info")
    public Map<String,Object> info(@RequestParam String id) {
            Organization organization = organizationService.getById(id);

        return Result.success(organization).map();
    }

    /**
     * 保存
     */
    @ApiOperation(
            value = "群组保存",
            notes = "群组保存"
    )
    @RequestMapping(
            value = "/save",
            method = RequestMethod.POST
    )
    @ResponseBody
//    @RequiresPermissions("core:organization:save")
    public Map<String,Object> save(@RequestBody OrganizationPutMapper organizationPutMapper){
        organizationService.save(OrganizationPutMapper.convertToEntity(organizationPutMapper));

        return Result.success().map();
    }

    /**
     * 修改
     */
    @ApiOperation(
            value = "群组修改",
            notes = "群组修改"
    )
    @RequestMapping(
            value = "/update",
            method = RequestMethod.POST
    )
    @ResponseBody
//    @RequiresPermissions("core:organization:update")
    public Map<String,Object> update(@RequestBody Organization organization){
        organizationService.updateById(organization);

        return Result.success().map();
    }

    /**
     * 删除
     */

    @ApiOperation(
            value = "群组批量删除",
            notes = "群组批量删除"
    )
    @RequestMapping(
            value = "/delete",
            method = RequestMethod.POST
    )
    @ResponseBody
//    @RequiresPermissions("core:organization:delete")
    public Map<String,Object> delete(@RequestBody List<String> ids){
        organizationService.removeByIds(ids);

        return Result.success().map();
    }




}
