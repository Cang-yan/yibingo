package com.yibingo.race.web.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.yibingo.race.dal.entity.Form;
import com.yibingo.race.core.service.FormService;
import com.yibingo.race.dal.filterMapper.FormFilterMapper;
import com.yibingo.race.dal.putMapper.FormPutMapper;
import com.yibingo.race.common.utils.Result;



/**
 * 表单发布
 *
 * @author Yang Xin
 * @email little-cy@qq.com
 * @date 2022-06-28 19:35:54
 */
@Api(
        value = "form",
        tags = "表单发布"
)
@RestController
@RequestMapping("core/form")
public class FormController {
    @Autowired
    private FormService formService;


    //基础增删改查基本没啥用，需要的话再打开即可

    /* @ApiOperation(
            value = "表单发布查询",
            notes = "表单发布查询"
    )*/
    @RequestMapping(
            value = "/info",
            method = RequestMethod.GET
    )
    @ResponseBody
//    @RequiresPermissions("core:form:info")
    public Map<String,Object> info(@RequestParam String id) {
            Form form = formService.getById(id);

        return Result.success(form).map();
    }

    /**
     * 保存
     */
    /* @ApiOperation(
            value = "表单发布保存",
            notes = "表单发布保存"
    )*/
    @RequestMapping(
            value = "/save",
            method = RequestMethod.POST
    )
    @ResponseBody
//    @RequiresPermissions("core:form:save")
    public Map<String,Object> save(@RequestBody FormPutMapper formPutMapper){
        formService.save(FormPutMapper.convertToEntity(formPutMapper));

        return Result.success().map();
    }

    /**
     * 修改
     */
    /* @ApiOperation(
            value = "表单发布修改",
            notes = "表单发布修改"
    )*/
    @RequestMapping(
            value = "/update",
            method = RequestMethod.POST
    )
    @ResponseBody
//    @RequiresPermissions("core:form:update")
    public Map<String,Object> update(@RequestBody Form form){
        formService.updateById(form);

        return Result.success().map();
    }

    /**
     * 删除
     */

    /* @ApiOperation(
            value = "表单发布批量删除",
            notes = "表单发布批量删除"
    )*/
    @RequestMapping(
            value = "/delete",
            method = RequestMethod.POST
    )
    @ResponseBody
//    @RequiresPermissions("core:form:delete")
    public Map<String,Object> delete(@RequestBody List<String> ids){
        formService.removeByIds(ids);

        return Result.success().map();
    }




}
