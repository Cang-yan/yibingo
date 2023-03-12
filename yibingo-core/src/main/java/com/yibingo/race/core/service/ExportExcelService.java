package com.yibingo.race.core.service;

import com.yibingo.race.core.service.enums.FormRecordsEnum;
import com.yibingo.race.dal.entity.Form;
import com.yibingo.race.dal.entity.FormRecords;
import com.yibingo.race.dal.entity.ImgHealthCode;
import com.yibingo.race.dal.entity.ImgTravelCard;
import com.yibingo.race.dal.filterMapper.FormRecordsFilterMapper;
import com.yibingo.race.dal.filterMapper.ImgHealthCodeFilterMapper;
import com.yibingo.race.dal.filterMapper.ImgTravelCardFilterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: 参考 https://www.hutool.cn/docs/#/poi/Excel%E7%94%9F%E6%88%90-ExcelWriter
 * @author: Yang Xin
 * @time: 2022/5/31 15:54
 */
@Service
public class ExportExcelService {
    @Autowired
    FormRecordsExtendsService formRecordsExtendsService;

    @Autowired
    FormExtendsService formExtendsService;

    @Autowired
    FormService formService;

    @Autowired
    ImgHealthCodeExtendsService imgHealthCodeExtendsService;

    @Autowired
    ImgTravelCardExtendsService imgTravelCardExtendsService;



    /*
     * 创建excel
     * */


    /*
     * 创建表格数据
     * */
    public List<Map<String, Object>> hutoolExcel(Form form) {

        Integer exitHealthyCode = form.getHealthyCode();
        Integer exitTravelCard = form.getTravelCard();

        FormRecordsFilterMapper formRecordsFilterMapper = new FormRecordsFilterMapper();
        formRecordsFilterMapper.formId = form.getId();
        List<FormRecords> formRecordsList = formRecordsExtendsService.getListByFilter(formRecordsFilterMapper);
        List<Map<String, Object>> excelRows = new ArrayList<>();

        for (FormRecords formRecords : formRecordsList) {
            Map<String, Object> rowMap = buildFormRecords(formRecords, exitHealthyCode, exitTravelCard);
            excelRows.add(rowMap);
        }
        return excelRows;
    }

    /*
     *每一行的数据
     * */
    public Map<String, Object> buildFormRecords(FormRecords formRecords, Integer exitHealthyCode, Integer exitTravelCard) {
        Map<String, Object> rowMap = new LinkedHashMap<>();

        if (formRecords.getLabelCombination() != null) {
            rowMap.putAll(formRecords.getLabelCombination());
        }
        if (formRecords.getLabelCombinationImg() != null) {

            rowMap.putAll(formRecords.getLabelCombinationImg());
        }
        /*Map<String, Object> labelCombinationImg = formRecords.getLabelCombinationImg();
        if (!labelCombinationImg.isEmpty()) {
            //处理一下图片url
            Map<String, Object> labelurlToImg = new LinkedHashMap<>();
            for (Map.Entry<String, Object> entry : labelCombinationImg.entrySet()) {
                labelurlToImg.put(entry.getKey(), new URL(StrUtil.toString(entry.getValue())));
            }
            rowMap.putAll(labelCombinationImg);
        }*/

        if (formRecords.getStatus().equals(FormRecordsEnum.UNDONE.getStatus())) {
            rowMap.put("状态" , FormRecordsEnum.UNDONE.getMsg());
        } else if (formRecords.getStatus().equals(FormRecordsEnum.DONE.getStatus())) {
            rowMap.put("状态" , FormRecordsEnum.DONE.getMsg());

        } else if (formRecords.getStatus().equals(FormRecordsEnum.ABNOMAL.getStatus())) {
            rowMap.put("状态" , FormRecordsEnum.ABNOMAL.getMsg());
        }

        rowMap.put("填写时间" , formRecords.getUpdateTime());
        if (exitHealthyCode.equals(1)) {
            rowMap.put("健康码图片" , formRecords.getHealthyCodeUrl());

            ImgHealthCodeFilterMapper imgHealthCodeFilterMapper = new ImgHealthCodeFilterMapper();
            imgHealthCodeFilterMapper.formRecordsId = formRecords.getId();
            ImgHealthCode imgHealthCode = imgHealthCodeExtendsService.getListByFilter(imgHealthCodeFilterMapper).stream().findFirst().orElse(new ImgHealthCode());
            //if (imgHealthCode == null) imgHealthCode = new ImgHealthCode();
            //Map<String, Object> imgHeadthCodeMap = ImgHealthCodeDetailMapper.buildMap(imgHealthCode);
            rowMap.put("健康码颜色" , imgHealthCode.getColor());
            rowMap.put("人员核酸类型" , imgHealthCode.getAcidType());
            rowMap.put("人员核酸时间" , imgHealthCode.getAcidTime());
            rowMap.put("疫苗针次" , imgHealthCode.getVaccinesCount());
            if (imgHealthCode.getStatus() == null) {
                rowMap.put("健康码是否出现异常" , "" );
            } else if (imgHealthCode.getStatus().equals(1)) {
                rowMap.put("健康码是否出现异常" , "否" );
            } else if (imgHealthCode.getStatus().equals(2)) {
                rowMap.put("健康码是否出现异常" , "是" );
            }

        }

        if (exitTravelCard.equals(1)) {
            rowMap.put("行程卡图片" , formRecords.getTravelCardUrl());
            ImgTravelCardFilterMapper imgTravelCardFilterMapper = new ImgTravelCardFilterMapper();
            imgTravelCardFilterMapper.formRecordsId = formRecords.getId();
            ImgTravelCard imgTravelCard = imgTravelCardExtendsService.getListByFilter(imgTravelCardFilterMapper).stream().findFirst().orElse(new ImgTravelCard());
            //if (imgTravelCard == null) imgTravelCard;
            rowMap.put("行程卡颜色" , imgTravelCard.getColor());
            rowMap.put("行程记录" , imgTravelCard.getTravelRecords());
            if (imgTravelCard.getIsStar() == null) {
                rowMap.put("是否带星" , "" );
                rowMap.put("风险地区" , "" );
            } else if (imgTravelCard.getIsStar().equals(2)) {
                rowMap.put("是否带星" , "是" );
                rowMap.put("风险地区" , imgTravelCard.getRiskArea());
            } else if (imgTravelCard.getIsStar().equals(1)) {
                rowMap.put("是否带星" , "否" );
                rowMap.put("风险地区" , "" );
            }

        }

        return rowMap;
    }

}
