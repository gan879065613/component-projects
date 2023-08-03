package com.ganjunhao.controller;

import com.ganjunhao.service.PrintService;
import com.ganjunhao.utils.ItextPdfUtil;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.ExecutionException;

/**
 * @author ganjunhao
 * @date 2023/7/3 10:24
 */
@RequestMapping("/pdf")
@RestController
public class PrintController {

    @Resource
    private PrintService printService;

    @GetMapping
    public String getPdfFile(String[] ids) throws ExecutionException, InterruptedException {
        String combinePdf = printService.getCombinePdf(ids);
        return combinePdf;
    }

    @PostMapping("/image/to")
    public void imageToPdf(@RequestParam("file") MultipartFile file, HttpServletResponse response) throws Exception{
        ItextPdfUtil.imageToPdf(file,response);
    }
}
