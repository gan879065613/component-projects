package com.ganjunhao.utils;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.io.resource.ResourceUtil;
import com.alibaba.fastjson.JSONObject;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ganjunhao
 * @date 2023/7/3 10:17
 */
@Slf4j
public class ItextPdfUtil {

    /**
     * 学时证明模板路径
     */
    public static final URL TEMPLATE_URL = ResourceUtil.getResource("template/template.pdf");


    public static void main(String[] args) throws IOException, DocumentException {
        Map<String, Object> data = new HashMap<>();//要插入的数据
        data.put("name", "沙发架");
        data.put("code", "12312");
        //初始化itext
        //设置编码
        BaseFont baseFont = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        PdfReader pdfReader = new PdfReader(TEMPLATE_URL);
        PdfStamper pdfStamper = new PdfStamper(pdfReader, new FileOutputStream("F:\\TempDownload\\demo\\result.pdf"));
        AcroFields form = pdfStamper.getAcroFields();
        form.addSubstitutionFont(baseFont);
        //写入数据
        for (String key : data.keySet()) {
            String value = data.get(key).toString();
            //key对应模板数据域的名称
            form.setField(key, value);
        }
        //添加图片
        int pageNo = form.getFieldPositions("img").get(0).page;
        Rectangle signRect = form.getFieldPositions("img").get(0).position;
        float x = signRect.getLeft();
        float y = signRect.getBottom();
        File file = new File("F:\\TempDownload\\demo\\qs1000002(1).png");
        byte[] bytes = FileUtil.readBytes(file);
        Image image = Image.getInstance(bytes);
        PdfContentByte under = pdfStamper.getOverContent(pageNo);
        //设置图片大小
        image.scaleAbsolute(signRect.getWidth(), signRect.getHeight());
        //设置图片位置
        image.setAbsolutePosition(x, y);
        under.addImage(image);

        //设置不可编辑
        pdfStamper.setFormFlattening(true);
        pdfStamper.close();
    }

    /**
     * 导出PDF方法
     *
     * @param savePath 保存服务器路径
     * @param obj      导出的参数
     */
    public static void exportTemplateByPdf(String savePath, JSONObject obj, String filePath) {
        PdfReader reader = null;
        ByteArrayOutputStream bos = null;
        PdfStamper stamper = null;
        OutputStream os = null;
        FileOutputStream out = null;
        File file;
        Document document = null;
        PdfCopy copy = null;
        Document doc = null;
        try {
            /** 实例化文档对象 */
            document = new Document(PageSize.A4, 50, 40, 40, 50);
            /** 创建 PdfWriter 对象 */
            // 打开文档
            document.open();
            /** pdf文档中中文字体的设置，注意一定要添加iTextAsian.jar包 */
            String localFontPath = "c:\\windows\\fonts\\";
            BaseFont bfChinese = BaseFont.createFont(localFontPath + "simhei.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            FileUtil.del(savePath);
            file = new File(savePath);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            out = new FileOutputStream(file);
            reader = new PdfReader(TEMPLATE_URL);
            bos = new ByteArrayOutputStream();
            stamper = new PdfStamper(reader, bos);
            AcroFields form = stamper.getAcroFields();
            // 文字类的内容处理
            form.addSubstitutionFont(bfChinese);
            String vlaues;
            for (Map.Entry<String, Object> entry : obj.entrySet()) {
                vlaues = String.valueOf(entry.getValue());
                if ("photo".equals(entry.getKey()) || "qrcode".equals(entry.getKey())) {
                    try {
                        // 通过域名获取所在页和坐标，左下角为起点
                        int pageNo = form.getFieldPositions(entry.getKey()).get(0).page;
                        Rectangle signRect = form.getFieldPositions(entry.getKey()).get(0).position;
                        // 印章位置
                        Rectangle seal_signRect = form.getFieldPositions("month").get(0).position;
                        float x = signRect.getLeft();
                        float y = signRect.getBottom();
                        // 印章坐标位置
                        float seal_x = seal_signRect.getLeft();
                        float seal_y = seal_signRect.getBottom();
                        // 读图片
                        Image image = Image.getInstance(vlaues);
                        Image seal_image = Image.getInstance(filePath);
                        // 获取操作的页面
                        PdfContentByte under = stamper.getOverContent(pageNo);
                        // 根据域的大小缩放图片
                        image.scaleToFit(signRect.getWidth(), signRect.getHeight());
                        seal_image.scaleToFit(signRect.getWidth(), signRect.getHeight());
                        // 添加图片
                        image.setAbsolutePosition(x, y);
                        seal_image.setAbsolutePosition(seal_x, seal_y);
                        under.addImage(image);
                        under.addImage(seal_image);
                    } catch (Exception e) {
                        log.info(e.getMessage());
                    }

                } else {
                    if ("fileno".equals(entry.getKey())) {
                        form.setFieldProperty(entry.getKey(), "textsize", 50f, null);
                    } else {
                        form.setFieldProperty(entry.getKey(), "textsize", 10f, null);
                    }
                    form.setField(entry.getKey(), vlaues);
                }
            }
            // 如果为false，生成的PDF文件可以编辑，如果为true，生成的PDF文件不可以编辑
            stamper.setFormFlattening(true);
            stamper.close();
            doc = new Document();
            copy = new PdfCopy(doc, out);
            doc.open();
            PdfImportedPage importPage = copy.getImportedPage(new PdfReader(bos.toByteArray()), 1);
            copy.addPage(importPage);
            doc.close();
            document.close();
            copy.flush();
            copy.close();

        } catch (Exception e) {
            log.info(e.getMessage());
        } finally {
            try {

                if (stamper != null) {
                    stamper.close();
                    stamper = null;
                }
                if (reader != null) {
                    reader.close();
                    reader = null;
                }
                IoUtil.close(os);
                IoUtil.close(bos);
                IoUtil.close(out);
                if (document != null) {
                    document.close();
                    document = null;
                }
                if (doc != null) {
                    doc.close();
                    doc = null;
                }
                if (copy != null) {
                    copy.flush();
                    copy.close();
                    copy = null;
                }
            } catch (Exception ignored) {
            }
        }
    }

    /**
     * 合并pdf
     *
     * @param files
     * @param newfile
     * @return
     */
    public static boolean mergePdfFiles(String[] files, String newfile) {
        boolean retValue = false;
        Document document = null;
        try {
            File file = new File(newfile);
            if (!file.exists()) {
                file.createNewFile();
            }
            document = new Document(new PdfReader(files[0]).getPageSize(1));
            PdfCopy copy = new PdfCopy(document, new FileOutputStream(file.getPath()));
            document.open();
            for (int i = 0; i < files.length; i++) {
                PdfReader reader = new PdfReader(files[i]);
                int n = reader.getNumberOfPages();
                for (int j = 1; j <= n; j++) {
                    document.newPage();
                    PdfImportedPage page = copy.getImportedPage(reader, j);
                    copy.addPage(page);
                }
            }
            retValue = true;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            System.out.println("执行结束");
            document.close();
        }
        return retValue;
    }


    /**
     * 图片转换PDF的公共接口
     *
     * @param file     SpringMVC获取的图片文件
     * @param response HttpServletResponse
     * @throws IOException       IO异常
     * @throws DocumentException PDF文档异常
     */
    public static void imageToPdf(MultipartFile file, HttpServletResponse response) throws IOException, DocumentException {
        File pdfFile = generatePdfFile(file);
        downloadPdfFile(pdfFile, response);
    }

    /**
     * 将图片转换为PDF文件
     *
     * @param file SpringMVC获取的图片文件
     * @return PDF文件
     * @throws IOException       IO异常
     * @throws DocumentException PDF文档异常
     */
    private static File generatePdfFile(MultipartFile file) throws IOException, DocumentException {
        String fileName = file.getOriginalFilename();
        String pdfFileName = fileName.substring(0, fileName.lastIndexOf(".")) + ".pdf";
        Document doc = new Document(PageSize.A10, 20, 20, 20, 20);
        PdfWriter.getInstance(doc, new FileOutputStream(pdfFileName));
        doc.open();
        doc.newPage();
        Image image = Image.getInstance(file.getBytes());
        float height = image.getHeight();
        float width = image.getWidth();
        int percent = getPercent(height, width);
        image.setAlignment(Image.MIDDLE);
        image.scalePercent(percent);
        doc.add(image);
        doc.close();
        File pdfFile = new File(pdfFileName);
        return pdfFile;
    }

    /**
     * 用于下载PDF文件
     *
     * @param pdfFile  PDF文件
     * @param response HttpServletResponse
     * @throws IOException IO异常
     */
    private static void downloadPdfFile(File pdfFile, HttpServletResponse response) throws IOException {
        FileInputStream fis = new FileInputStream(pdfFile);
        byte[] bytes = new byte[fis.available()];
        fis.read(bytes);
        fis.close();

        response.reset();
        response.setHeader("Content-Type", "application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(pdfFile.getName(), "UTF-8"));
        OutputStream out = response.getOutputStream();
        out.write(bytes);
        out.flush();
        out.close();
    }


    /**
     * 等比压缩，获取压缩百分比
     *
     * @param height 图片的高度
     * @param weight 图片的宽度
     * @return 压缩百分比
     */
    private static int getPercent(float height, float weight) {
        float percent = 0.0F;
        if (height > weight) {
            percent = PageSize.A10.getHeight() / height * 100;
        } else {
            percent = PageSize.A10.getWidth() / weight * 100;
        }
        return Math.round(percent);
    }
}
