package com.yibingo.race.core.service;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.yibingo.race.common.utils.HttpUtils;
import com.yibingo.race.dal.entity.ImgHealthCode;
import com.yibingo.race.dal.entity.ImgTravelCard;
import com.yibingo.race.ocr.QcloudBasicGeneral;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @description:
 * @author: Yang Xin
 * @time: 2022/5/19 18:40
 */
@Service
@Slf4j
public class OCRService {

    /*
     * 1999-19-1808:19 正则表达式
     * */
    private final static String healthCodeAcidTime = "^([1-2][0-9][0-9][0-9]-[0-1]{0,1}[0-9]-[0-3]{0,1}[0-9])(20|21|22|23|[0-1]\\d):[0-5]\\d$";
    private final static String healthCodeTime = "^[0-9]{4}-[0-9]{2}-[0-9]{1,2}[0-9]{2}:[0-9]{2}:[0-9]{2}$";

    //  返回给前端是一个状态，上传完这个图片就调这个接口
    //传参之前，先把哪个图片的对象的records设置好再传，调完方法以后再保存一下
    public Integer ocr(String imgUrl, ImgHealthCode imgHealthCode, ImgTravelCard imgTravelCard, Integer acidTime) {
        LocalDateTime currentLocalDateTime = LocalDateTimeUtil.now();

        AtomicReference<Integer> status = new AtomicReference<>(1);
        try {
            //防止传反
            AtomicReference<Integer> countException = new AtomicReference<>(0);
            //行程卡
            JSONObject travelCardDetection = QcloudBasicGeneral.recognizeTravelCardOCR(imgUrl);

            String color = travelCardDetection.getString("Color" );
            if (!(color.equals("绿色" )||color.equals("黄色"))) {
                status.set(2);
            }
            //行程卡的识别
            if (imgTravelCard.getFormRecordsId() != null) {
                String detectionTime = travelCardDetection.getString("Time" );
                String time = detectionTime.substring(0, 4) + "-" + detectionTime.substring(5, 7) + "-" + detectionTime.substring(8);
                LocalDateTime travelcardTime = LocalDateTimeUtil.parse(time, DatePattern.NORM_DATETIME_PATTERN);
                //上交的行程卡大于1天
                long duration = LocalDateTimeUtil.between(travelcardTime, currentLocalDateTime, ChronoUnit.DAYS);

                if (duration > 1) {
                    status.set(2);
                }
                String reachedCityDemo = travelCardDetection.getString("ReachedCity" );
                String reachedCity = " ";
                //去除首尾中括号
                if (reachedCityDemo.length() > 2)
                    reachedCity = reachedCityDemo.substring(2, reachedCityDemo.length() - 2);
                //传反了 那么就有异常
                if (StrUtil.isBlank(reachedCity)) status.set(2);

                String riskAreaDemo = travelCardDetection.getString("RiskArea" );
                imgTravelCard.setColor(color);
                imgTravelCard.setTravelRecords(reachedCity);
                imgTravelCard.setRiskArea(" " );
                //即使没有风险地区是空的话  这里的值为“[]”
                if (riskAreaDemo.length() > 2) {
                    String riskArea = riskAreaDemo.substring(2, reachedCityDemo.length() - 2);
                    imgTravelCard.setRiskArea(riskArea);
                    imgTravelCard.setIsStar(2);
                    status.set(2);
                    return status.get();
                }
            }
            if (imgHealthCode.getFormRecordsId() != null) {
                imgHealthCode.setColor(color);
                //通用ocr
                JSONObject jsonObject = QcloudBasicGeneral.basicGeneralOcr(imgUrl);
                JSONArray textArray = jsonObject.getJSONArray("TextDetections" );


                textArray.forEach((j) -> {
                    String detectedText = ((JSONObject) j).getString("DetectedText" );
                    //上交健康码时间不能超过一天
                    if (detectedText.matches(healthCodeTime)) {
                        //
                        String time = detectedText.substring(0, 10) + " " + detectedText.substring(10);
                        log.debug(time);
                        long timeBetween = LocalDateTimeUtil.between(LocalDateTimeUtil.parse(time, DatePattern.NORM_DATETIME_PATTERN)
                                , currentLocalDateTime, ChronoUnit.DAYS);
                        if (timeBetween > 1) {
                            status.set(2);
                        }
                    }
                    //5天会传给我5*24小时
                    //48或者72小时阴性
                    if (detectedText.contains("小时阴性" )) {
                        imgHealthCode.setAcidType(detectedText);
                        Integer realAcidTime = Integer.parseInt(detectedText.substring(0, 2));
                        if (realAcidTime > acidTime) status.set(2);
                        countException.getAndSet(countException.get() + 1);
                    } else if (detectedText.contains("天阴性" )) {
                        imgHealthCode.setAcidType(detectedText);
                        Integer realAcidTime = Integer.parseInt(detectedText.substring(0, 1));
                        if (realAcidTime * 24 > acidTime) status.set(2);
                        countException.getAndSet(countException.get() + 1);
                    }


                    if (detectedText.contains("次" )) {
                        imgHealthCode.setVaccinesCount(detectedText);
                        countException.getAndSet(countException.get() + 1);

                    }
                    if (detectedText.matches(healthCodeAcidTime)) {
                        imgHealthCode.setAcidTime(detectedText);
                        countException.getAndSet(countException.get() + 1);

                    }
                });
                if (countException.get().equals(0)) {
                    status.set(2);
                }
            }


        } catch (TencentCloudSDKException e) {
            System.out.println(e);
            status.set(2);
        }


        return status.get();
    }

    public Integer recognizeImageColor(String imgUrl) {


        String url = "https://imagerecog.cn-shanghai.aliyuncs.com/?Action=RecognizeImageColor" +
                "&ColorCount=5" +
                "&Url=" + imgUrl;
        try {
            JSONObject jsonObject = HttpUtils.doGetJson(url, "Get" );
            jsonObject.getString("color" );
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        return 1;
    }


}
