package com.xinyi.xinfo.util;

import java.util.ArrayList;
import java.util.List;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

//演示例子，执行 main 方法控制台输入模块表名回车自动生成对应项目目录中
public class AutoGeneratorUtils
{

    public static void main(String[] args)
    {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("xiaohao.pang");
        // gc.setSwagger2(true);
        gc.setOpen(false);
        gc.setFileOverride(true);
        gc.setActiveRecord(true);// 开启 activeRecord 模式
        gc.setEnableCache(false);// XML 二级缓存
        gc.setBaseResultMap(true);// XML ResultMap
        gc.setBaseColumnList(true);// XML columList
        gc.setServiceName("%sService");// 指定servic类名称
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:oracle:thin:@183.62.140.8:15221:nsqw/oauth2_test?useUnicode=true&useSSL=false&characterEncoding=utf8");
        // dsc.setSchemaName("public");
        dsc.setDriverName("oracle.jdbc.OracleDriver");
        dsc.setUsername("xy_test");
        dsc.setPassword("xy_test");
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
        strategy.setInclude("oauth_client_details");
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setSuperControllerClass("com.xinyi.xinfo.controller.BaseController");
        strategy.setSuperServiceImplClass("com.xinyi.xinfo.domain.service.support.BaseServiceImpl");
        strategy.setTablePrefix("tab_");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }

}
