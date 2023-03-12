package com.yibingo.race.web.controller;

import com.yibingo.race.common.utils.Result;
import com.yibingo.race.core.service.FormExtendsService;
import com.yibingo.race.core.service.OCRService;
import com.yibingo.race.dal.filterMapper.FormFilterMapper;
import com.yibingo.race.dal.putMapper.FormPutMapper;
import com.yibingo.race.dal.putMapper.FormRecordsPutMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * 表单发布扩展
 *
 * @author Yang Xin
 * @email little-cy@qq.com
 * @date 2022-05-15 18:42:55
 */
@Api(
        value = "form_extends",
        tags = "表单发布扩展管理"
)
@RestController
@RequestMapping("core/form/extends")
public class FormExtendsController {

    @Autowired
    private OCRService ocrService;

    @Autowired
    private FormExtendsService formExtendsService;

    @ApiOperation(
            value = "创建表单时返回上次创建的表单记录",
            notes = "创建表单时返回上次创建的表单记录"
    )
    @RequestMapping(
            value = "/records",
            method = RequestMethod.GET
    )
    @ResponseBody
    public Map<String, Object> getLastCreate(@RequestParam String userId) {
        return Result.success(
                formExtendsService.getLastCreate(userId)
        ).map();

    }

    @ApiOperation(
            value = "创建表单",
            notes = "创建表单"
    )
    @RequestMapping(
            value = "/create",
            method = RequestMethod.POST
    )
    @ResponseBody
    public Map<String, Object> createForm(@RequestBody FormPutMapper formPutMapper) {
        formExtendsService.createForm(formPutMapper);
        return Result.success().map();
    }

    @ApiOperation(
            value = "分别查询进行中和已结束收集表",
            notes = "分别查询进行中和已结束收集表"
    )
    @RequestMapping(
            value = "/get/created",
            method = RequestMethod.GET
    )
    @ResponseBody
    public Map<String, Object> getFormNotEnd(@RequestParam String userId, @RequestParam Integer isEnd) {
        return Result.success(
                formExtendsService.getFormNotEnd(userId, isEnd)
        ).map();

    }

    @ApiOperation(
            value = "获取某个表的具体数据全部",
            notes = "获取某个表的具体数据全部"
    )
    @RequestMapping(
            value = "/get/created/data",
            method = RequestMethod.GET
    )
    @ResponseBody
    public Map<String, Object> getFormStatisticData(@RequestParam String formId) {

        return Result.success(
                formExtendsService.getFormStatisticData(formId)
        ).map();
    }

    @ApiOperation(
            value = "获取某个表的大体数据-仅到元组",
            notes = "获取某个表的大体数据-仅到元组"
    )
    @RequestMapping(
            value = "/get/created/simple",
            method = RequestMethod.GET
    )
    @ResponseBody
    public Map<String, Object> getFormStatisticSimpleData(@RequestParam String formId) {

        return Result.success(
                formExtendsService.getFormStatisticSimpleData(formId)
        ).map();
    }

    @ApiOperation(
            value = "获取某个表的元组的具体数据",
            notes = "获取某个表的元组的具体数据"
    )
    @RequestMapping(
            value = "/get/create/tuple/data",
            method = RequestMethod.GET
    )
    @ResponseBody
    public Map<String, Object> getFormTupleData(@RequestParam String formId,@RequestParam String tupleId){
        return Result.success(
                formExtendsService.getFormTupleData("",formId,tupleId)
        ).map();
    }




    @ApiOperation(
            value = "查询我参与的表单",
            notes = "查询我参与的表单"
    )
    @RequestMapping(
            value = "/get/participate/data",
            method = RequestMethod.GET
    )
    @ResponseBody
    public Map<String, Object> getMyParticipateForm(@RequestParam String userId) {

        return Result.success(
                formExtendsService.getMyParticipateForm(userId)
        ).map();
    }

    @ApiOperation(
            value = "查询我参与的表单的元组的具体信息",
            notes = "查询我参与的表单的元组的具体信息"
    )
    @RequestMapping(
            value = "/get/participate/tuple",
            method = RequestMethod.GET
    )
    @ResponseBody
    public Map<String, Object> getParticipateTupleData(@RequestParam String userId,@RequestParam String formId,@RequestParam String tupleId){
        return Result.success(
                formExtendsService.getFormTupleData(userId,formId,tupleId)
        ).map();
    }

    @ApiOperation(
            value = "填写表格",
            notes = "填写表格"
    )
    @RequestMapping(
            value = "/post/form",
            method = RequestMethod.POST
    )
    @ResponseBody
    public Map<String, Object> postFormRecords(@RequestBody List<FormRecordsPutMapper> formRecordsPutMapperList) {
        formExtendsService.postFormRecords(formRecordsPutMapperList);
        return Result.success().map();
    }




    @ApiOperation(
            value = "搜索条件查询",
            notes = "搜索条件查询"
    )
    @RequestMapping(
            value = "/search",
            method = RequestMethod.GET
    )
    @ResponseBody
    public Map<String, Object> getListByFilter(@RequestParam(value = "updateTimeFrom", required = false) Long updateTimeFrom,
                                               @RequestParam(value = "updateTimeTo", required = false) Long updateTimeTo,
                                               @RequestParam(value = "createTimeFrom", required = false) Long createTimeFrom,
                                               @RequestParam(value = "createTimeTo", required = false) Long createTimeTo,
                                               @RequestParam(value = "page", required = false, defaultValue = "1") Long page,
                                               @RequestParam(value = "row", required = false, defaultValue = "10") Long row,
                                               @RequestParam(value = "orderBy", required = false) List<String> orderBy
    ) {
        FormFilterMapper mapper = new FormFilterMapper();
        mapper.updateTimeFrom = updateTimeFrom;
        mapper.updateTimeTo = updateTimeTo;
        mapper.createTimeFrom = createTimeFrom;
        mapper.createTimeTo = createTimeTo;
        mapper.page = page;
        mapper.row = row;
        mapper.orderBy = orderBy;
        return Result.success(formExtendsService.getListByFilter(mapper)).map();
    }


}