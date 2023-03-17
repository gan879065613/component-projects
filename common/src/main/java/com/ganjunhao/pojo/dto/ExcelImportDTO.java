package com.ganjunhao.pojo.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

/**
 * Excel文件导入DTO
 */
@Data
public class ExcelImportDTO {

    @NotNull(message = "导入EXCEL不能为空")
    private MultipartFile file;

}
