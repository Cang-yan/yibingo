package com.yibingo.race.web.controller;

import com.yibingo.race.common.utils.Result;
import com.yibingo.race.core.service.OCRService;
import com.yibingo.race.core.service.UploadFileService;
import com.yibingo.race.dal.entity.ImgHealthCode;
import com.yibingo.race.dal.entity.ImgTravelCard;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

/**
 * @author : smalljop
 * @description : 上传文件
 * @create : 2020-11-27 14:00
 **/
@Api(
        value = "upload" ,
        tags = "上传图片"
)
@RestController
@RequestMapping("upload" )
public class UploadFileController {

    @Autowired
    UploadFileService uploadFileService;
    @Autowired
    OCRService ocrService;

    /**
     * 上传用户文件
     * <p>
     * 用户Id MD5加密 同一个用户的文件放在一个目录下
     *
     * @param file
     * @param userId
     * @return
     * @throws IOException
     */
    //
    //@Login
    @ApiOperation(
            value = "存储图片" ,
            notes = "存储图片"
    )
    @RequestMapping(
            value = "/storage/file/" ,
            method = RequestMethod.POST
    )
    @ResponseBody
    public Map<String, Object> uploadUserFile(@RequestParam("file" ) MultipartFile file, @RequestParam String userId) throws IOException {

        return Result.success(
                uploadFileService.uploadUserFile(file, userId)
        ).map();
    }

    @ApiOperation(
            value = "上传健康码" ,
            notes = "上传健康码"
    )
    @RequestMapping(
            value = "/health/code" ,
            method = RequestMethod.POST
    )
    @ResponseBody
    public Map<String, Object> uploadHealthCode(@RequestParam("file" ) MultipartFile file,
                                                @RequestParam String userId,
                                                @RequestParam String formRecordsId,
                                                @RequestParam Integer acidRequirement

    ) throws IOException {

        return Result.success(
                uploadFileService.uploadHealthCode(file, userId, formRecordsId, acidRequirement)
        ).map();
    }

    @ApiOperation(
            value = "上传行程卡" ,
            notes = "上传行程卡"
    )
    @RequestMapping(
            value = "/travel/card" ,
            method = RequestMethod.POST
    )
    @ResponseBody
    public Map<String, Object> uploadTravelCard(@RequestParam("file" ) MultipartFile file,
                                                @RequestParam String userId,
                                                @RequestParam String formRecordsId

    ) throws IOException {

        return Result.success(
                uploadFileService.uploadTravelCard(file, userId, formRecordsId)
        ).map();
    }

    @ApiOperation(
            value = "上传测试" ,
            notes = "上传测试"
    )
    @RequestMapping(
            value = "/test" ,
            method = RequestMethod.GET
    )
    @ResponseBody
    public Map<String, Object> uploadtest(@RequestParam(required = false) String imgHealthCodeUrl,
                                          @RequestParam(required = false) String imgTravelCardUrl,
                                          @RequestParam(required = false) String formRecordsId,
                                          @RequestParam(required = false) String acidRequirement
    ) throws IOException {


        if (imgHealthCodeUrl == null) {
            ImgTravelCard imgTravelCard = new ImgTravelCard();
            imgTravelCard.setUrl(imgTravelCardUrl);
            imgTravelCard.setFormRecordsId(formRecordsId);

            Integer status2 = ocrService.ocr(imgTravelCardUrl, new ImgHealthCode(), imgTravelCard, 0);
        }
        if (imgTravelCardUrl == null) {
            ImgHealthCode imgHealthCode = new ImgHealthCode();
            imgHealthCode.setUrl(imgHealthCodeUrl);
            imgHealthCode.setFormRecordsId(formRecordsId);
            Integer status2 = ocrService.ocr(imgHealthCodeUrl, imgHealthCode, new ImgTravelCard(), 0);
        }
        return Result.success(

        ).map();
    }

    @ApiOperation(
            value = "图像识别测试" ,
            notes = "图像识别测试"
    )
    @RequestMapping(
            value = "/test/color" ,
            method = RequestMethod.GET
    )
    @ResponseBody
    public Map<String, Object> imgColor(String url) {
        ocrService.recognizeImageColor(url);
        return Result.success().map();
    }

}
