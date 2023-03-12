package com.yibingo.race.web.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.yibingo.race.dal.entity.FormCount;
import com.yibingo.race.core.service.FormCountService;
import com.yibingo.race.dal.filterMapper.FormCountFilterMapper;
import com.yibingo.race.dal.putMapper.FormCountPutMapper;
import com.yibingo.race.common.utils.Result;



/**
 * 表单计数
 *
 * @author Yang Xin
 * @email little-cy@qq.com
 * @date 2022-05-26 20:03:13
 */
@Api(
        value = "formcount",
        tags = "表单计数"
)
@RestController
@RequestMapping("core/formcount")
public class FormCountController {
    @Autowired
    private FormCountService formCountService;



    @ApiOperation(
            value = "表单计数查询",
            notes = "表单计数查询"
    )
    @RequestMapping(
            value = "/info",
            method = RequestMethod.GET
    )
    @ResponseBody
//    @RequiresPermissions("core:formcount:info")
    public Map<String,Object> info(@RequestParam String id) {
            FormCount formCount = formCountService.getById(id);

        return Result.success(formCount).map();
    }

    /**
     * 保存
     */
    @ApiOperation(
            value = "表单计数保存",
            notes = "表单计数保存"
    )
    @RequestMapping(
            value = "/save",
            method = RequestMethod.POST
    )
    @ResponseBody
//    @RequiresPermissions("core:formcount:save")
    public Map<String,Object> save(@RequestBody FormCountPutMapper formCountPutMapper){
        formCountService.save(FormCountPutMapper.convertToEntity(formCountPutMapper));

        return Result.success().map();
    }

    /**
     * 修改
     */
    @ApiOperation(
            value = "表单计数修改",
            notes = "表单计数修改"
    )
    @RequestMapping(
            value = "/update",
            method = RequestMethod.POST
    )
    @ResponseBody
//    @RequiresPermissions("core:formcount:update")
    public Map<String,Object> update(@RequestBody FormCount formCount){
        formCountService.updateById(formCount);

        return Result.success().map();
    }

    /**
     * 删除
     */

    @ApiOperation(
            value = "表单计数批量删除",
            notes = "表单计数批量删除"
    )
    @RequestMapping(
            value = "/delete",
            method = RequestMethod.POST
    )
    @ResponseBody
//    @RequiresPermissions("core:formcount:delete")
    public Map<String,Object> delete(@RequestBody List<String> ids){
        formCountService.removeByIds(ids);

        return Result.success().map();
    }




}
