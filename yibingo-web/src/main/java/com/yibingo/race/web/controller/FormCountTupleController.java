package com.yibingo.race.web.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.yibingo.race.dal.entity.FormCountTuple;
import com.yibingo.race.core.service.FormCountTupleService;
import com.yibingo.race.dal.filterMapper.FormCountTupleFilterMapper;
import com.yibingo.race.dal.putMapper.FormCountTuplePutMapper;
import com.yibingo.race.common.utils.Result;



/**
 * 表单元组统计
 *
 * @author Yang Xin
 * @email little-cy@qq.com
 * @date 2022-05-26 20:03:13
 */
@Api(
        value = "formcounttuple",
        tags = "表单元组统计"
)
@RestController
@RequestMapping("core/formcounttuple")
public class FormCountTupleController {
    @Autowired
    private FormCountTupleService formCountTupleService;



    @ApiOperation(
            value = "表单元组统计查询",
            notes = "表单元组统计查询"
    )
    @RequestMapping(
            value = "/info",
            method = RequestMethod.GET
    )
    @ResponseBody
//    @RequiresPermissions("core:formcounttuple:info")
    public Map<String,Object> info(@RequestParam String id) {
            FormCountTuple formCountTuple = formCountTupleService.getById(id);

        return Result.success(formCountTuple).map();
    }

    /**
     * 保存
     */
    @ApiOperation(
            value = "表单元组统计保存",
            notes = "表单元组统计保存"
    )
    @RequestMapping(
            value = "/save",
            method = RequestMethod.POST
    )
    @ResponseBody
//    @RequiresPermissions("core:formcounttuple:save")
    public Map<String,Object> save(@RequestBody FormCountTuplePutMapper formCountTuplePutMapper){
        formCountTupleService.save(FormCountTuplePutMapper.convertToEntity(formCountTuplePutMapper));

        return Result.success().map();
    }

    /**
     * 修改
     */
    @ApiOperation(
            value = "表单元组统计修改",
            notes = "表单元组统计修改"
    )
    @RequestMapping(
            value = "/update",
            method = RequestMethod.POST
    )
    @ResponseBody
//    @RequiresPermissions("core:formcounttuple:update")
    public Map<String,Object> update(@RequestBody FormCountTuple formCountTuple){
        formCountTupleService.updateById(formCountTuple);

        return Result.success().map();
    }

    /**
     * 删除
     */

    @ApiOperation(
            value = "表单元组统计批量删除",
            notes = "表单元组统计批量删除"
    )
    @RequestMapping(
            value = "/delete",
            method = RequestMethod.POST
    )
    @ResponseBody
//    @RequiresPermissions("core:formcounttuple:delete")
    public Map<String,Object> delete(@RequestBody List<String> ids){
        formCountTupleService.removeByIds(ids);

        return Result.success().map();
    }




}
