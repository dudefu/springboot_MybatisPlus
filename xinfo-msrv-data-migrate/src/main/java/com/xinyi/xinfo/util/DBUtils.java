package com.xinyi.xinfo.util;

import com.alibaba.fastjson.JSONObject;
import com.xinyi.xinfo.bean.Status;


import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtils {

    /**
     * RDBMSService 工厂方法，根据不同的driverclass 创建 service
     * @param driverClass
     * @return
     */
    /*public static RDBMSDao RDBMSServiceFactory(String driverClass){

        RDBMSDao rdbmsService = null;

        if(driverClass.contains("mysql")){
            rdbmsService = new MySQLDaoImpl();
        }else if(driverClass.contains("oracle")){
            rdbmsService = new OracleDaoImpl();
        }
        return rdbmsService;
    }*/


    /**
     * 测试数据源配置是否正确，测试是否能够成功连接到数据源
     *
     * @param driver
     * @param userName
     * @param password
     * @param url
     * @return
     */
    public static Boolean testConnection(String driver, String userName, String password, String url) throws Exception {

        Boolean b;

        Connection conn = getConnection(driver, userName, password, url);
        b = !conn.isClosed();
        if (!conn.isClosed() && null != conn) {
            conn.close();
        }
        return b;
    }

    /**
     * 获取连接
     *
     * @param driver
     * @param userName
     * @param password
     * @param url
     * @return
     * @throws Exception
     */
    public static Connection getConnection(
            String driver,
            String userName,
            String password,
            String url
    ) throws Exception {
        Class.forName(driver);
        return DriverManager.getConnection(url, userName, password);
    }
}
