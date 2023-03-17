package com.ganjunhao.service;

import com.ganjunhao.excel.excelData.DemoData;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ganjunhao
 * @date 2023/2/24 17:48
 */
@Service
public class DemoService {

    public void save(List<DemoData> list) {
        // 如果是mybatis,尽量别直接调用多次insert,自己写一个mapper里面新增一个方法batchInsert,所有数据一次性插入
    }
}
