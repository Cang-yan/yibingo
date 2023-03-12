package com.yibingo.race.excel;


import com.alibaba.fastjson.JSONArray;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:动态导出工具类
 * @author: Yang Xin
 * @time: 2022/5/31 15:39
 */
public class DynamicEasyExcelExportUtils {

   /* private  static final Logger log = LoggerFactory.getLogger(DynamicEasyExcelExportUtils.class);
    private static final String DEFAULT_SHEET_NAME = "sheet1";

    *//**
     * 动态⽣成导出模版(单表头)
     *
     * @param headColumns 列名称
     * @return excel⽂件流
     *//*
    public static byte[] exportTemplateExcelFile(List<String> headColumns) {
        List<List<String>> excelHead = Lists.newArrayList();
        headColumns.forEach(columnName -> {
            excelHead.add(Lists.newArrayList(columnName));
        });
        byte[] stream = createExcelFile(excelHead, new ArrayList<>());
        return stream;
    }

    *//**
     * 动态导出⽂件（通过map⽅式计算）
     *
     * @param headColumnMap 有序列头部
     * @param dataList      数据体
     * @return
     *//*
    public static byte[] exportExcelFile(LinkedHashMap<String, String> headColumnMap, List<Map<String, Object>> dataList) {
        //获取列名称
        List<List<String>> excelHead = new ArrayList<>();
        if (MapUtils.isNotEmpty(headColumnMap)) {
            //key为匹配符，value为列名，如果多级列名⽤逗号隔开
            headColumnMap.entrySet().forEach(entry -> {
                excelHead.add(Lists.newArrayList(entry.getValue().split("," )));
            });
        }
        List<List<Object>> excelRows = new ArrayList<>();
        if (MapUtils.isNotEmpty(headColumnMap) && CollectionUtils.isNotEmpty(dataList)) {
            for (Map<String, Object> dataMap : dataList) {
                List<Object> rows = new ArrayList<>();
                headColumnMap.entrySet().forEach(headColumnEntry -> {
                    if (dataMap.containsKey(headColumnEntry.getKey())) {
                        Object data = dataMap.get(headColumnEntry.getKey());
                        rows.add(data);
                    }
                });
                excelRows.add(rows);
            }
        }
        byte[] stream = createExcelFile(excelHead, excelRows);
        return stream;
    }

    *//**
     * ⽣成⽂件（⾃定义头部排列）
     *
     * @param rowHeads
     * @param excelRows
     * @return
     *//*
    public static byte[] customerExportExcelFile(List<List<String>> rowHeads, List<List<Object>> excelRows) {
        //将⾏头部转成easyexcel能识别的部分
        List<List<String>> excelHead = transferHead(rowHeads);
        return createExcelFile(excelHead, excelRows);
    }


    *//**
     * ⽣成⽂件
     *
     * @param excelHead
     * @param excelRows
     * @return
     *//*
    private static byte[] createExcelFile(List<List<String>> excelHead, List<List<Object>> excelRows) {
        try {
            if (CollectionUtils.isNotEmpty(excelHead)) {
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                EasyExcel.write(outputStream).registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
                        .head(excelHead)
                        .sheet(DEFAULT_SHEET_NAME)
                        .doWrite(excelRows);
                return outputStream.toByteArray();
            }
        } catch (Exception e) {
            log.error("动态⽣成excel⽂件失败，headColumns：" + JSONArray.toJSONString(excelHead) + "，excelRows：" +
                    JSONArray.toJSONString(excelRows), e);
        }
        return null;
    }



    *//**
     * 将⾏头部转成easyexcel能识别的部分
     * @param rowHeads
     * @return
     *//*
    public static List<List<String>> transferHead(List<List<String>> rowHeads){
        //将头部列进⾏反转
        List<List<String>> realHead = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(rowHeads)){
            Map<Integer, List<String>> cellMap = new LinkedHashMap<>();
            //遍历⾏
            for (List<String> cells : rowHeads) {
                //遍历列
                for (int i = 0; i < cells.size(); i++) {
                    if(cellMap.containsKey(i)){
                        cellMap.get(i).add(cells.get(i));
                    } else {
                        cellMap.put(i, Lists.newArrayList(cells.get(i)));
                    }
                }
            }
            //将列⼀⾏⼀⾏加⼊realHead
            cellMap.entrySet().forEach(item -> realHead.add(item.getValue()));
        }
        return realHead;
    }
*/

}