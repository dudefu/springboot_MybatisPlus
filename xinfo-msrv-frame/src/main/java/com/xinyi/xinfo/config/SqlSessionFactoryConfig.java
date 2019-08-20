package com.xinyi.xinfo.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class SqlSessionFactoryConfig
{

//    @Autowired
//    private DataSource dataSource;
//
//    @Autowired
//    private Interceptor paginationInterceptor;
//    
//    @Autowired
//    private OracleKeyGenerator oracleKeyGenerator;

//    // 创建全局配置
//    @Bean
//    public GlobalConfiguration globalConfig()
//    {
//        // 全局配置文件
//        GlobalConfiguration globalConfig = new GlobalConfiguration();
//        globalConfig.setIdType(IdType.AUTO.getKey());
//        globalConfig.setTablePrefix("tab_");
//        globalConfig.setLogicDeleteValue("1");
//        globalConfig.setLogicNotDeleteValue("0");
//        
//        return globalConfig;
//    }
//
//    // 自定义SqlSessionFactory类
//    @Bean
//    public MybatisSqlSessionFactoryBean sqlSessionFactory(GlobalConfiguration globalConfig) throws Exception
//    {
//        MybatisSqlSessionFactoryBean sessionFactory = new MybatisSqlSessionFactoryBean();
//        sessionFactory.setDataSource(dataSource);
//        sessionFactory.setTypeAliasesPackage("com.xinyi.xinfo.domain.model");
//        sessionFactory.setConfigLocation(new ClassPathResource("mybatis/mybatis-config.xml"));
//
//        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//        Resource[] mybatisRes = resolver.getResources("classpath*:com/xinyi/xinfo/domain/repository/xml/*.xml");
//
//        sessionFactory.setMapperLocations(mybatisRes);
//        sessionFactory.setPlugins(new Interceptor[] { paginationInterceptor });
//        
//        sessionFactory.setGlobalConfig(globalConfig);
//
//        return sessionFactory;
//    }

    
 
	 // 创建全局配置
//    @Bean
//    public GlobalConfig globalConfig() {
//        // 全局配置文件
//        GlobalConfig globalConfig = new GlobalConfig();
//        DbConfig dbConfig = new DbConfig();
//        // 默认为自增
//        dbConfig.setIdType(IdType.INPUT);
//        // 手动指定db 的类型
//        dbConfig.setDbType(DbType.ORACLE);
//        globalConfig.setDbConfig(dbConfig);
//        // 逻辑删除注入器
//        LogicSqlInjector injector = new LogicSqlInjector();
//        globalConfig.setSqlInjector(injector);
//        return globalConfig;
//    }

    // 自定义SqlSessionFactory类
//    @Bean
//    public MybatisSqlSessionFactoryBean sqlSessionFactory(GlobalConfig globalConfig) throws Exception
//    {
//        MybatisSqlSessionFactoryBean sessionFactory = new MybatisSqlSessionFactoryBean();
//        sessionFactory.setDataSource(dataSource);
//        sessionFactory.setTypeAliasesPackage("com.xinyi.xinfo.domain.model");
//        sessionFactory.setConfigLocation(new ClassPathResource("mybatis/mybatis-config.xml"));
//
//        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//        Resource[] mybatisRes = resolver.getResources("classpath*:com/xinyi/xinfo/domain/repository/xml/*.xml");
//
//        sessionFactory.setMapperLocations(mybatisRes);
//        sessionFactory.setPlugins(new Interceptor[] { paginationInterceptor });
//        sessionFactory.setGlobalConfig(globalConfig);
//
//        return sessionFactory;
//    }
    
    
	 // 创建全局配置
//    @Bean
//    public GlobalConfig globalConfig() {
//        // 全局配置文件
//        GlobalConfig globalConfig = new GlobalConfig();
//        DbConfig dbConfig = new DbConfig();
//        // 默认为自增
//        dbConfig.setIdType(IdType.INPUT);
//        // 手动指定db 的类型
//        dbConfig.setDbType(DbType.ORACLE);
//        dbConfig.setKeyGenerator(oracleKeyGenerator);
//        globalConfig.setDbConfig(dbConfig);
//        return globalConfig;
//    }
//    
//    
//    // 自定义SqlSessionFactory类
//    @Bean
//    public MybatisSqlSessionFactoryBean sqlSessionFactory(GlobalConfig globalConfig)  throws Exception{
//        return getSessionFactoryBean(dataSource,globalConfig);
//    }
//    
//    
//    private MybatisSqlSessionFactoryBean getSessionFactoryBean(
//    		DataSource dataSource, GlobalConfig globalConfig)  throws Exception{
//    	
//    	 MybatisSqlSessionFactoryBean sessionFactory = new MybatisSqlSessionFactoryBean();
//         sessionFactory.setDataSource(dataSource);
//         sessionFactory.setTypeAliasesPackage("com.xinyi.xinfo.domain.model");
//         sessionFactory.setConfigLocation(new ClassPathResource("mybatis/mybatis-config.xml"));
//
//         PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//         Resource[] mybatisRes = resolver.getResources("classpath*:com/xinyi/xinfo/domain/repository/xml/*.xml");
//
//         sessionFactory.setMapperLocations(mybatisRes);
//         sessionFactory.setPlugins(new Interceptor[] { paginationInterceptor });
//         sessionFactory.setGlobalConfig(globalConfig);
//        
////        // 源码里面如果有configuration, 不会注入BaseMapper里面的方法, 所以这里要这样写
////        MybatisConfiguration configuration = new MybatisConfiguration();
////        configuration.init(globalConfig);
////        configuration.setMapUnderscoreToCamelCase(true);
////        sessionFactory.setConfiguration(configuration);
////        
////        
////        List<Interceptor> interceptors = new ArrayList<>();
////        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
////        // 设置分页插件
////        interceptors.add(paginationInterceptor);
////        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
////        interceptors.add(performanceInterceptor);
////        
////        sessionFactory.setPlugins(interceptors.toArray(new Interceptor[0]));
//        
//        return sessionFactory;
//   }
    
    
}
