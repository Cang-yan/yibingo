package com.yibingo.race.web.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.yibingo.race.dal.entity.FormLabelImg;
import com.yibingo.race.core.service.FormLabelImgService;
import com.yibingo.race.dal.filterMapper.FormLabelImgFilterMapper;
import com.yibingo.race.dal.putMapper.FormLabelImgPutMapper;
import com.yibingo.race.common.utils.Result;



/**
 * 图片标签
 *
 * @author Yang Xin
 * @email little-cy@qq.com
 * @date 2022-05-26 20:03:13
 */
@Api(
        value = "formlabelimg",
        tags = "图片标签"
)
@RestController
@RequestMapping("core/formlabelimg")
public class FormLabelImgController {
    @Autowired
    private FormLabelImgService formLabelImgService;



    @ApiOperation(
            value = "图片标签查询",
            notes = "图片标签查询"
    )
    @RequestMapping(
            value = "/info",
            method = RequestMethod.GET
    )
    @ResponseBody
//    @RequiresPermissions("core:formlabelimg:info")
    public Map<String,Object> info(@RequestParam String id) {
            FormLabelImg formLabelImg = formLabelImgService.getById(id);

        return Result.success(formLabelImg).map();
    }

    /**
     * 保存
     */
    @ApiOperation(
            value = "图片标签保存",
            notes = "图片标签保存"
    )
    @RequestMapping(
            value = "/save",
            method = RequestMethod.POST
    )
    @ResponseBody
//    @RequiresPermissions("core:formlabelimg:save")
    public Map<String,Object> save(@RequestBody FormLabelImgPutMapper formLabelImgPutMapper){
        formLabelImgService.save(FormLabelImgPutMapper.convertToEntity(formLabelImgPutMapper));

        return Result.success().map();
    }

    /**
     * 修改
     */
    @ApiOperation(
            value = "图片标签修改",
            notes = "图片标签修改"
    )
    @RequestMapping(
            value = "/update",
            method = RequestMethod.POST
    )
    @ResponseBody
//    @RequiresPermissions("core:formlabelimg:update")
    public Map<String,Object> update(@RequestBody FormLabelImg formLabelImg){
        formLabelImgService.updateById(formLabelImg);

        return Result.success().map();
    }

    /**
     * 删除
     */

    @ApiOperation(
            value = "图片标签批量删除",
            notes = "图片标签批量删除"
    )
    @RequestMapping(
            value = "/delete",
            method = RequestMethod.POST
    )
    @ResponseBody
//    @RequiresPermissions("core:formlabelimg:delete")
    public Map<String,Object> delete(@RequestBody List<String> ids){
        formLabelImgService.removeByIds(ids);

        return Result.success().map();
    }




}
