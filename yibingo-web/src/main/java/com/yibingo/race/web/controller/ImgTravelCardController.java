package com.yibingo.race.web.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.yibingo.race.dal.entity.ImgTravelCard;
import com.yibingo.race.core.service.ImgTravelCardService;
import com.yibingo.race.dal.filterMapper.ImgTravelCardFilterMapper;
import com.yibingo.race.dal.putMapper.ImgTravelCardPutMapper;
import com.yibingo.race.common.utils.Result;



/**
 * 行程卡照片
 *
 * @author Yang Xin
 * @email little-cy@qq.com
 * @date 2022-05-26 20:03:13
 */
@Api(
        value = "imgtravelcard",
        tags = "行程卡照片"
)
@RestController
@RequestMapping("core/imgtravelcard")
public class ImgTravelCardController {
    @Autowired
    private ImgTravelCardService imgTravelCardService;



    @ApiOperation(
            value = "行程卡照片查询",
            notes = "行程卡照片查询"
    )
    @RequestMapping(
            value = "/info",
            method = RequestMethod.GET
    )
    @ResponseBody
//    @RequiresPermissions("core:imgtravelcard:info")
    public Map<String,Object> info(@RequestParam String id) {
            ImgTravelCard imgTravelCard = imgTravelCardService.getById(id);

        return Result.success(imgTravelCard).map();
    }

    /**
     * 保存
     */
    @ApiOperation(
            value = "行程卡照片保存",
            notes = "行程卡照片保存"
    )
    @RequestMapping(
            value = "/save",
            method = RequestMethod.POST
    )
    @ResponseBody
//    @RequiresPermissions("core:imgtravelcard:save")
    public Map<String,Object> save(@RequestBody ImgTravelCardPutMapper imgTravelCardPutMapper){
        imgTravelCardService.save(ImgTravelCardPutMapper.convertToEntity(imgTravelCardPutMapper));

        return Result.success().map();
    }

    /**
     * 修改
     */
    @ApiOperation(
            value = "行程卡照片修改",
            notes = "行程卡照片修改"
    )
    @RequestMapping(
            value = "/update",
            method = RequestMethod.POST
    )
    @ResponseBody
//    @RequiresPermissions("core:imgtravelcard:update")
    public Map<String,Object> update(@RequestBody ImgTravelCard imgTravelCard){
        imgTravelCardService.updateById(imgTravelCard);

        return Result.success().map();
    }

    /**
     * 删除
     */

    @ApiOperation(
            value = "行程卡照片批量删除",
            notes = "行程卡照片批量删除"
    )
    @RequestMapping(
            value = "/delete",
            method = RequestMethod.POST
    )
    @ResponseBody
//    @RequiresPermissions("core:imgtravelcard:delete")
    public Map<String,Object> delete(@RequestBody List<String> ids){
        imgTravelCardService.removeByIds(ids);

        return Result.success().map();
    }




}
