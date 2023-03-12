package com.yibingo.race.web.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.yibingo.race.dal.entity.ImgHealthCode;
import com.yibingo.race.core.service.ImgHealthCodeService;
import com.yibingo.race.dal.filterMapper.ImgHealthCodeFilterMapper;
import com.yibingo.race.dal.putMapper.ImgHealthCodePutMapper;
import com.yibingo.race.common.utils.Result;



/**
 * 健康码图片
 *
 * @author Yang Xin
 * @email little-cy@qq.com
 * @date 2022-05-26 20:03:13
 */
@Api(
        value = "imghealthcode",
        tags = "健康码图片"
)
@RestController
@RequestMapping("core/imghealthcode")
public class ImgHealthCodeController {
    @Autowired
    private ImgHealthCodeService imgHealthCodeService;



    @ApiOperation(
            value = "健康码图片查询",
            notes = "健康码图片查询"
    )
    @RequestMapping(
            value = "/info",
            method = RequestMethod.GET
    )
    @ResponseBody
//    @RequiresPermissions("core:imghealthcode:info")
    public Map<String,Object> info(@RequestParam String id) {
            ImgHealthCode imgHealthCode = imgHealthCodeService.getById(id);

        return Result.success(imgHealthCode).map();
    }

    /**
     * 保存
     */
    @ApiOperation(
            value = "健康码图片保存",
            notes = "健康码图片保存"
    )
    @RequestMapping(
            value = "/save",
            method = RequestMethod.POST
    )
    @ResponseBody
//    @RequiresPermissions("core:imghealthcode:save")
    public Map<String,Object> save(@RequestBody ImgHealthCodePutMapper imgHealthCodePutMapper){
        imgHealthCodeService.save(ImgHealthCodePutMapper.convertToEntity(imgHealthCodePutMapper));

        return Result.success().map();
    }

    /**
     * 修改
     */
    @ApiOperation(
            value = "健康码图片修改",
            notes = "健康码图片修改"
    )
    @RequestMapping(
            value = "/update",
            method = RequestMethod.POST
    )
    @ResponseBody
//    @RequiresPermissions("core:imghealthcode:update")
    public Map<String,Object> update(@RequestBody ImgHealthCode imgHealthCode){
        imgHealthCodeService.updateById(imgHealthCode);

        return Result.success().map();
    }

    /**
     * 删除
     */

    @ApiOperation(
            value = "健康码图片批量删除",
            notes = "健康码图片批量删除"
    )
    @RequestMapping(
            value = "/delete",
            method = RequestMethod.POST
    )
    @ResponseBody
//    @RequiresPermissions("core:imghealthcode:delete")
    public Map<String,Object> delete(@RequestBody List<String> ids){
        imgHealthCodeService.removeByIds(ids);

        return Result.success().map();
    }




}
