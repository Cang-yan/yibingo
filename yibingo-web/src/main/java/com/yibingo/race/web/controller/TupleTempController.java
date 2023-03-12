package com.yibingo.race.web.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.yibingo.race.dal.entity.TupleTemp;
import com.yibingo.race.core.service.TupleTempService;
import com.yibingo.race.dal.filterMapper.TupleTempFilterMapper;
import com.yibingo.race.dal.putMapper.TupleTempPutMapper;
import com.yibingo.race.common.utils.Result;



/**
 * 暂时元组
 *
 * @author Yang Xin
 * @email little-cy@qq.com
 * @date 2022-05-26 20:06:52
 */
@Api(
        value = "tupletemp",
        tags = "暂时元组"
)
@RestController
@RequestMapping("core/tupletemp")
public class TupleTempController {
    @Autowired
    private TupleTempService tupleTempService;



    @ApiOperation(
            value = "暂时元组查询",
            notes = "暂时元组查询"
    )
    @RequestMapping(
            value = "/info",
            method = RequestMethod.GET
    )
    @ResponseBody
//    @RequiresPermissions("core:tupletemp:info")
    public Map<String,Object> info(@RequestParam String id) {
            TupleTemp tupleTemp = tupleTempService.getById(id);

        return Result.success(tupleTemp).map();
    }

    /**
     * 保存
     */
    @ApiOperation(
            value = "暂时元组保存",
            notes = "暂时元组保存"
    )
    @RequestMapping(
            value = "/save",
            method = RequestMethod.POST
    )
    @ResponseBody
//    @RequiresPermissions("core:tupletemp:save")
    public Map<String,Object> save(@RequestBody TupleTempPutMapper tupleTempPutMapper){
        tupleTempService.save(TupleTempPutMapper.convertToEntity(tupleTempPutMapper));

        return Result.success().map();
    }

    /**
     * 修改
     */
    @ApiOperation(
            value = "暂时元组修改",
            notes = "暂时元组修改"
    )
    @RequestMapping(
            value = "/update",
            method = RequestMethod.POST
    )
    @ResponseBody
//    @RequiresPermissions("core:tupletemp:update")
    public Map<String,Object> update(@RequestBody TupleTemp tupleTemp){
        tupleTempService.updateById(tupleTemp);

        return Result.success().map();
    }

    /**
     * 删除
     */

    @ApiOperation(
            value = "暂时元组批量删除",
            notes = "暂时元组批量删除"
    )
    @RequestMapping(
            value = "/delete",
            method = RequestMethod.POST
    )
    @ResponseBody
//    @RequiresPermissions("core:tupletemp:delete")
    public Map<String,Object> delete(@RequestBody List<String> ids){
        tupleTempService.removeByIds(ids);

        return Result.success().map();
    }




}
