package com.ganjunhao.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.ReadListener;
import com.ganjunhao.excel.excelData.IndexOrNameData;
import com.ganjunhao.excel.listener.IndexOrNameDataListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author ganjunhao
 * @date 2023/2/23 16:23
 */
@Slf4j
public class ImportExcelApi {

    /**
     * 指定列的下标或者列名
     *
     * <p>
     * 1. 创建excel对应的实体对象,并使用{ExcelProperty}注解. 参照{@link IndexOrNameData}
     * <p>
     * 2. 由于默认一行行的读取excel，所以需要创建excel一行一行的回调监听器，参照{@link IndexOrNameDataListener}
     * <p>
     * 3. 直接读即可
     */
    public static void indexOrNameRead(MultipartFile multipartFile, Class<?> clazz, ReadListener<?> readListener) {
        InputStream inputStream = null;
        try {
            inputStream = multipartFile.getInputStream();
            // 这里默认读取第一个sheet
            EasyExcel.read(inputStream, clazz, readListener).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
