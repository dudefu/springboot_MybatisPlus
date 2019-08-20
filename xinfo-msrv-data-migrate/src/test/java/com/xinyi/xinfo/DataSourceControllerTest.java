package com.xinyi.xinfo;


import com.xinyi.xinfo.utils.HttpUtil;
import org.junit.Test;
import java.util.HashMap;
import java.util.Map;

public class DataSourceControllerTest {

    String url = "http://127.0.0.1:8080";

    /**
     * 添加数据源测试
     */
    @Test
    public void addDataSource() {
        url = url+"/source/addDataSource";

        Map map = new HashMap<String, String>();
        map.put("sourceType", "mysql2");
        map.put("driverClass", "com.mysql.jdbc.Driver2");
        map.put("userName", "root2");
        map.put("password", "xinyi251322");
        map.put("url", "jdbc:mysql://10.24.5.21:3306/test22");
        map.put("remarks", "beizhu22");
        map.put("userId", "1001");

        System.out.println(HttpUtil.sendPost(url, map));
    }

    /**
     * 数据源测试是否能够成功连接
     */
    @Test
    public void connectionTestDataSource() {
        url = url+"/source/connectionTestDataSource";

        Map map = new HashMap<String, String>();
        map.put("driverClass", "com.mysql.jdbc.Driver");
        map.put("userName", "root");
        map.put("password", "xinyi2513");
        map.put("url", "jdbc:mysql://10.24.5.21:3306/test");

        System.out.println(HttpUtil.sendPost(url, map));
    }

    /**
     * 通过数据源ID更新数据源
     */
    @Test
    public void updateDataSourceById() {
        url = url+"/source/updateDataSourceById";

        Map map = new HashMap<String, String>();
        map.put("sourceId", "101");
        map.put("sourceType", "mmmmmmysql2");
        map.put("driverClass", "com.mysql.jdbc.Driver2");
        map.put("userName", "root2");
        map.put("password", "xinyi251322");
        map.put("url", "jdbc:mysql://10.24.5.21:3306/test22");
        map.put("remarks", "beizhu22");
        map.put("userId", "1001");

        System.out.println(HttpUtil.sendPost(url, map));
    }


    /**
     * 通过数据源ID获取数据源详细信息
     */
    @Test
    public void queryDataSourceById() {
        url = url+"/source/queryDataSourceById";

        Map map = new HashMap<String, String>();
        map.put("sourceId", "101");

        System.out.println(HttpUtil.sendPost(url, map));
    }


    /**
     * 通过用户ID查询该用户下面配置的所有数据源
     */
    @Test
    public void queryDataSourceList() {
        url = url+"/source/queryDataSourceList";

        Map map = new HashMap<String, String>();
        map.put("userId", "1001");

        System.out.println(HttpUtil.sendPost(url, map));
    }


    /**
     * 通过数据源ID停用某个数据源
     */
    @Test
    public void disableDataSourceById() {
        url = url+"/source/disableDataSourceById";

        Map map = new HashMap<String, String>();
        map.put("sourceId", "101");

        System.out.println(HttpUtil.sendPost(url, map));
    }


    /**
     * 通过数据源ID停用某个数据源
     */
    @Test
    public void enableDataSourceById() {
        url = url+"/source/enableDataSourceById";

        Map map = new HashMap<String, String>();
        map.put("sourceId", "101");

        System.out.println(HttpUtil.sendPost(url, map));
    }



}
