package com.yibingo.race.web.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.yibingo.race.dal.entity.FormRecords;
import com.yibingo.race.core.service.FormRecordsService;
import com.yibingo.race.dal.filterMapper.FormRecordsFilterMapper;
import com.yibingo.race.dal.putMapper.FormRecordsPutMapper;
import com.yibingo.race.common.utils.Result;



/**
 * 表单记录
 *
 * @author Yang Xin
 * @email little-cy@qq.com
 * @date 2022-05-26 20:03:13
 */
@Api(
        value = "formrecords",
        tags = "表单记录"
)
@RestController
@RequestMapping("core/formrecords")
public class FormRecordsController {
    @Autowired
    private FormRecordsService formRecordsService;



    @ApiOperation(
            value = "表单记录查询",
            notes = "表单记录查询"
    )
    @RequestMapping(
            value = "/info",
            method = RequestMethod.GET
    )
    @ResponseBody
//    @RequiresPermissions("core:formrecords:info")
    public Map<String,Object> info(@RequestParam String id) {
            FormRecords formRecords = formRecordsService.getById(id);

        return Result.success(formRecords).map();
    }

    /**
     * 保存
     */
    @ApiOperation(
            value = "表单记录保存",
            notes = "表单记录保存"
    )
    @RequestMapping(
            value = "/save",
            method = RequestMethod.POST
    )
    @ResponseBody
//    @RequiresPermissions("core:formrecords:save")
    public Map<String,Object> save(@RequestBody FormRecordsPutMapper formRecordsPutMapper){
        formRecordsService.save(FormRecordsPutMapper.convertToEntity(formRecordsPutMapper));

        return Result.success().map();
    }

    /**
     * 修改
     */
    @ApiOperation(
            value = "表单记录修改",
            notes = "表单记录修改"
    )
    @RequestMapping(
            value = "/update",
            method = RequestMethod.POST
    )
    @ResponseBody
//    @RequiresPermissions("core:formrecords:update")
    public Map<String,Object> update(@RequestBody FormRecords formRecords){
        formRecordsService.updateById(formRecords);

        return Result.success().map();
    }

    /**
     * 删除
     */

    @ApiOperation(
            value = "表单记录批量删除",
            notes = "表单记录批量删除"
    )
    @RequestMapping(
            value = "/delete",
            method = RequestMethod.POST
    )
    @ResponseBody
//    @RequiresPermissions("core:formrecords:delete")
    public Map<String,Object> delete(@RequestBody List<String> ids){
        formRecordsService.removeByIds(ids);

        return Result.success().map();
    }




}
