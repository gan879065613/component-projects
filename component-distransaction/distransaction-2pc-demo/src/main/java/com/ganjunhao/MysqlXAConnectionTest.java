package com.ganjunhao;

import com.mysql.cj.jdbc.MysqlXAConnection;
import com.mysql.cj.jdbc.MysqlXid;

import javax.sql.XAConnection;
import javax.transaction.xa.XAException;
import javax.transaction.xa.XAResource;
import javax.transaction.xa.Xid;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author ganjunhao
 * @date 2023/3/29 15:46
 */
public class MysqlXAConnectionTest {
    public static void main(String[] args) throws SQLException {
        // true 表示打印 XA 语句, 用于调试
        boolean logXaCommands = true;
        // 获得资源管理器操作接口实例 RM1
        Connection conn1 = DriverManager.getConnection("jdbc:mysql://localhost:63306/component_projects?useSSL=false", "root", "haohao1995810");
        XAConnection xaConn1 = new MysqlXAConnection((com.mysql.cj.jdbc.JdbcConnection) conn1, logXaCommands);
        XAResource rm1 = xaConn1.getXAResource();
        // 获得资源管理器操作接口实例 RM2
        Connection conn2 = DriverManager.getConnection("jdbc:mysql://localhost:63306/component_projects?useSSL=false", "root", "haohao1995810");
        XAConnection xaConn2 = new MysqlXAConnection((com.mysql.cj.jdbc.JdbcConnection) conn2, logXaCommands);
        XAResource rm2 = xaConn2.getXAResource();
        // AP（应用程序）请求 TM（事务管理器） 执行一个分布式事务, TM 生成全局事务 ID
        byte[] gtrid = "distributed_transaction_id_1".getBytes();
        int formatId = 1;
        try {
            // ============== 分别执行 RM1 和 RM2 上的事务分支 ====================
            // TM 生成 RM1 上的事务分支 ID
            byte[] bqual1 = "transaction_001".getBytes();
            Xid xid1 = new MysqlXid(gtrid, bqual1, formatId);
            // 执行 RM1 上的事务分支
            rm1.start(xid1, XAResource.TMNOFLAGS);
            PreparedStatement ps1 = conn1.prepareStatement("INSERT into user(name) VALUES ('jack')");
            ps1.execute();
            rm1.end(xid1, XAResource.TMSUCCESS);
            // TM 生成 RM2 上的事务分支 ID
            byte[] bqual2 = "transaction_002".getBytes();
            Xid xid2 = new MysqlXid(gtrid, bqual2, formatId);
            // 执行 RM2 上的事务分支
            rm2.start(xid2, XAResource.TMNOFLAGS);
            PreparedStatement ps2 = conn2.prepareStatement("INSERT into user(name) VALUES ('rose')");
            ps2.execute();
            rm2.end(xid2, XAResource.TMSUCCESS);
            // =================== 两阶段提交 ================================
            // phase1: 询问所有的RM 准备提交事务分支
            int rm1_prepare = rm1.prepare(xid1);
            int rm2_prepare = rm2.prepare(xid2);
            // phase2: 提交所有事务分支
            if (rm1_prepare == XAResource.XA_OK && rm2_prepare == XAResource.XA_OK) {
                // 所有事务分支都 prepare 成功, 提交所有事务分支
                rm1.commit(xid1, false);
                rm2.commit(xid2, false);
            } else {
                // 如果有事务分支没有成功, 则回滚
                rm1.rollback(xid1);
                rm1.rollback(xid2);
            }
        } catch (XAException e) {
            e.printStackTrace();
        }
    }
}

