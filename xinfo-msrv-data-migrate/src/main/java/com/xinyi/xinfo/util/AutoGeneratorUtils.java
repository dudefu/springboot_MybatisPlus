package com.xinyi.xinfo.util;

import java.util.ArrayList;
import java.util.List;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.ITypeConvert;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.converts.OracleTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

//演示例子，执行 main 方法控制台输入模块表名回车自动生成对应项目目录中
public class AutoGeneratorUtils
{


    public static void main(String[] args)
    {
        AutoGenerator02();
    }


    public static void AutoGenerator01() {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("haikun.shao");
        gc.setSwagger2(true);
        gc.setOpen(false);
        gc.setFileOverride(true);
        gc.setActiveRecord(true);// 开启 activeRecord 模式
        gc.setEnableCache(false);// XML 二级缓存
        gc.setBaseResultMap(true);// XML ResultMap
        gc.setBaseColumnList(true);// XML columList
        gc.setServiceName("%sService");// 指定servic类名称
        gc.setDateType(DateType.ONLY_DATE);
        mpg.setGlobalConfig(gc);
        // 数据源配置
//        DataSourceConfig dsc = new DataSourceConfig();
//        dsc.setUrl("jdbc:mysql://localhost:3306/msrv_baseserver?useUnicode=true&useSSL=false&characterEncoding=utf8");
//        // dsc.setSchemaName("public");
//        dsc.setDriverName("com.mysql.jdbc.Driver");
//        dsc.setUsername("root");
//        dsc.setPassword("xinyi2513");
//        mpg.setDataSource(dsc);
        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.ORACLE);
        dsc.setTypeConvert(new OracleTypeConvert());
        dsc.setUrl("jdbc:oracle:thin:@10.200.66.64:1521:nsqw");
        // dsc.setSchemaName("public");
        dsc.setDriverName("oracle.jdbc.OracleDriver");
        dsc.setUsername("XY_BASE01");
        dsc.setPassword("XY_BASE01");
        mpg.setDataSource(dsc);



        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent(null);
        pc.setController("com.xinyi.xinfo.controller");// 这里是控制器包名，默认 web
        pc.setEntity("com.xinyi.xinfo.domain.model");
        pc.setMapper("com.xinyi.xinfo.domain.repository");
        pc.setXml("com.xinyi.xinfo.domain.repository.xml");
        pc.setService("com.xinyi.xinfo.domain.service");
        pc.setServiceImpl("com.xinyi.xinfo.domain.service.impl");
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig()
        {
            @Override
            public void initMap()
            {
                // to do nothing
            }
        };
        List<FileOutConfig> focList = new ArrayList<>();
        focList.add(new FileOutConfig("/templates/mapper.xml.ftl")
        {
            @Override
            public String outputFile(TableInfo tableInfo)
            {
                // 自定义输入文件名称
                return projectPath + "/src/main/java/com/xinyi/xinfo/domain/repository/xml/" + tableInfo.getEntityName() + "Mapper.xml";
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
        mpg.setTemplate(new TemplateConfig().setXml(null));

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(false);
        strategy.setRestControllerStyle(true);
        strategy.setInclude("righttochangroup");
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setSuperControllerClass("com.xinyi.xinfo.controller.BaseController");
        strategy.setSuperServiceImplClass("com.xinyi.xinfo.domain.service.support.BaseServiceImpl");
//        strategy.setTablePrefix("right");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }

    public static void AutoGenerator02() {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("mingshen.wang");
        gc.setSwagger2(true);
        gc.setOpen(false);
        gc.setFileOverride(false);//文件覆盖
        gc.setActiveRecord(true);// 开启 activeRecord 模式
        gc.setEnableCache(false);// XML 二级缓存
        gc.setBaseResultMap(true);// XML ResultMap
        gc.setBaseColumnList(true);// XML columList
        gc.setServiceName("%sService");// 指定servic类名称
        gc.setIdType(IdType.INPUT);//指定生成的主键的ID类型
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.ORACLE);
        dsc.setTypeConvert(new MyOracleTypeConvert());
//       dsc.setSchemaName("public");
        dsc.setUrl("jdbc:oracle:thin:@183.62.140.8:15221:nsqw");
        dsc.setDriverName("oracle.jdbc.OracleDriver");
        dsc.setUsername("XY_DATAVIEW");
        dsc.setPassword("XY_DATAVIEW01");

//       dsc.setDriverName("oracle.jdbc.OracleDriver");
//       dsc.setUrl("jdbc:oracle:thin:@10.17.3.120:1521:XSIGHT");
//       dsc.setUsername("XY_USER01");
//       dsc.setPassword("XY_USER0112");
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
//        pc.setModuleName("");//模块名
        pc.setParent(null);
        pc.setController("com.xinyi.xinfo.controller");// 这里是控制器包名，默认 web
        pc.setEntity("com.xinyi.xinfo.domain.model");
        pc.setMapper("com.xinyi.xinfo.domain.repository");
        pc.setXml("com.xinyi.xinfo.domain.repository.xml");
        pc.setService("com.xinyi.xinfo.domain.service");
        pc.setServiceImpl("com.xinyi.xinfo.domain.service.impl");
        mpg.setPackageInfo(pc);



        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
        // String templatePath = "/templates/mapper.xml.vm";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
//                return projectPath + "/src/main/resources/mapper/" + pc.getModuleName()
//                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
                // 自定义输入文件名称
                return projectPath + "/src/main/java/com/xinyi/xinfo/domain/repository/xml/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });

        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // templateConfig.setEntity("templates/entity2.java");
        // templateConfig.setService();
        // templateConfig.setController();

        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(false);
        strategy.setRestControllerStyle(true);
        strategy.setInclude("TAB_DATA_MIGRATE_SOURCE");//表名，多个英文逗号分割
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setSuperControllerClass("com.xinyi.xinfo.controller.BaseController");
        strategy.setSuperServiceImplClass("com.xinyi.xinfo.domain.service.support.BaseServiceImpl");
//        strategy.setSuperEntityColumns("id");
//        strategy.setSuperEntityClass("com.baomidou.ant.common.BaseEntity");
//        strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();

    }
}

