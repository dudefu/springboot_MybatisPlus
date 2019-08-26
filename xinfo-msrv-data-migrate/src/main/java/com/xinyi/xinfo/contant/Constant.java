package com.xinyi.xinfo.contant;

import com.xinyi.xinfo.util.PropertiesUtils;

import java.util.Properties;

public class Constant {

    public final static String PROPERTY_FILE_NAME = "application.properties";
    public final static Properties props = PropertiesUtils.getProperties();

    //定时调度参数配置
    public final static Long oneDay = Long.valueOf(props.getProperty("oneDay"));		    //每一天批量执行一次
    public final static Long initDelay = Long.valueOf(props.getProperty("initDelay"));	//延迟0分钟开始执行
    public final static String jsonPath = props.getProperty("jsonPath");                 //json文件夹地址
    public final static String dataxPath = props.getProperty("dataxPath");	            //datax的python文件地址

    //Oracle连接参数配置
    public final static String Oracle_CLASS_NAME = props.getProperty("msrv.primary.datasource.driverClassName");
    public final static String Oracle_URL = props.getProperty("msrv.primary.datasource.url");
    public final static String Oracle_USERNAME = props.getProperty("msrv.primary.datasource.username");
    public final static String Oracle_PASSWORD = props.getProperty("msrv.primary.datasource.password");

    //mysql连接参数
    public final static String MYSQL_CLASS_NAME = props.getProperty("mysql.class.name");
    public final static String MYSQL_URL = props.getProperty("mysql.url");
    public final static String MYSQL_USERNAME = props.getProperty("mysql.username");
    public final static String MYSQL_PASSWORD = props.getProperty("mysql.password");

    //greenplum连接参数
    public final static String GP_CLASS_NAME = props.getProperty("gp.class.name");
    public final static String GP_URL = props.getProperty("gp.url");
    public final static String GP_USERNAME = props.getProperty("gp.username");
    public final static String GP_PASSWORD = props.getProperty("gp.password");

    //datax读取插件reader
    public final static String MYSQLREADER_NAME = props.getProperty("mysqlreader.name");
    public final static String ORACLEREADER_NAME = props.getProperty("oraclereader.name");
    public final static String POSTGRESQLREADER_NAME = props.getProperty("postgresqlreader.name");
    public final static String SQLSERVERREADER_NAME = props.getProperty("sqlserverreader.name");
    public final static String READERUSERNAME = null ;
    public final static String READERPASSWORD = null ;
    public final static String READERCOLUMN = null ;
    public final static String READERtable = null ;
    public final static String READERjdbcUrl = null ;


    //datax读取插件writer
    public final static String MYSQLWRITER_NAME = props.getProperty("mysqlwriter.name");
    public final static String ORACLEWRITER_NAME = props.getProperty("oraclewriter.name");
    public final static String GPWRITER_NAME = props.getProperty("gpwriter.name");
    public final static String SQLSERVERWRITER_NAME = props.getProperty("sqlserverwriter.name");
    public final static String WRITERUSERNAME = null ;
    public final static String WRITERPASSWORD = null ;
    public final static String WRITERCOLUMN = null ;
    public final static String WRITERTABLE = null ;
    public final static String WRITERJDBCURL = null ;
    public final static int segment_reject_limit = 0 ;
    public final static int copy_queue_size = 1000 ;
    public final static int num_copy_processor = 4 ;
    public final static int num_copy_writer = 1 ;

    //sourceId
    public final static String MYSQL_SOURCEID = props.getProperty("mysql.sourceId");
    public final static String ORACLE_SOURCEID = props.getProperty("oracle.sourceId");
    public final static String POSTG_SOURCEID = props.getProperty("postgresql.sourceId");
    public final static String SQLSERVER_SOURCEID = props.getProperty("sqlserver.sourceId");

    //定时时间设置
    public static String cronTime = "0/6 * * * * ?";


}
