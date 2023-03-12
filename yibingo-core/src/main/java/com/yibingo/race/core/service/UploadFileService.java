package com.yibingo.race.core.service;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.CharUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import com.yibingo.race.dal.entity.ImgHealthCode;
import com.yibingo.race.dal.entity.ImgTravelCard;
import com.yibingo.race.dal.filterMapper.ImgHealthCodeFilterMapper;
import com.yibingo.race.dal.filterMapper.ImgTravelCardFilterMapper;
import com.yibingo.race.storage.cloud.OssStorageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: Yang Xin
 * @time: 2022/5/20 9:54
 */
@Service
public class UploadFileService {
    @Autowired
    OCRService ocrService;
    @Autowired
    ImgHealthCodeService imgHealthCodeService;
    @Autowired
    ImgHealthCodeExtendsService imgHealthCodeExtendsService;
    @Autowired
    ImgTravelCardService imgTravelCardService;
    @Autowired
    ImgTravelCardExtendsService imgTravelCardExtendsService;

    public String uploadUserFile(MultipartFile file, String userId) throws IOException {
        String path = new StringBuffer(SecureUtil.md5(String.valueOf(userId)))
                .append(CharUtil.SLASH)
                .append(IdUtil.simpleUUID())
                .append(CharUtil.DOT)
                .append(FileUtil.extName(file.getOriginalFilename())).toString();
        String url = OssStorageFactory.build().upload(file, path);
        return url;
    }

    public Map<String, Object> uploadHealthCode(MultipartFile file, String userId, String formRecordId, Integer acidRequirement) throws IOException {
        Map<String, Object> endMap = new HashMap<>();

        String url = uploadUserFile(file, userId);
        ImgHealthCodeFilterMapper imgHealthCodeFilterMapper = new ImgHealthCodeFilterMapper();
        imgHealthCodeFilterMapper.formRecordsId = formRecordId;
        ImgHealthCode imgHealthCode = imgHealthCodeExtendsService.getListByFilter(imgHealthCodeFilterMapper).stream().findFirst().orElse(new ImgHealthCode());

        imgHealthCode.setUrl(url);
        imgHealthCode.setFormRecordsId(formRecordId);

        if (acidRequirement == null) acidRequirement = 999;
        Integer status = ocrService.ocr(url, imgHealthCode, new ImgTravelCard(), acidRequirement);
        imgHealthCode.setStatus(status);
        
        imgHealthCodeService.saveOrUpdate(imgHealthCode);
        
        endMap.put("status", status);
        endMap.put("url", url);
        return endMap;
    }

    public Map<String, Object> uploadTravelCard(MultipartFile file, String userId, String formRecordId) throws IOException {
        Map<String, Object> endMap = new HashMap<>();
        String url = uploadUserFile(file, userId);

        ImgTravelCardFilterMapper imgTravelCardFilterMapper = new ImgTravelCardFilterMapper();
        imgTravelCardFilterMapper.formRecordsId = formRecordId;
        ImgTravelCard imgTravelCard = imgTravelCardExtendsService.getListByFilter(imgTravelCardFilterMapper).stream().findFirst().orElse(new ImgTravelCard());

        imgTravelCard.setUrl(url);
        imgTravelCard.setFormRecordsId(formRecordId);
        Integer status = ocrService.ocr(url, new ImgHealthCode(), imgTravelCard, 0);
        imgTravelCard.setIsStar(status);
        imgTravelCardService.saveOrUpdate(imgTravelCard);
        endMap.put("status", status);
        endMap.put("url", url);
        return endMap;
    }


}
