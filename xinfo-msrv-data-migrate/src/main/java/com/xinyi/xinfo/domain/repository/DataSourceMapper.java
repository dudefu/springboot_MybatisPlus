package com.xinyi.xinfo.domain.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xinyi.xinfo.domain.model.DataSource;

import java.util.List;

public interface DataSourceMapper extends BaseMapper<DataSource> {
    /**
     * 添加数据源
     * @param ds
     */
    void addDataSource(DataSource ds);

    /**
     * 删除（停用）数据源
     * @param sourceId
     * @return
     */
    int disableDataSourceById(String sourceId);
    /**
     * 启用数据源
     * @param sourceId
     * @return
     */
    int enableDataSourceById(String sourceId);
    /**
     * 通过userId 查询该用户下面配置所有的数据源
     * @param userId
     * @return
     */
    List<DataSource> queryDataSourceList(String userId);


    /**
     * 通过 sourceId 获取数据源详细信息
     * @param sourceId
     * @return
     */
    DataSource queryDataSourceById(String sourceId);

    /**
     * 修改数据源接口
     * @param ds 修改数据源对象
     * @return
     */
    int updateDataSourceById(DataSource ds);

    /**
     * 测试的配置的数据源是否能够连接成功
     * @param sourceType
     * @param driverClass
     * @param userName
     * @param password
     * @param url
     * @param remarks
     * @return
     */
    String connectionTestDataSource(String sourceType, String driverClass, String userName, String password, String url, String remarks);

    DataSource getDataSourceById();

}
