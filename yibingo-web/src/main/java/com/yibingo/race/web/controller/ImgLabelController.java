package com.yibingo.race.web.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.yibingo.race.dal.entity.ImgLabel;
import com.yibingo.race.core.service.ImgLabelService;
import com.yibingo.race.dal.filterMapper.ImgLabelFilterMapper;
import com.yibingo.race.dal.putMapper.ImgLabelPutMapper;
import com.yibingo.race.common.utils.Result;



/**
 * 标签要求上传的图片
 *
 * @author Yang Xin
 * @email little-cy@qq.com
 * @date 2022-05-26 20:03:13
 */
@Api(
        value = "imglabel",
        tags = "标签要求上传的图片"
)
@RestController
@RequestMapping("core/imglabel")
public class ImgLabelController {
    @Autowired
    private ImgLabelService imgLabelService;



    @ApiOperation(
            value = "标签要求上传的图片查询",
            notes = "标签要求上传的图片查询"
    )
    @RequestMapping(
            value = "/info",
            method = RequestMethod.GET
    )
    @ResponseBody
//    @RequiresPermissions("core:imglabel:info")
    public Map<String,Object> info(@RequestParam String id) {
            ImgLabel imgLabel = imgLabelService.getById(id);

        return Result.success(imgLabel).map();
    }

    /**
     * 保存
     */
    @ApiOperation(
            value = "标签要求上传的图片保存",
            notes = "标签要求上传的图片保存"
    )
    @RequestMapping(
            value = "/save",
            method = RequestMethod.POST
    )
    @ResponseBody
//    @RequiresPermissions("core:imglabel:save")
    public Map<String,Object> save(@RequestBody ImgLabelPutMapper imgLabelPutMapper){
        imgLabelService.save(ImgLabelPutMapper.convertToEntity(imgLabelPutMapper));

        return Result.success().map();
    }

    /**
     * 修改
     */
    @ApiOperation(
            value = "标签要求上传的图片修改",
            notes = "标签要求上传的图片修改"
    )
    @RequestMapping(
            value = "/update",
            method = RequestMethod.POST
    )
    @ResponseBody
//    @RequiresPermissions("core:imglabel:update")
    public Map<String,Object> update(@RequestBody ImgLabel imgLabel){
        imgLabelService.updateById(imgLabel);

        return Result.success().map();
    }

    /**
     * 删除
     */

    @ApiOperation(
            value = "标签要求上传的图片批量删除",
            notes = "标签要求上传的图片批量删除"
    )
    @RequestMapping(
            value = "/delete",
            method = RequestMethod.POST
    )
    @ResponseBody
//    @RequiresPermissions("core:imglabel:delete")
    public Map<String,Object> delete(@RequestBody List<String> ids){
        imgLabelService.removeByIds(ids);

        return Result.success().map();
    }




}
