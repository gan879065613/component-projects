package com.ganjunhao.controller;

import com.ganjunhao.annotation.ValidateExcel;
import com.ganjunhao.constant.R;
import com.ganjunhao.dao.DemoDAO;
import com.ganjunhao.excel.ImportExcelApi;
import com.ganjunhao.excel.excelData.IndexOrNameData;
import com.ganjunhao.excel.listener.IndexOrNameDataListener;
import com.ganjunhao.pojo.dto.ExcelImportDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * author ganjunhao
 * date 2023/2/23 16:22
 */

@RestController
@RequestMapping("/excelImport")
public class ImportController {

    @Resource
    private DemoDAO demoDAO;

    @PostMapping("/importData")
    @ValidateExcel
    public R<Void> importData(ExcelImportDTO excelImportDTO) {
        ImportExcelApi.indexOrNameRead(excelImportDTO.getFile(), IndexOrNameData.class, new IndexOrNameDataListener(demoDAO));
        return R.ok();
    }
}
