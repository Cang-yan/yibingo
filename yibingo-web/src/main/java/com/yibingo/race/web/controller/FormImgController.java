package com.yibingo.race.web.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.yibingo.race.dal.entity.FormImg;
import com.yibingo.race.core.service.FormImgService;
import com.yibingo.race.dal.filterMapper.FormImgFilterMapper;
import com.yibingo.race.dal.putMapper.FormImgPutMapper;
import com.yibingo.race.common.utils.Result;



/**
 * 表单图片选择
 *
 * @author Yang Xin
 * @email little-cy@qq.com
 * @date 2022-05-26 20:03:13
 */
@Api(
        value = "formimg",
        tags = "表单图片选择"
)
@RestController
@RequestMapping("core/formimg")
public class FormImgController {
    @Autowired
    private FormImgService formImgService;



    @ApiOperation(
            value = "表单图片选择查询",
            notes = "表单图片选择查询"
    )
    @RequestMapping(
            value = "/info",
            method = RequestMethod.GET
    )
    @ResponseBody
//    @RequiresPermissions("core:formimg:info")
    public Map<String,Object> info(@RequestParam String id) {
            FormImg formImg = formImgService.getById(id);

        return Result.success(formImg).map();
    }

    /**
     * 保存
     */
    @ApiOperation(
            value = "表单图片选择保存",
            notes = "表单图片选择保存"
    )
    @RequestMapping(
            value = "/save",
            method = RequestMethod.POST
    )
    @ResponseBody
//    @RequiresPermissions("core:formimg:save")
    public Map<String,Object> save(@RequestBody FormImgPutMapper formImgPutMapper){
        formImgService.save(FormImgPutMapper.convertToEntity(formImgPutMapper));

        return Result.success().map();
    }

    /**
     * 修改
     */
    @ApiOperation(
            value = "表单图片选择修改",
            notes = "表单图片选择修改"
    )
    @RequestMapping(
            value = "/update",
            method = RequestMethod.POST
    )
    @ResponseBody
//    @RequiresPermissions("core:formimg:update")
    public Map<String,Object> update(@RequestBody FormImg formImg){
        formImgService.updateById(formImg);

        return Result.success().map();
    }

    /**
     * 删除
     */

    @ApiOperation(
            value = "表单图片选择批量删除",
            notes = "表单图片选择批量删除"
    )
    @RequestMapping(
            value = "/delete",
            method = RequestMethod.POST
    )
    @ResponseBody
//    @RequiresPermissions("core:formimg:delete")
    public Map<String,Object> delete(@RequestBody List<String> ids){
        formImgService.removeByIds(ids);

        return Result.success().map();
    }




}
