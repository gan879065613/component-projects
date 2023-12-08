package com.ganjunhao.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipOutputStream;

/**
 * @author ganjunhao
 * @date 2023/11/17 11:19
 */
public class GeneratorCodeTest {
    public static void main(String[] args) throws IOException {
        Map<String,Object> data = new HashMap<String,Object>();
        data.put("className","Product");
        data.put("classname","product");
        data.put("package","com.ganjunhao");

        File file = new File("F:\\code.zip");
        FileOutputStream outputStream = new FileOutputStream(file);
        ZipOutputStream zip = new ZipOutputStream(outputStream);

        GenUtil.generatorCode(data,GenUtil.getTemplates(),zip);

        zip.close();
    }
}
