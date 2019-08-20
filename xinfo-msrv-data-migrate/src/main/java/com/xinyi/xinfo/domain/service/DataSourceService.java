package com.xinyi.xinfo.domain.service;

import com.xinyi.xinfo.domain.model.DataSource;

public interface DataSourceService {

    /**
     * 增加数据源接口
     * @param sourceType
     * @param driverClass
     * @param userName
     * @param password
     * @param url
     * @param remarks
     * @param userId
     * @return
     */
    String addDataSource(String sourceType, String driverClass, String userName, String password, String url, String remarks,String userId);


    /**
     * 删除（停用）数据源
     * @param sourceId
     * @return
     */
    String disableDataSourceById(String sourceId);
    /**
     * 启用数据源
     * @param sourceId
     * @return
     */
    String enableDataSourceById(String sourceId);

    /**
     * 通过userId 查询该用户下面配置所有的数据源
     * @param userId
     * @return
     */
    String queryDataSourceList(String userId);

    /**
     * 通过 sourceId 获取数据源详细信息
     * @param sourceId
     * @return
     */
    String queryDataSourceById(String sourceId);

    /**
     * 修改数据源接口
     * @param sourceId
     * @param sourceType
     * @param driverClass
     * @param userName
     * @param password
     * @param url
     * @param remarks
     * @return
     */
    String updateDataSourceById(String sourceId,String sourceType, String driverClass, String userName, String password, String url, String remarks);


    DataSource getDataSourceById();

    /**
     * 测试的配置的数据源是否能够连接成功
     * @param driverClass
     * @param userName
     * @param password
     * @param url
     * @return
     */
    String connectionTestDataSource(String driverClass, String userName, String password, String url);
}
