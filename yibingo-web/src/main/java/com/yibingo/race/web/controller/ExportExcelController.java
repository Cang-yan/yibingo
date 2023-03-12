package com.yibingo.race.web.controller;

import cn.hutool.http.server.HttpServerResponse;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.yibingo.race.common.exception.BaseException;
import com.yibingo.race.core.service.ExportExcelService;
import com.yibingo.race.core.service.FormService;
import com.yibingo.race.dal.entity.Form;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: Yang Xin
 * @time: 2022/5/31 19:57
 */
@Api(
        value = "export_excel",
        tags = "导出excel"
)
@RestController
@RequestMapping("core/export/excel")
public class ExportExcelController {
    @Autowired
    private ExportExcelService exportExcelService;
    @Autowired
    private FormService formService;

    @ApiOperation(
            value = "表格数据导出",
            notes = "表格数据导出"
    )
    @RequestMapping(
            value = "/try",
            method = RequestMethod.GET
    )
    public ResponseEntity<ByteArrayOutputStream> exportExcel(String formId) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Form form = formService.getById(formId);
        if (form == null) throw new BaseException("您没有表格数据");
        List<Map<String, Object>> excelRows = exportExcelService.hutoolExcel(form);
        Map<String, Object> row1 = excelRows.stream().findFirst().orElse(null);
        if (excelRows.isEmpty() || row1 == null) throw new BaseException("数据表为空");
        ExcelWriter writer = ExcelUtil.getBigWriter();

        String acidRequirement = form.getAcidRequirement().toString();
        if (form.getAcidRequirement()<24){
            acidRequirement  = acidRequirement+"天核酸要求";
        }else {
            acidRequirement = acidRequirement + "小时核酸要求";
        }

        writer.merge(row1.size() - 1, form.getTitle());
        writer.merge(row1.size() - 1, form.getNotes() + "  " + form.getBeginTime() + "-" + form.getEndTime() +"  "+ acidRequirement);
        writer.write(excelRows, true);
        writer.flush(byteArrayOutputStream);
        writer.close();
        byteArrayOutputStream.close();
        //return byteArrayOutputStream.toByteArray();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Content-Disposition", "attachment; filename=" + form.getTitle() + File.separator+"xlsx");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        headers.add("Last-Modified", new Date().toString());
        headers.add("ETag", String.valueOf(System.currentTimeMillis()));

        return ResponseEntity.ok().headers(headers).contentLength(byteArrayOutputStream.size()).contentType(MediaType.parseMediaType("application/msexcel")).body(byteArrayOutputStream);
    }



    @ApiOperation(
            value = "表格数据导出",
            notes = "表格数据导出"
    )
    @RequestMapping(
            value = "/try/try",
            method = RequestMethod.GET
    )
    public void exportExcel(HttpServletRequest request, HttpServletResponse response) throws IOException {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Form form = formService.getById(request.getParameter("formId"));
        if (form == null) throw new BaseException("您没有表格数据");
        List<Map<String, Object>> excelRows = exportExcelService.hutoolExcel(form);
        Map<String, Object> row1 = excelRows.stream().findFirst().orElse(null);
        if (excelRows.isEmpty() || row1 == null) throw new BaseException("数据表为空");

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Content-Disposition", "attachment; filename=export.xlsx");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");
        response.setHeader("Last-Modified", new Date().toString());
        response.setHeader("ETag", String.valueOf(System.currentTimeMillis()));
        OutputStream outputStream = response.getOutputStream();
        ExcelWriter writer = ExcelUtil.getBigWriter();

        writer.merge(row1.size() - 1, form.getTitle());
        writer.merge(row1.size() - 1, form.getNotes() + "  " + form.getBeginTime() + "-" + form.getEndTime());
        writer.write(excelRows, true);
        //writer.flush(byteArrayOutputStream);
        writer.flush(outputStream);
        writer.close();
        outputStream.close();
//        byteArrayOutputStream.flush();
//        byteArrayOutputStream.close();
        //return byteArrayOutputStream.toByteArray();


        //return ResponseEntity.ok().headers(headers).contentLength(byteArrayOutputStream.size()).contentType(MediaType.parseMediaType("application/msexcel")).body(byteArrayOutputStream);
    }
}


