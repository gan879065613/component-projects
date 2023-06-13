package com.ganjunhao.simpleDemo;

import org.apache.shardingsphere.driver.api.ShardingSphereDataSourceFactory;
import org.apache.shardingsphere.infra.config.algorithm.ShardingSphereAlgorithmConfiguration;
import org.apache.shardingsphere.sharding.api.config.ShardingRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.rule.ShardingTableRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.strategy.keygen.KeyGenerateStrategyConfiguration;
import org.apache.shardingsphere.sharding.api.config.strategy.sharding.StandardShardingStrategyConfiguration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author ganjunhao
 * @date 2023/6/12 17:50
 */
public class ShardingDatabasesAndTableConfiguration {

    //创建两个数据源
    private static Map<String, DataSource> createDataSourceMap() {
        Map<String, DataSource> dataSourceMap = new HashMap<>();
        dataSourceMap.put("ds0", DataSourceUtil.createDataSource("shard01"));
        dataSourceMap.put("ds1", DataSourceUtil.createDataSource("shard02"));
        return dataSourceMap;
    }

    private static ShardingRuleConfiguration createShardingRuleConfiguration() {
        ShardingRuleConfiguration configuration = new ShardingRuleConfiguration();
        configuration.getTables().add(getOrderTableRuleConfiguration());
//        configuration.getBindingTableGroups().add("t_order,t_order_item");
        //
        //
        /**
         * 设置数据库的分片规则
         * inline表示行表达式分片算法，它使用groovy的表达式，支持单分片键，比如 t_user_$->{uid%8} 表示t_user表根据u_id%8分成8张表
         */
        configuration.setDefaultDatabaseShardingStrategy(
                new StandardShardingStrategyConfiguration("user_id", "inline"));
        /**
         * 设置表的分片规则
         */
        configuration.setDefaultTableShardingStrategy(new StandardShardingStrategyConfiguration("order_id", "order_inline"));
        Properties props = new Properties();
        props.setProperty("algorithm-expression", "ds${user_id%2}"); //表示根据user_id取模得到目标表
        /**
         * 定义具体的分片规则算法，用于提供分库分表的算法规则
         */
        configuration.getShardingAlgorithms().put("inline", new ShardingSphereAlgorithmConfiguration("INLINE", props));
        Properties properties = new Properties();
        properties.setProperty("algorithm-expression", "t_order_${order_id%2}");
        configuration.getShardingAlgorithms().put("order_inline", new ShardingSphereAlgorithmConfiguration("INLINE", properties));
        configuration.getKeyGenerators().put("snowflake", new ShardingSphereAlgorithmConfiguration("SNOWFLAKE", getProperties()));
        return configuration;
    }

    private static Properties getProperties() {
        Properties properties = new Properties();
        properties.setProperty("worker-id", "123");
        return properties;
    }

    //创建订单表的分片规则
    private static ShardingTableRuleConfiguration getOrderTableRuleConfiguration() {
        ShardingTableRuleConfiguration tableRule = new ShardingTableRuleConfiguration("t_order", "ds${0..1}.t_order_${0..1}");
        tableRule.setKeyGenerateStrategy(new KeyGenerateStrategyConfiguration("order_id", "snowflake"));
        return tableRule;
    }

    public static DataSource getDataSource() throws SQLException {
        return ShardingSphereDataSourceFactory.createDataSource(createDataSourceMap(), Collections.singleton(createShardingRuleConfiguration()), new Properties());
    }
}
