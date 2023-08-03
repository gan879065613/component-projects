package com.ganjunhao.pdfCombine;

import cn.hutool.core.util.StrUtil;
import com.ganjunhao.service.PdfService;

import java.util.concurrent.Callable;

/**
 * @author ganjunhao
 * @date 2023/7/3 10:08
 */
public class CombinePdfCallable implements Callable<String> {

    private PdfService pdfService;

    private String id;

    public CombinePdfCallable(PdfService pdfService, String id) {
        this.pdfService = pdfService;
        this.id = id;
    }

    @Override
    public String call() throws Exception {
        String file = "";
        String filePatch = pdfService.getSinglePdf(id);
        if(StrUtil.isNotEmpty(filePatch)){
            file = filePatch;
        }
        return file;
    }
}
