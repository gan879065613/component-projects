package com.ganjunhao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.*;

/**
 * @author ganjunhao
 * @date 2023/4/6 15:41
 */
public class CodeGenerator {
    // TODO 项目名称
    private static final String PROJECT_NAME = "code";
    // TODO 微服务项目模块名（可选，和单体二选一）
    private static final String CLOUD_NAME = "code-example";
    // TODO 路径包名（生成的文件放在绝地路径下，为下面路径代码铺垫）
    private static final String PACKAGE_DIR = "/com/code/product/";
    // TODO 生成包名
    private static final String PACKAGE_EXE = "jnpf.asso";
    // TODO 数据库地址
    private static final String URL = "jdbc:mysql://localhost:63306/zgyf_boot_dev?useUnicode=true&characterEncoding =UTF-8&serverTimezone = Asia/Shanghai";
    // TODO 数据库用户名
    private static final String USERNAME = "root";
    // TODO 数据库密码
    private static final String PASSWORD = "haohao1995810";
    // TODO 作者
    private static final String AUTHOR = "ganjunhao";
    // TODO 去除的表前缀（可选）
    private static final String TABLE_PREFIX = "t_";
    // TODO 去除的表字段前缀（可选）
    private static final String FIELD_PREFIX = "F_";
    // TODO 模板路径（可选）
    private static final String TEMPLATE_PATH = "/templates/entity.java";
    /**
     * 项目路径
     */
    private static final String PARENT_DIR = System.getProperty("user.dir");
    /**
     * xml路径
     */
    //private static final String XML_PATH = PARENT_DIR + "/"+PROJECT_NAME+"-mapper/src/main/resources/mapper"; //单体项目
    private static final String XML_PATH = PARENT_DIR + "/" + CLOUD_NAME + "/src/main/resources/mapper";
    /**
     * entity路径
     */
    //private static final String ENTITY_PATH = PARENT_DIR +"/"+PROJECT_NAME+"-entity/src/main/java"+PACKAGE_DIR+"entity";//单体项目
    private static final String ENTITY_PATH = PARENT_DIR + "/" + CLOUD_NAME + "/src/main/java" + PACKAGE_DIR + "entity";
    /**
     * mapper（Dao）路径
     */
    //private static final String MAPPER_PATH = PARENT_DIR +"/"+PROJECT_NAME+"-mapper/src/main/java"+PACKAGE_DIR+"mapper";//单体项目
    private static final String MAPPER_PATH = PARENT_DIR + "/" + CLOUD_NAME + "/src/main/java" + PACKAGE_DIR + "dao";
    /**
     * service路径
     */
    //private static final String SERVICE_PATH = PARENT_DIR +"/"+PROJECT_NAME+"-service/src/main/java"+PACKAGE_DIR+"service";//单体项目
    private static final String SERVICE_PATH = PARENT_DIR + "/" + CLOUD_NAME + "/src/main/java" + PACKAGE_DIR + "service";
    /**
     * serviceImpl路径
     */
    //private static final String SERVICE_IMPL_PATH = PARENT_DIR +"/"+PROJECT_NAME+"-service/src/main/java"+PACKAGE_DIR+"service/impl/";//单体项目
    private static final String SERVICE_IMPL_PATH = PARENT_DIR + "/" + CLOUD_NAME + "/src/main/java" + PACKAGE_DIR + "service/impl/";
    /**
     * controller路径
     */
    //private static final String CONTROLLER_PATH = PARENT_DIR +"/"+PROJECT_NAME+"-api/src/main/java"+PACKAGE_DIR+"controller";//单体项目
    private static final String CONTROLLER_PATH = PARENT_DIR + "/" + CLOUD_NAME + "/src/main/java" + PACKAGE_DIR + "controller";

    public static void main(String[] args) {
        FastAutoGenerator.create(URL, USERNAME, PASSWORD)
                // 全局配置
                .globalConfig(builder -> builder
                        // 设置作者
                        .author(AUTHOR)
                        // 开启swagger注解
                        .enableSwagger()
                        .disableOpenDir()
                )
                // 包配置
                .packageConfig(builder -> builder
                        .parent("")
                        .xml("mapper")
                        .entity(PACKAGE_EXE + ".entity")
                        .mapper(PACKAGE_EXE + ".dao")
                        .service(PACKAGE_EXE + ".service")
                        .serviceImpl(PACKAGE_EXE + ".service.impl")
                        .controller(PACKAGE_EXE + ".controller")
                        .pathInfo(getPathInfo())
                )
                // 策略配置
                .strategyConfig((scanner, builder) ->
                                builder.addInclude(getTables(scanner.apply("请输入表名，多个表之间用英文逗号分隔，所有输入all")))
                                        .addTablePrefix(TABLE_PREFIX)
                                        .addFieldPrefix(FIELD_PREFIX)
                                        // entity
                                        .entityBuilder()
                                        .fileOverride()
                                        .enableChainModel()
                                        .fileOverride()
                                        // 开启lombock
                                        .enableLombok()
                                        .enableTableFieldAnnotation()
                                        //.logicDeleteColumnName("deleted")
                                        //.logicDeletePropertyName("deleteFlag")
                                        .idType(IdType.AUTO)
                                        //.addTableFills(new Column("create_time", FieldFill.INSERT))
                                        //.addTableFills(new Property("update_time", FieldFill.INSERT_UPDATE))
                                        // controller
                                        .controllerBuilder()
                                        .fileOverride()
                                        .enableRestStyle()
                                        .formatFileName("%sController")
                                        // service
                                        .serviceBuilder()
                                        .fileOverride()
                                        .superServiceClass(IService.class)
                                        .formatServiceFileName("%sService")
                                        .formatServiceImplFileName("%sServiceImpl")
                                        // mapper
                                        .mapperBuilder()
                                        .fileOverride()
                                        .enableBaseResultMap()
                                        .enableBaseColumnList()
                                        .superClass(BaseMapper.class)
                                        .formatMapperFileName("%sMapper")
                                        .formatXmlFileName("%sMapper")
                        //.enableMapperAnnotation()
                )
                // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .templateConfig(builder -> {
                    // 实体类使用我们自定义模板
                    builder.entity("/templates/entity.java");
                })
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();


    }

    /**
     * 获取路径信息map
     *
     * @author 何翔
     */
    private static Map<OutputFile, String> getPathInfo() {
        Map<OutputFile, String> pathInfo = new HashMap<>(5);
        pathInfo.put(OutputFile.entity, ENTITY_PATH);
        pathInfo.put(OutputFile.mapper, MAPPER_PATH);
        pathInfo.put(OutputFile.service, SERVICE_PATH);
        pathInfo.put(OutputFile.serviceImpl, SERVICE_IMPL_PATH);
        pathInfo.put(OutputFile.controller, CONTROLLER_PATH);
        pathInfo.put(OutputFile.xml, XML_PATH);
        return pathInfo;
    }

    // 处理 all 情况
    protected static List<String> getTables(String tables) {
        return "all".equals(tables) ? Collections.emptyList() : Arrays.asList(tables.split(","));
    }
}
