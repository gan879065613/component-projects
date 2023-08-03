package com.ganjunhao.utils;

import cn.hutool.core.io.FileUtil;
import com.itextpdf.text.Image;
import org.dromara.pdf.pdfbox.component.XEasyPdfComponent;
import org.dromara.pdf.pdfbox.component.image.XEasyPdfImageType;
import org.dromara.pdf.pdfbox.doc.XEasyPdfDefaultFontStyle;
import org.dromara.pdf.pdfbox.doc.XEasyPdfPageRectangle;
import org.dromara.pdf.pdfbox.handler.XEasyPdfHandler;
import org.dromara.pdf.pdfbox.util.XEasyPdfImageUtil;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.*;

/**
 * @author ganjunhao
 * @date 2023/7/7 11:07
 */
public class EasyPdfUtil {

    private static final String OUTPUT_PATH = "F:\\TempDownload\\pdf\\test\\doc\\";

    public static void main(String[] args) throws IOException {
        testFill();
    }

    public static void testCreate(){
        String filePath = OUTPUT_PATH + "testCreate.pdf";
        XEasyPdfHandler.Document
                // 创建文档
                .build()
                // 添加页面
                .addPage(
                        // 创建空白页
                        XEasyPdfHandler.Page.build()
                )
                // 获取表单填写器
                .formFiller()
                // 创建表单
                .create()
                // 创建第一个文本属性
                .createTextField()
                // 设置映射名称
                .setMappingName("property1")
                // 设置位置坐标
                .setPosition(50F,700F)
                // 开启打印
                .enablePrint()
                // 完成文本属性创建
                .finish()
                // 创建第二个文本属性
                .createTextField()
                // 设置映射名称
                .setMappingName("property2")
                // 设置位置坐标
                .setPosition(200F,700F)
                // 设置默认值
                .setDefaultValue("test")
                // 设置最大字符数
                .setMaxLength(11)
                // 完成文本属性创建
                .finish()
                // 完成表单操作
                .finish()
                // 完成填写器操作
                .finish(filePath);
    }

    public static void testFill(){
        long begin = System.currentTimeMillis();
        String sourcePath = OUTPUT_PATH + "testCreate.pdf";
        String filePath = OUTPUT_PATH + "testFill.pdf";
        Map<String, Object> map = new HashMap<>(5);
        map.put("title", "贵阳市");
        map.put("Text1", "贵州省南明区花果园");
        map.put("Text2", "贵阳2");
        map.put("Text3", "可以的 2022-04-06");
        map.put("Text4", "2022-04-06 10:00:00");
        map.put("img", "2022-04-06 10:00:00");
        XEasyPdfHandler.Document
                .load(sourcePath)
                .setDefaultFontStyle(XEasyPdfDefaultFontStyle.NORMAL)
                .formFiller()
                .enableReadOnly()
                .enableAppearance()
//\                .enableCompress()
                .finish(filePath);
        long end = System.currentTimeMillis();
        System.out.printf("finish: %sms", (end-begin));
    }

    public static void testReplace(){
        String sourcePath = OUTPUT_PATH+"testCreate2.pdf";
        String filePath = OUTPUT_PATH+"testReplace.pdf";

        Map<String, String> map = new HashMap<>(5);
        map.put("title", "贵阳市");
        map.put("Text1", "贵州省南明区花果园");
        map.put("Text2", "贵阳2");
        map.put("Text3", "可以的 2022-04-06");
        map.put("Text4", "2022-04-06 10:00:00");
        XEasyPdfHandler.Document
                .load(sourcePath)
                .replacer()
                .setDefaultFontStyle(XEasyPdfDefaultFontStyle.NORMAL)
                // .enableReplaceCOSArray()
                .replaceText(map)
                .replaceImage(XEasyPdfImageUtil.read(new File("F:\\TempDownload\\demo\\qs1000002.png")), 0)
                .finish(filePath);
    }

    public static void test08Merge() throws IOException {
        String sourcePath = OUTPUT_PATH + "merge1.pdf";
        String mergePath1 = OUTPUT_PATH + "merge2.pdf";
        String mergePath2 = OUTPUT_PATH + "merge3.pdf";
        String filePath = OUTPUT_PATH + "mergeResult.pdf";
        XEasyPdfHandler.Document.load(sourcePath).merge(
                XEasyPdfHandler.Document.load(mergePath1),
                XEasyPdfHandler.Document.load(mergePath2)
        ).save(filePath).close();
        System.out.println("finish");
    }

    public static void testImage5() throws IOException {
        String filePath = OUTPUT_PATH + "testImage5.pdf";
        InputStream inputStream = new FileInputStream(OUTPUT_PATH + "qs1000002.png");
        BufferedImage image = XEasyPdfImageUtil.read(inputStream);
        inputStream.close();
        List<XEasyPdfComponent> list = new ArrayList<>();
        inputStream = XEasyPdfImageUtil.toInputStream(image, XEasyPdfImageType.PNG.name());
        list.add(
                XEasyPdfHandler.Image.build(inputStream, XEasyPdfImageType.PNG).setWidth(100).setHeight(100)
        );
        float marginLeft = 100;
        for (int i = 0; i < 6; i++) {
            inputStream = XEasyPdfImageUtil.toInputStream(image, XEasyPdfImageType.PNG.name());
            list.add(
                    XEasyPdfHandler.Image.build(inputStream, XEasyPdfImageType.PNG)
                            .setWidth(100)
                            .setHeight(100)
                            .setMarginTop(-100)
                            .setMarginLeft(marginLeft)
                            .disableSelfAdaption()
            );
            marginLeft += 100;
            inputStream.close();
        }
        XEasyPdfHandler.Document.build().addPage(
                XEasyPdfHandler.Page.build(XEasyPdfPageRectangle.A4, list)
        ).save(filePath).close();
        System.out.println("finish");
    }
}
