package com.xinyi.xinfo.config;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.incrementer.OracleKeyGenerator;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;

@Configuration
public class DataSourceConfig
{
    @Autowired
    private WallFilter wallFilter;

    @Bean // 声明其为Bean实例
    @Primary // 在同样的DataSource中，首先使用被标注的DataSource
    @ConfigurationProperties(prefix = "msrv.primary.datasource")
    public DataSource dataSource()
    {
        DruidDataSource datasource = new DruidDataSource();

        List<Filter> filters = new ArrayList<Filter>();
        filters.add(wallFilter);
        datasource.setProxyFilters(filters);
        return datasource;
    }

    @Bean(name = "wallConfig")
    public WallConfig wallFilterConfig()
    {
        WallConfig wc = new WallConfig();
        wc.setMultiStatementAllow(true);
        return wc;
    }

    @Bean(name = "wallFilter")
    @DependsOn("wallConfig")
    public WallFilter wallFilter(WallConfig wallConfig)
    {
        WallFilter wfilter = new WallFilter();
        wfilter.setConfig(wallConfig);
        return wfilter;
    }
    
    @Bean(name = "paginationInterceptor")
    public Interceptor paginationInterceptor()
    {
        PaginationInterceptor interceptor = new PaginationInterceptor();
        return interceptor;
    }
    
//    @Bean
//    public OracleKeyGenerator oracleKeyGenerator(){
//      return new OracleKeyGenerator();
//    }

}
