package com.ganjunhao.demo;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import com.alibaba.otter.canal.common.utils.AddressUtils;
import com.alibaba.otter.canal.protocol.CanalEntry.*;
import com.alibaba.otter.canal.protocol.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;
import java.util.List;

/**
 * Canal 客户端测试
 * @author ganjunhao
 * @date 2023/6/8 15:47
 */
@Slf4j
@Component
public class CanalClient implements InitializingBean {

    /**
     *
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println(AddressUtils.getHostIp());
        CanalConnector connector = CanalConnectors.newSingleConnector(new InetSocketAddress(AddressUtils.getHostIp(),
                11111), "example", "", "");
        int batchSize = 1000;
        int emptyCount = 0;
        try {
            //打开连接
            connector.connect();
            //订阅数据库表  , connector.subscribe(".*\\..*");全部表
            connector.subscribe("zgyf_boot_dev.t_asso_service_info");
            //回滚到未进行ack的地方，下次fetch的时候，可以从最后一个没有ack的地方开始拿
            connector.rollback();
            int totalEmptyCount = 120;
            while (emptyCount < totalEmptyCount) {
                // 获取指定数量的数据
                Message message = connector.getWithoutAck(batchSize);
                //获取批量ID
                long batchId = message.getId();
                int size = message.getEntries().size();
                //如果没有数据
                if (batchId == -1 || size == 0) {
                    emptyCount++;
                    System.out.println("empty count: " + emptyCount);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                    }
                } else {
                    //如果有数据,处理数据
                    emptyCount = 0;
                    printEntry(message.getEntries());
                }
                connector.ack(batchId);
            }
            System.out.println("empty too many times, exit");
        } finally {
            connector.disconnect();
        }
    }

    /**
     * 打印canal server解析binlog获得的实体类信息
     * @param entrys
     */
    private static void printEntry(List<Entry> entrys) {
        for (Entry entry : entrys) {
            if (entry.getEntryType() == EntryType.TRANSACTIONBEGIN || entry.getEntryType() == EntryType.TRANSACTIONEND) {
                //开启/关闭事务的实体类型，跳过
                continue;
            }
            //RowChange对象，包含了一行数据变化的所有特征
            //比如isDdl 是否是ddl变更操作 sql 具体的ddl sql beforeColumns afterColumns 变更前后的数据字段等等
            RowChange rowChange;
            try {
                rowChange = RowChange.parseFrom(entry.getStoreValue());
            } catch (Exception e) {
                throw new RuntimeException("ERROR ## parser of error change-event has an error, data: " + entry, e);
            }
            //获取操作类型：insert/update/delete类型
            EventType eventType = rowChange.getEventType();
            //打印Header信息
            System.out.println(String.format("================> binlog[%s:%s], name[%s,%s], eventType: %s",
                    entry.getHeader().getLogfileName(), entry.getHeader().getLogfileOffset(),
                    entry.getHeader().getSchemaName(), entry.getHeader().getTableName(),
                    eventType));
            //判断是否是DDL语句
            if (rowChange.getIsDdl()) {
                System.out.println("================》;isDdl: true,sql:" + rowChange.getSql());
            }
            //获取RowChange对象里的每一行数据，打印出来
            for (RowData rowData : rowChange.getRowDatasList()) {

                if (eventType == EventType.DELETE) {
                    //如果是删除语句
                    printColumn(rowData.getBeforeColumnsList());
                } else if (eventType == EventType.INSERT) {
                    //如果是新增语句
                    printColumn(rowData.getAfterColumnsList());
                } else {
                    //变更前的数据
                    System.out.println("-------> before");
                    printColumn(rowData.getBeforeColumnsList());
                    //变更后的数据
                    System.out.println("-------> after");
                    printColumn(rowData.getAfterColumnsList());
                }
            }
        }
    }

    private static void printColumn(List<Column> columns) {
        for (Column column : columns) {
            System.out.println(column.getName() + ": " + column.getValue() + "    update=" + column.getUpdated());
        }
    }
}
