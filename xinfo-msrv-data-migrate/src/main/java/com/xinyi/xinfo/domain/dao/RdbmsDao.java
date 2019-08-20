package com.xinyi.xinfo.domain.dao;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xinyi.xinfo.domain.model.DataSource;

public interface RdbmsDao {
    /**
     * 查询数据源中的所有表接口
     *
     * @param ds 数据源对象
     * @return
     */
    JSONObject queryTableList(DataSource ds) throws Exception;
    /**
     * 查询表结构接口
     *
     * @param ds 数据源对象
     * @param  tableName 表名
     * @return
     */
    JSONObject queryTableSchema(DataSource ds,String tableName) throws Exception;
    /**
     * 接入数据预览接口
     * @param ds 数据源对象
     * @param tableName 表名
     * @param columns 查询字段
     * @return
     */
    JSONArray queryTableDataList(DataSource ds, String  tableName, String columns) throws Exception;
}
