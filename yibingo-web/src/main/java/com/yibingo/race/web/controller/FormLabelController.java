package com.yibingo.race.web.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.yibingo.race.dal.entity.FormLabel;
import com.yibingo.race.core.service.FormLabelService;
import com.yibingo.race.dal.filterMapper.FormLabelFilterMapper;
import com.yibingo.race.dal.putMapper.FormLabelPutMapper;
import com.yibingo.race.common.utils.Result;



/**
 * 表单标签
 *
 * @author Yang Xin
 * @email little-cy@qq.com
 * @date 2022-05-26 20:03:13
 */
@Api(
        value = "formlabel",
        tags = "表单标签"
)
@RestController
@RequestMapping("core/formlabel")
public class FormLabelController {
    @Autowired
    private FormLabelService formLabelService;



    @ApiOperation(
            value = "表单标签查询",
            notes = "表单标签查询"
    )
    @RequestMapping(
            value = "/info",
            method = RequestMethod.GET
    )
    @ResponseBody
//    @RequiresPermissions("core:formlabel:info")
    public Map<String,Object> info(@RequestParam String id) {
            FormLabel formLabel = formLabelService.getById(id);

        return Result.success(formLabel).map();
    }

    /**
     * 保存
     */
    @ApiOperation(
            value = "表单标签保存",
            notes = "表单标签保存"
    )
    @RequestMapping(
            value = "/save",
            method = RequestMethod.POST
    )
    @ResponseBody
//    @RequiresPermissions("core:formlabel:save")
    public Map<String,Object> save(@RequestBody FormLabelPutMapper formLabelPutMapper){
        formLabelService.save(FormLabelPutMapper.convertToEntity(formLabelPutMapper));

        return Result.success().map();
    }

    /**
     * 修改
     */
    @ApiOperation(
            value = "表单标签修改",
            notes = "表单标签修改"
    )
    @RequestMapping(
            value = "/update",
            method = RequestMethod.POST
    )
    @ResponseBody
//    @RequiresPermissions("core:formlabel:update")
    public Map<String,Object> update(@RequestBody FormLabel formLabel){
        formLabelService.updateById(formLabel);

        return Result.success().map();
    }

    /**
     * 删除
     */

    @ApiOperation(
            value = "表单标签批量删除",
            notes = "表单标签批量删除"
    )
    @RequestMapping(
            value = "/delete",
            method = RequestMethod.POST
    )
    @ResponseBody
//    @RequiresPermissions("core:formlabel:delete")
    public Map<String,Object> delete(@RequestBody List<String> ids){
        formLabelService.removeByIds(ids);

        return Result.success().map();
    }




}
