package com.ganjunhao.service;

import cn.hutool.core.util.RandomUtil;
import com.ganjunhao.pdfCombine.CombinePdfCallable;
import com.ganjunhao.utils.ItextPdfUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author ganjunhao
 * @date 2023/7/3 10:24
 */
@Service
public class PrintService {

    @Resource
    private PdfService pdfService;

    public String getCombinePdf(String[] split) throws ExecutionException, InterruptedException {
        long startTime = System.currentTimeMillis();    //获取开始时间
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2, 10, 1L,
                TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(3), Executors.defaultThreadFactory(), new ThreadPoolExecutor.DiscardOldestPolicy());//定义线程池
        List<Future<String>> arrayList = new ArrayList<>(); //用于接受线程执行的返回值
        //生成pdf 签章：
        for (int i = 0; i < split.length; i++) {
            arrayList.add(threadPool.submit(new CombinePdfCallable(pdfService, split[i]))); //这里去执行生成pdf 签章的方法
        }
        CopyOnWriteArrayList<String> copyOnWriteArrayList = new CopyOnWriteArrayList(); //使用并发list去接受值，这里我怕值会乱所以用了并发的list
        String contractId = RandomUtil.randomUUID();//生成新的pdf文件的名字
        for (int i = 0; i < arrayList.size(); i++) {
            if (StringUtils.isNotEmpty(arrayList.get(i).get())) {
                copyOnWriteArrayList.add("F:\\TempDownload\\demo\\" + arrayList.get(i).get());//获取放回值
            }
        }
        String[] objects = copyOnWriteArrayList.toArray(new String[copyOnWriteArrayList.size()]); //转换成数组
        String filePatch = "new" + contractId + ".pdf";
        ItextPdfUtil.mergePdfFiles(objects, "F:\\TempDownload\\demo\\" + filePatch); //多个pdf写入到一个新的pdf文件中
        long endTime = System.currentTimeMillis();    //获取结束时间
        System.err.println("程序运行时间：" + (endTime - startTime) + "ms");    //输出程序运行时间 计算程序运行的时间
        return filePatch;
    }

}
