package com.xinyi.xinfo.domain.service;

public interface DataTableService {
    /**
     * 查询数据源中的所有表接口
     *
     * @param sourceId 数据源ID
     * @return
     */
    String queryTableList(String  sourceId);
    /**
     * 查询表结构接口
     *
     * @param sourceId 数据源ID
     * @param  tableName 表名
     * @return
     */
    String queryTableSchema(String  sourceId,String tableName);

    /**
     * 接入数据预览接口
     * @param sourceId 数据源ID
     * @param tableName 表名
     * @param columns 查询字段
     * @return
     */
    String queryTableDataList(String sourceId, String  tableName,String columns);

}
