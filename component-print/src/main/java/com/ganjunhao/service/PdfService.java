package com.ganjunhao.service;

import org.springframework.stereotype.Service;

/**
 * @author ganjunhao
 * @date 2023/7/3 10:09
 */
@Service
public class PdfService {
    public String getSinglePdf(String id) {
        if ("333".equals(id)) {
            return id + ".jpg";
        } else {
            return id + ".pdf";
        }

    }
}
