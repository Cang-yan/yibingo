package com.yibingo.race.web.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.yibingo.race.dal.entity.Tuple;
import com.yibingo.race.core.service.TupleService;
import com.yibingo.race.dal.filterMapper.TupleFilterMapper;
import com.yibingo.race.dal.putMapper.TuplePutMapper;
import com.yibingo.race.common.utils.Result;



/**
 * 元组
 *
 * @author Yang Xin
 * @email little-cy@qq.com
 * @date 2022-05-26 20:03:13
 */
@Api(
        value = "tuple",
        tags = "元组"
)
@RestController
@RequestMapping("core/tuple")
public class TupleController {
    @Autowired
    private TupleService tupleService;



    @ApiOperation(
            value = "元组查询",
            notes = "元组查询"
    )
    @RequestMapping(
            value = "/info",
            method = RequestMethod.GET
    )
    @ResponseBody
//    @RequiresPermissions("core:tuple:info")
    public Map<String,Object> info(@RequestParam String id) {
            Tuple tuple = tupleService.getById(id);

        return Result.success(tuple).map();
    }

    /**
     * 保存
     */
    @ApiOperation(
            value = "元组保存",
            notes = "元组保存"
    )
    @RequestMapping(
            value = "/save",
            method = RequestMethod.POST
    )
    @ResponseBody
//    @RequiresPermissions("core:tuple:save")
    public Map<String,Object> save(@RequestBody TuplePutMapper tuplePutMapper){
        tupleService.save(TuplePutMapper.convertToEntity(tuplePutMapper));

        return Result.success().map();
    }

    /**
     * 修改
     */
    @ApiOperation(
            value = "元组修改",
            notes = "元组修改"
    )
    @RequestMapping(
            value = "/update",
            method = RequestMethod.POST
    )
    @ResponseBody
//    @RequiresPermissions("core:tuple:update")
    public Map<String,Object> update(@RequestBody Tuple tuple){
        tupleService.updateById(tuple);

        return Result.success().map();
    }

    /**
     * 删除
     */

    @ApiOperation(
            value = "元组批量删除",
            notes = "元组批量删除"
    )
    @RequestMapping(
            value = "/delete",
            method = RequestMethod.POST
    )
    @ResponseBody
//    @RequiresPermissions("core:tuple:delete")
    public Map<String,Object> delete(@RequestBody List<String> ids){
        tupleService.removeByIds(ids);

        return Result.success().map();
    }




}
