package com.ganjunhao.pojo.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;


/**
 * Excel文件导入DTO
 */
@Data
public class ExcelImportDTO {

    private MultipartFile file;

}
