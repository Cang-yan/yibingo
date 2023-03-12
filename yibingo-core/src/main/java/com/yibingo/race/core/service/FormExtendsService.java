package com.yibingo.race.core.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.base.CaseFormat;
import com.yibingo.race.common.exception.BaseException;
import com.yibingo.race.dal.detailMapper.FormCountDetailMapper;
import com.yibingo.race.dal.detailMapper.FormDetailMapper;
import com.yibingo.race.dal.detailMapper.TupleDetailMapper;
import com.yibingo.race.dal.detailMapper.TupleMemberDetailMapper;
import com.yibingo.race.dal.entity.*;
import com.yibingo.race.dal.filterMapper.*;
import com.yibingo.race.dal.putMapper.FormPutMapper;
import com.yibingo.race.dal.putMapper.FormRecordsPutMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 表单发布扩展
 *
 * @author Yang Xin
 * @email little-cy@qq.com
 * @date 2022-05-15 18:42:55
 */
@Service
public class FormExtendsService {

    @Autowired
    private FormService formService;

    @Autowired
    private FormLabelService formLabelService;

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private OrganizationExtendsService organizationExtendsService;

    @Autowired
    private TupleService tupleService;
    @Autowired
    private TupleMemberExtendsService tupleMemberExtendsService;

    @Autowired
    private FormCountService formCountService;

    @Autowired
    private FormCountExtendsService formCountExtendsService;

    @Autowired
    private FormRecordsService formRecordsService;
    @Autowired
    private FormRecordsExtendsService formRecordsExtendsService;
    @Autowired
    private ImgHealthCodeExtendsService imgHealthCodeExtendsService;
    @Autowired
    private ImgTravelCardExtendsService imgTravelCardExtendsService;
    @Autowired
    private FormCountTupleService formCountTupleService;
    @Autowired
    private FormCountTupleExtendsService formCountTupleExtendsService;
    @Autowired
    private FormLabelImgService formLabelImgService;
    @Autowired
    private MessageExtendsService messageExtendsService;

    /*
     * 查询上次创建的
     * */
    public Map<String, Object> getLastCreate(String userId) {
        FormFilterMapper formFilterMapper = new FormFilterMapper();
        formFilterMapper.createUserId = userId;
        //默认按照创建时间降序排列
        Form form = getListByFilter(formFilterMapper).stream().findFirst().orElse(new Form());

        List<FormLabel> formLabelList = formLabelService.list();
        List<FormLabelImg> formLabelImgList = formLabelImgService.list();
        Map<String, Object> historyRecord = FormDetailMapper.buildMap(form);
        historyRecord.put("labelList", formLabelList);
        historyRecord.put("labelImgList",formLabelImgList);
        return historyRecord;
    }

    /*
     *
     * 创建收集表以及表项
     * */
    public void createForm(FormPutMapper formPutMapper) {
        Form form = FormPutMapper.convertToEntity(formPutMapper);
        formService.save(form);

        //该创建表单标签了
        Map<String, Object> labelMap = form.getLabelCombination();
        //需要去重
        List<Object> titleValue = Arrays.asList(labelMap.values().toArray()).stream().distinct().collect(Collectors.toList());
        List<String> formLabelList = formLabelService.list().stream().map(FormLabel::getTitle).collect(Collectors.toList());
        List<FormLabel> readyToCreate = new ArrayList<>();
        for (Object title : titleValue) {
            String titleStr = StrUtil.toString(title);
            if (!formLabelList.contains(titleStr)) {
                FormLabel formLabel = new FormLabel();
                formLabel.setTitle(titleStr);
                readyToCreate.add(formLabel);
            }
        }
        formLabelService.saveBatch(readyToCreate);
        //创建需要传图片的标签
        Map<String, Object> labelImgMap = form.getLabelCombinationImg();
        //需要去重
        List<Object> imgTitleValue = Arrays.asList(labelImgMap.values().toArray()).stream().distinct().collect(Collectors.toList());
        List<String> ImgLabelList = formLabelImgService.list().stream().map(FormLabelImg::getTitle).collect(Collectors.toList());
        List<FormLabelImg> readyToCreateImgLable = new ArrayList<>();
        for (Object title : imgTitleValue) {
            String titleStr = StrUtil.toString(title);
            if (!ImgLabelList.contains(titleStr)) {
                FormLabel formLabel = new FormLabel();
                formLabel.setTitle(titleStr);
                readyToCreate.add(formLabel);
            }
        }

        formLabelImgService.saveBatch(readyToCreateImgLable);
        TupleMemberFilterMapper tupleMemberFilterMapper = new TupleMemberFilterMapper();
        tupleMemberFilterMapper.organizationId = form.getOrganizationId();
        List<TupleMember> tupleMemberList = tupleMemberExtendsService.getListByFilter(tupleMemberFilterMapper);
        Integer count = 0;
        if (!tupleMemberList.isEmpty()) {
            List<String> userIdList = tupleMemberList.stream().map(TupleMember::getUserId).collect(Collectors.toList());
            List<String> tupleIdList = tupleMemberList.stream().map(TupleMember::getTupleId).collect(Collectors.toList());
            List<Tuple> tupleList = tupleService.listByIds(tupleIdList);
            count = tupleMemberList.size();

            //创建表单记录
            List<FormRecords> formRecordsList = new ArrayList<>();
            for (TupleMember tupleMember : tupleMemberList) {
                FormRecords formRecords = new FormRecords();
                formRecords.setFormId(form.getId());
                formRecords.setUserId(tupleMember.getUserId());
                formRecords.setOrganizationId(tupleMember.getOrganizationId());
                formRecords.setTupleId(tupleMember.getTupleId());
                formRecordsList.add(formRecords);
            }
            formRecordsService.saveBatch(formRecordsList);

            //创建表单元组的计数
            List<FormCountTuple> formCountTupleList = new ArrayList<>();
            for (Tuple tuple : tupleList) {
                FormCountTuple formCountTuple = new FormCountTuple();
                formCountTuple.setFormId(form.getId());
                formCountTuple.setTupleId(tuple.getId());
                formCountTuple.setUndone(tuple.getNum());
                formCountTupleList.add(formCountTuple);
            }
            formCountTupleService.saveBatch(formCountTupleList);
        }


        //创建表单计数  todo 事后再想一下这里有没有重复的可能，下同
        FormCount formCount = new FormCount();
        formCount.setFormId(form.getId());
        formCount.setUndone(count);
        formCountService.save(formCount);


    }

    /*
     * 查询进行中和结束的收集表list（我创建的
     *
     * isend   未结束为0，已经结束为1
     * */
    public List<Map<String, Object>> getFormNotEnd(String userId, Integer isEnd) {
        List<Map<String, Object>> endMapList = new ArrayList<>();
        FormFilterMapper formFilterMapper = new FormFilterMapper();
        formFilterMapper.isEnd = isEnd;
        formFilterMapper.createUserId = userId;
        List<Form> formList = getListByFilter(formFilterMapper);
        if (formList.isEmpty()) return endMapList;
        List<String> formIdList = formList.stream().map(Form::getId).collect(Collectors.toList());
        FormCountFilterMapper formCountFilterMapper = new FormCountFilterMapper();
        formCountFilterMapper.formIdIn = formIdList;
        List<FormCount> formCountList = formCountExtendsService.getListByFilter(formCountFilterMapper);
        for (Form form : formList) {
            Map<String, Object> formInfoMap = new HashMap<>();
            //表单的基本信息
            formInfoMap.putAll(FormDetailMapper.buildMap(form));
            //表单计数
            FormCount formCount = formCountList.stream().filter(
                    item -> form.getId() != null && form.getId().equals(item.getFormId())
            ).findFirst().orElse(null);
            if (formCount == null) throw new BaseException("统计表信息有误，请工作人员核实");
            formInfoMap.putAll(FormCountDetailMapper.buildMap(formCount));
            endMapList.add(formInfoMap);
        }
        return endMapList;
    }

    /*
     * 查询某个收集表的全部具体数据
     * 表单本身的信息，表单计数，以元组为单位的异常，以及元组本身的数据
     * */
    public Map<String, Object> getFormStatisticData(String formId) {
        Form form = formService.getById(formId);
        Map<String, Object> formStatisticInfo = FormDetailMapper.buildMap(form);
        Organization organization = organizationService.getById(form.getOrganizationId());
        formStatisticInfo.put("organization", organization);

        //表单计数
        FormCountFilterMapper formCountFilterMapper = new FormCountFilterMapper();
        formCountFilterMapper.formId = formId;
        FormCount formCount = formCountExtendsService.getListByFilter(formCountFilterMapper).stream().findFirst().orElse(null);
        if (formCount == null) throw new BaseException("统计表信息有误，请工作人员核实");
        formStatisticInfo.putAll(FormCountDetailMapper.buildMap(formCount));

        //元组计数
        FormCountTupleFilterMapper formCountTupleFilterMapper = new FormCountTupleFilterMapper();
        formCountTupleFilterMapper.formId = formId;
        List<FormCountTuple> formCountTupleList = formCountTupleExtendsService.getListByFilter(formCountTupleFilterMapper);

        //拿个人统计数据
        FormRecordsFilterMapper formRecordsFilterMapper = new FormRecordsFilterMapper();
        formRecordsFilterMapper.formId = formId;
        formRecordsFilterMapper.organizationId = form.getOrganizationId();
        List<FormRecords> formRecordsList = formRecordsExtendsService.getListByFilter(formRecordsFilterMapper);
        //拿图片
        List<String> formRecordsIdList = formRecordsList.stream().map(FormRecords::getFormId).collect(Collectors.toList());
        ImgHealthCodeFilterMapper imgHealthCodeFilterMapper = new ImgHealthCodeFilterMapper();
        imgHealthCodeFilterMapper.formRecordsIdIn = formRecordsIdList;
        List<ImgHealthCode> imgHealthCodeList = imgHealthCodeExtendsService.getListByFilter(imgHealthCodeFilterMapper);

        ImgTravelCardFilterMapper imgTravelCardFilterMapper = new ImgTravelCardFilterMapper();
        imgTravelCardFilterMapper.formRecordsIdIn = formRecordsIdList;
        List<ImgTravelCard> imgTravelCardList = imgTravelCardExtendsService.getListByFilter(imgTravelCardFilterMapper);

        //以元组为单位
        //拿元组 需要考虑为空
        List<Tuple> tupleList = organizationExtendsService.getTupleByOrganization(form.getOrganizationId());
        //拿元组成员，
        List<TupleMember> tupleMemberList = organizationExtendsService.getMemberByTuple(tupleList);
        //此时需要绑定，元组成员和完成状态，一整个元组的绑定
        List<Map<String, Object>> tupleDetailInfoMapList = new ArrayList<>();
        for (Tuple tuple : tupleList) {
            Map<String, Object> tupleMap = new HashMap<>();
            tupleMap.putAll(TupleDetailMapper.buildMap(tuple));

            FormCountTuple formCountTuple = formCountTupleList.stream().filter(
                    item -> tuple.getId() != null && tuple.getId().equals(item.getTupleId())
            ).findFirst().orElse(null);
            if (formCountTuple == null) new BaseException("统计记录有问题，请工作人员核实");

            tupleMap.put("tupleUndone", formCountTuple.getUndone());
            tupleMap.put("tupleDone", formCountTuple.getDone());
            tupleMap.put("tupleAbnomal", formCountTuple.getAbnormal());

            List<TupleMember> tupleMembers = tupleMemberList.stream().filter(
                    item -> tuple.getId() != null && tuple.getId().equals(item.getTupleId())
            ).collect(Collectors.toList());
            //由于是管理员端，所以不需要是否是自己是否是管理员
            List<Map<String, Object>> tupleMemberMapList = bindTupleMemberDetailMap("", tuple, tupleMemberList, formRecordsList, imgHealthCodeList, imgTravelCardList);


            tupleMap.put("tupleMember", tupleMemberMapList);
            tupleDetailInfoMapList.add(tupleMap);
        }
        formStatisticInfo.put("tupleInfo", tupleDetailInfoMapList);
        return formStatisticInfo;

    }

    /*
     * 查询某个收集表的全部元组的数据
     *
     * */
    public Map<String, Object> getFormStatisticSimpleData(String formId) {
        Form form = formService.getById(formId);
        Map<String, Object> formStatisticInfo = FormDetailMapper.buildMap(form);
        Organization organization = organizationService.getById(form.getOrganizationId());
        formStatisticInfo.put("organization", organization);

        List<Tuple> tupleList = organizationExtendsService.getTupleByOrganization(organization.getId());
        //表单计数
        FormCountFilterMapper formCountFilterMapper = new FormCountFilterMapper();
        formCountFilterMapper.formId = formId;
        FormCount formCount = formCountExtendsService.getListByFilter(formCountFilterMapper).stream().findFirst().orElse(null);
        if (formCount == null) throw new BaseException("统计表信息有误，请工作人员核实");
        formStatisticInfo.putAll(FormCountDetailMapper.buildMap(formCount));
        formStatisticInfo.put("formCount", formCount);

        //元组计数
        FormCountTupleFilterMapper formCountTupleFilterMapper = new FormCountTupleFilterMapper();
        formCountTupleFilterMapper.formId = formId;
        List<FormCountTuple> formCountTupleList = formCountTupleExtendsService.getListByFilter(formCountTupleFilterMapper);
        List<Map<String, Object>> tupleInfoMapList = new ArrayList<>();
        for (Tuple tuple : tupleList) {
            Map<String, Object> tupleInfoMap = TupleDetailMapper.buildMap(tuple);
            FormCountTuple formCountTuple = formCountTupleList.stream().filter(
                    item -> tuple.getId() != null && tuple.getId().equals(item.getTupleId())
            ).findFirst().orElse(null);
            if (formCountTuple == null) throw new BaseException("统计有误");
            tupleInfoMap.put("formTupleCount", formCountTuple);
            tupleInfoMapList.add(tupleInfoMap);
        }
        formStatisticInfo.put("tupleInfo", tupleInfoMapList);
        return formStatisticInfo;

    }

    /*
     * 查询某个收集表的某个元组的具体数据
     * */
    public Map<String, Object> getFormTupleData(String userId, String formId, String tupleId) {

        FormCountTupleFilterMapper formCountTupleFilterMapper = new FormCountTupleFilterMapper();
        formCountTupleFilterMapper.formId = formId;
        formCountTupleFilterMapper.tupleId = tupleId;
        FormCountTuple formCountTuple = formCountTupleExtendsService.getListByFilter(formCountTupleFilterMapper).stream().findFirst().orElse(null);
        if (formCountTuple == null) throw new BaseException("统计表有误，请联系管理员");
        //拿个人统计数据
        FormRecordsFilterMapper formRecordsFilterMapper = new FormRecordsFilterMapper();
        formRecordsFilterMapper.formId = formId;
        formRecordsFilterMapper.tupleId = tupleId;
        List<FormRecords> formRecordsList = formRecordsExtendsService.getListByFilter(formRecordsFilterMapper);
        //拿图片
        List<String> formRecordsIdList = formRecordsList.stream().map(FormRecords::getFormId).collect(Collectors.toList());
        ImgHealthCodeFilterMapper imgHealthCodeFilterMapper = new ImgHealthCodeFilterMapper();
        imgHealthCodeFilterMapper.formRecordsIdIn = formRecordsIdList;
        List<ImgHealthCode> imgHealthCodeList = imgHealthCodeExtendsService.getListByFilter(imgHealthCodeFilterMapper);

        ImgTravelCardFilterMapper imgTravelCardFilterMapper = new ImgTravelCardFilterMapper();
        imgTravelCardFilterMapper.formRecordsIdIn = formRecordsIdList;
        List<ImgTravelCard> imgTravelCardList = imgTravelCardExtendsService.getListByFilter(imgTravelCardFilterMapper);

        //以元组为单位
        //拿元组 需要考虑为空
        Tuple tuple = tupleService.getById(tupleId);
        //拿元组成员，
        List<TupleMember> tupleMemberList = organizationExtendsService.getMemberByTuple(Collections.singletonList(tuple));

        Map<String, Object> tupleMap = new HashMap<>();
        tupleMap.putAll(TupleDetailMapper.buildMap(tuple));

        tupleMap.put("tupleUndone", formCountTuple.getUndone());
        tupleMap.put("tupleDone", formCountTuple.getDone());
        tupleMap.put("tupleAbnomal", formCountTuple.getAbnormal());

        //由于是管理员端，所以不需要是否是自己是否是管理员
        List<Map<String, Object>> tupleMemberMapList = bindTupleMemberDetailMap(userId, tuple, tupleMemberList, formRecordsList, imgHealthCodeList, imgTravelCardList);


        tupleMap.put("tupleMember", tupleMemberMapList);
        return tupleMap;
    }

    /*
     * 查询我参与的表单列表，只到元组的统计
     * 包含信息有，表单的信息，元组的信息，元组成员的信息
     *
     * */
    public List<Map<String, Object>> getMyParticipateForm(String userId) {
        List<Map<String, Object>> endMapList = new ArrayList<>();
        //这里是我本人所对应的表单记录
        FormRecordsFilterMapper myFormRecordsFilterMapper = new FormRecordsFilterMapper();
        myFormRecordsFilterMapper.userId = userId;
        List<FormRecords> myFormRecordsList = formRecordsExtendsService.getListByFilter(myFormRecordsFilterMapper);
        if (myFormRecordsList.isEmpty()) return new ArrayList<>();
        List<String> formIdList = myFormRecordsList.stream().map(FormRecords::getFormId).collect(Collectors.toList());
        List<Form> formList = formService.listByIds(formIdList);
        //拿元组
        List<String> tupleIdList = myFormRecordsList.stream().map(FormRecords::getTupleId).collect(Collectors.toList());
        List<Tuple> tupleList = tupleService.listByIds(tupleIdList);

        //元组统计数据
        FormCountTupleFilterMapper formCountTupleFilterMapper = new FormCountTupleFilterMapper();
        formCountTupleFilterMapper.tupleIdIn = tupleIdList;
        List<FormCountTuple> formCountTupleList = formCountTupleExtendsService.getListByFilter(formCountTupleFilterMapper);

        for (Form form : formList) {
            Map<String, Object> formMap = FormDetailMapper.buildMap(form);
            List<Map<String, Object>> tupleInfoMapList = new ArrayList<>();
            Organization organization = organizationService.getById(form.getOrganizationId());
            for (Tuple tuple : tupleList) {
                if (!tuple.getOrganizationId().equals(organization.getId())) continue;
                Map<String, Object> tupleInfoMap = TupleDetailMapper.buildMap(tuple);
                FormCountTuple formCountTuple = formCountTupleList.stream().filter(
                        item -> tuple.getId() != null && tuple.getId().equals(item.getTupleId())
                ).findFirst().orElse(null);
                if (formCountTuple == null) throw new BaseException("统计有误");
                tupleInfoMap.put("formTupleCount", formCountTuple);
                tupleInfoMapList.add(tupleInfoMap);

            }
            formMap.put("head", organization.getHead());
            formMap.put("tupleInfo", tupleInfoMapList);
            endMapList.add(formMap);
        }
        return endMapList;


        //拿我所在的元组各个成员对应的记录
//        FormRecordsFilterMapper tupleFormRecordsFilterMapper = new FormRecordsFilterMapper();
//        tupleFormRecordsFilterMapper.tupleIdIn=tupleIdList;
//        List<FormRecords> tupleFormRecordsList = formRecordsExtendsService.getListByFilter(tupleFormRecordsFilterMapper);

        //拿图片
//        List<String> formRecordsIdList = myFormRecordsList.stream().map(FormRecords::getFormId).collect(Collectors.toList());
//        ImgHealthCodeFilterMapper imgHealthCodeFilterMapper = new ImgHealthCodeFilterMapper();
//        imgHealthCodeFilterMapper.formRecordsIdIn = formRecordsIdList;
//        List<ImgHealthCode> imgHealthCodeList = imgHealthCodeExtendsService.getListByFilter(imgHealthCodeFilterMapper);
//
//        ImgTravelCardFilterMapper imgTravelCardFilterMapper = new ImgTravelCardFilterMapper();
//        imgTravelCardFilterMapper.formRecordsIdIn = formRecordsIdList;
//        List<ImgTravelCard> imgTravelCardList = imgTravelCardExtendsService.getListByFilter(imgTravelCardFilterMapper);
//
//        for (Form form : formList) {
//            Tuple tuple = tupleList.stream().filter(
//                    item -> form.getOrganizationId() != null && form.getOrganizationId().equals(item.getOrganizationId())
//            ).findFirst().orElse(null);
//            if (tuple == null) throw new BaseException("信息有误");
//            Map<String, Object> formMap = FormDetailMapper.buildMap(form);
        //拿到元组的表单记录
//            FormCountTupleFilterMapper formCountTupleFilterMapper = new FormCountTupleFilterMapper();
//            formCountTupleFilterMapper.formId = form.getId();
//            formCountTupleFilterMapper.tupleId = tuple.getId();
//            FormCountTuple formCountTuple = formCountTupleExtendsService.getListByFilter(formCountTupleFilterMapper).stream().findFirst().orElse(null);
//            if (formCountTuple == null) throw new BaseException("信息有误");
//            Map<String, Object> tupleInfoMap = TupleDetailMapper.buildMap(tuple);
        //元组成员
//            TupleMemberFilterMapper tupleMemberFilterMapper = new TupleMemberFilterMapper();
//            tupleMemberFilterMapper.tupleId = tuple.getId();
//            List<TupleMember> tupleMemberList = tupleMemberExtendsService.getListByFilter(tupleMemberFilterMapper);
        //绑定元组成员信息
//            List<Map<String, Object>> tupleMemberMapList = bindTupleMemberDetailMap(userId, tuple, tupleMemberList, tupleFormRecordsList, imgHealthCodeList, imgTravelCardList);
//
//            tupleInfoMap.put("tupleMember", tupleMemberMapList);
//            formMap.put("tupleInfo", tupleInfoMap);
//            endMapList.add(formMap);
//        }
//
//        return endMapList;

    }

    /*
     *
     * */


    /*
     * 绑定元组成员各项统计信息
     * 成员list  表单记录list  两个图片list,
     *
     * */

    private List<Map<String, Object>> bindTupleMemberDetailMap(String userId,
                                                               Tuple tuple,
                                                               List<TupleMember> tupleMemberList,
                                                               List<FormRecords> formRecordsList,
                                                               List<ImgHealthCode> imgHealthCodeList,
                                                               List<ImgTravelCard> imgTravelCardList) {
        List<Map<String, Object>> tupleMemberMapList = new ArrayList<>();
        for (TupleMember tupleMember : tupleMemberList) {
            //  和两张图片绑定一下
            Map<String, Object> tupleMemberMap = TupleMemberDetailMapper.buildMap(tupleMember);
            FormRecords formRecords = formRecordsList.stream().filter(
                    item -> tupleMember.getUserId() != null && tupleMember.getUserId().equals(item.getUserId())
            ).findFirst().orElse(null);
            if (formRecords == null) throw new BaseException("统计记录有问题，请工作人员核实");
            Integer status = formRecords.getStatus();
            tupleMemberMap.put("formRecords", formRecords);
            ImgHealthCode imgHealthCode = imgHealthCodeList.stream().filter(
                    item -> formRecords.getId().equals(item.getFormRecordsId())
            ).findFirst().orElse(new ImgHealthCode());

            ImgTravelCard imgTravelCard = imgTravelCardList.stream().filter(
                    item -> formRecords.getId().equals(item.getFormRecordsId())
            ).findFirst().orElse(new ImgTravelCard());

            tupleMemberMap.put("imgHealthCode", imgHealthCode);
            tupleMemberMap.put("imgTravelCard", imgTravelCard);
            int isCreateUser = 0;
            int isMe = 0;
            if (tupleMember.getUserId().equals(tuple.getCreateUserId())) isCreateUser = 1;
            if (tupleMember.getUserId().equals(userId)) isMe = 1;
            tupleMemberMap.put("isCreateUser", isCreateUser);
            tupleMemberMap.put("isMe", isMe);
            tupleMemberMapList.add(tupleMemberMap);
        }
        return tupleMemberMapList;

    }


    /*
     * 填写表格
     * 主要是计数上的变化
     *
     * 前端调ocr拿到返回的status值，放在这里
     * */
    public void postFormRecords(List<FormRecordsPutMapper> formRecordsPutMapperList) {
        List<FormRecords> formRecordsList = new ArrayList<>();
        String formId = formRecordsPutMapperList.stream().findAny().orElse(new FormRecordsPutMapper()).getFormId();
        //表格总的统计表
        FormCountFilterMapper formCountFilterMapper = new FormCountFilterMapper();
        formCountFilterMapper.formId = formId;
        FormCount formCount = formCountExtendsService.getListByFilter(formCountFilterMapper).stream().findFirst().orElse(null);
        if (formCount == null) throw new BaseException("统计表有误");
        //元组统计表
        FormCountTupleFilterMapper formCountTupleFilterMapper = new FormCountTupleFilterMapper();
        formCountTupleFilterMapper.formId = formId;
        List<FormCountTuple> formCountTupleList = formCountTupleExtendsService.getListByFilter(formCountTupleFilterMapper);
        List<FormCountTuple> formCountTupleToUpdate = new ArrayList<>();

        FormRecordsFilterMapper formRecordsFilterMapper = new FormRecordsFilterMapper();
        formRecordsFilterMapper.formId = formId;
        List<FormRecords> formRecordsList1 = formRecordsExtendsService.getListByFilter(formRecordsFilterMapper);


        Integer undone = formCount.getUndone();
        Integer done = formCount.getDone();
        Integer abnormal = formCount.getAbnormal();

        for (FormRecordsPutMapper model : formRecordsPutMapperList) {
            FormRecords formRecords = FormRecordsPutMapper.convertToEntity(model);
            //原来的表格里存在的表单记录
            FormRecords preFormRecords = formRecordsList1.stream().filter(
                    item -> formRecords.getUserId() != null && formRecords.getUserId().equals(item.getUserId())
            ).findFirst().orElse(null);
            if (preFormRecords == null) throw new BaseException("统计发布时您不在群组内，暂不支持您填表");
            if (!preFormRecords.getStatus().equals(0)) break;
            //修改原表单记录
            preFormRecords.setHealthyCodeUrl(formRecords.getHealthyCodeUrl());
            preFormRecords.setLabelCombination(formRecords.getLabelCombination());
            preFormRecords.setLabelCombinationImg(formRecords.getLabelCombinationImg());
            preFormRecords.setStatus(formRecords.getStatus());
            preFormRecords.setTravelCardUrl(formRecords.getTravelCardUrl());

            formRecordsList.add(preFormRecords);

            FormCountTuple formCountTuple = formCountTupleList.stream().filter(
                    item -> model.getTupleId() != null && model.getTupleId().equals(item.getTupleId())
            ).findFirst().orElse(null);
            if (formCountTuple == null) throw new BaseException("元组统计表有误");
            undone--;
            if (formRecords.getStatus().equals(1)) {
                done++;
                formCountTuple.setUndone(formCountTuple.getUndone() - 1);
                formCountTuple.setDone(formCountTuple.getDone() + 1);
            }
            if (formRecords.getStatus().equals(2)) {
                abnormal++;
                formCountTuple.setUndone(formCountTuple.getUndone() - 1);
                formCountTuple.setAbnormal(formCountTuple.getAbnormal() + 1);
                Tuple tuple = tupleService.getById(formRecords.getTupleId());
                //创建异常通知
                messageExtendsService.createWarnMessage(formRecords.getFormId(), tuple.getTitle());
            }
            formCountTupleToUpdate.add(formCountTuple);
        }
        formCount.setUndone(undone);
        formCount.setDone(done);
        formCount.setAbnormal(abnormal);
        formRecordsService.updateBatchById(formRecordsList);
        formCountTupleService.updateBatchById(formCountTupleToUpdate);
        formCountService.updateById(formCount);

    }

    /*
     * 新加入成员，为其创建填写记录
     * */
    public void createNewMemberRecords(TupleMember newTupleMember) {
        FormFilterMapper formFilterMapper = new FormFilterMapper();
        formFilterMapper.isEnd = 0;
        formFilterMapper.organizationId = newTupleMember.getOrganizationId();
        List<Form> formList = getListByFilter(formFilterMapper);
        List<FormCount> toUpdateFormCountList = new ArrayList<>();
        List<FormCountTuple> toUpdateFormCountTupleList = new ArrayList<>();
        List<FormRecords> toCreateFormRecordsList = new ArrayList<>();
        for (Form form : formList) {
            FormCountFilterMapper formCountFilterMapper = new FormCountFilterMapper();
            formCountFilterMapper.formId = form.getId();
            FormCount formCount = formCountExtendsService.getListByFilter(formCountFilterMapper).stream().findFirst().orElse(null);
            formCount.setUndone(formCount.getUndone()+1);
            toUpdateFormCountList.add(formCount);

            FormCountTupleFilterMapper formCountTupleFilterMapper = new FormCountTupleFilterMapper();
            formCountTupleFilterMapper.formId = form.getId();
            formCountTupleFilterMapper.tupleId = newTupleMember.getTupleId();
            FormCountTuple formCountTuple = formCountTupleExtendsService.getListByFilter(formCountTupleFilterMapper).stream().findFirst().orElse(null);
            formCountTuple.setUndone(formCount.getUndone()+1);
            toUpdateFormCountTupleList.add(formCountTuple);

            FormRecords formRecords = new FormRecords();
            formRecords.setTupleId(newTupleMember.getTupleId());
            formRecords.setOrganizationId(newTupleMember.getOrganizationId());
            formRecords.setUserId(newTupleMember.getUserId());
            formRecords.setFormId(form.getId());
            toCreateFormRecordsList.add(formRecords);
        }

        formCountService.updateBatchById(toUpdateFormCountList);
        formCountTupleService.updateBatchById(toUpdateFormCountTupleList);
        formRecordsService.saveBatch(toCreateFormRecordsList);

    }


    public List<Form> getListByFilter(FormFilterMapper filterMapper) {

        QueryWrapper<Form> wrapper = new QueryWrapper<>();

        if (filterMapper.orderBy != null) {
            for (String orderBy : filterMapper.orderBy) {
                int desc = orderBy.indexOf("desc");
                int asc = orderBy.indexOf("asc");
                if (desc != -1 && asc == -1) {
                    wrapper.orderByDesc(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, orderBy.substring(0, desc - 1)));
                }
                if (desc == -1 && asc != -1) {
                    wrapper.orderByAsc(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, orderBy.substring(0, asc - 1)));
                }
            }
        } else {
            wrapper.orderByDesc("create_time");
        }

        if (filterMapper.updateTimeFrom != null) wrapper.ge("update_time", new Date(filterMapper.updateTimeFrom));
        if (filterMapper.updateTimeTo != null) wrapper.le("update_time", new Date(filterMapper.updateTimeTo));
        if (filterMapper.createTimeFrom != null) wrapper.ge("create_time", new Date(filterMapper.createTimeFrom));
        if (filterMapper.createTimeTo != null) wrapper.le("create_time", new Date(filterMapper.createTimeTo));
        if (filterMapper.createUserId != null) wrapper.eq("create_user_id", filterMapper.createUserId);
        if (filterMapper.isEnd != null) wrapper.eq("is_end", filterMapper.isEnd);
        if (filterMapper.scheduleRemindFrom != null) wrapper.ge("schedule_remind", filterMapper.scheduleRemindFrom);
        if (filterMapper.organizationId != null) wrapper.eq("organization_id", filterMapper.organizationId);

        Long page = 1L;
        Long row = -1L;
        if (filterMapper.page != null) page = filterMapper.page;
        if (filterMapper.row != null) row = filterMapper.row;

        Page<Form> markPage = new Page<>(page, row);

        Page<Form> resultList = formService.page(markPage, wrapper);

        return resultList.getRecords();
    }


}