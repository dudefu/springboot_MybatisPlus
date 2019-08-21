package com.xinyi.xinfo.domain.model;

/**
 * 请求任务参数对象
 */
public class TaskDescription {

    /**
     * 任务描述id
     */
    private int tdTaskId ;
    /**
     * 数据源Id
     */
    private int tdSourceId ;
    /**
     * 增量（Increment）/全量（whole）
     */
    private String tdMode;
    /**
     * 增量导入时标识字段名称
     */
    private String tdIncrementColumn ;
    /**
     * 创建某个表相同的结构
     */
    private String tdTableName ;
    /**
     * 同步字段，多个字段用逗号分隔
     */
    private String tdColumns;
    /**
     * 同步数据到GP哪张表中
     */
    private String tdTargetTableName ;
    /**
     * 调度方式:使用crontab 描述方式,一次全量导入则为空
     */
    private String tdDispatch ;
    /**
     * 备注
     */
    private String tdRemarks ;

    public TaskDescription(int tdTaskId, int tdSourceId, String tdMode, String tdIncrementColumn, String tdTableName, String tdColumns, String tdTargetTableName, String tdDispatch, String tdRemarks) {
        this.tdTaskId = tdTaskId;
        this.tdSourceId = tdSourceId;
        this.tdMode = tdMode;
        this.tdIncrementColumn = tdIncrementColumn;
        this.tdTableName = tdTableName;
        this.tdColumns = tdColumns;
        this.tdTargetTableName = tdTargetTableName;
        this.tdDispatch = tdDispatch;
        this.tdRemarks = tdRemarks;
    }

    public int getTdTaskId() {
        return tdTaskId;
    }

    public void setTdTaskId(int tdTaskId) {
        this.tdTaskId = tdTaskId;
    }

    public int getTdSourceId() {
        return tdSourceId;
    }

    public void setTdSourceId(int tdSourceId) {
        this.tdSourceId = tdSourceId;
    }

    public String getTdMode() {
        return tdMode;
    }

    public void setTdMode(String tdMode) {
        this.tdMode = tdMode;
    }

    public String getTdIncrementColumn() {
        return tdIncrementColumn;
    }

    public void setTdIncrementColumn(String tdIncrementColumn) {
        this.tdIncrementColumn = tdIncrementColumn;
    }

    public String getTdTableName() {
        return tdTableName;
    }

    public void setTdTableName(String tdTableName) {
        this.tdTableName = tdTableName;
    }

    public String getTdColumns() {
        return tdColumns;
    }

    public void setTdColumns(String tdColumns) {
        this.tdColumns = tdColumns;
    }

    public String getTdTargetTableName() {
        return tdTargetTableName;
    }

    public void setTdTargetTableName(String tdTargetTableName) {
        this.tdTargetTableName = tdTargetTableName;
    }

    public String getTdDispatch() {
        return tdDispatch;
    }

    public void setTdDispatch(String tdDispatch) {
        this.tdDispatch = tdDispatch;
    }

    public String getTdRemarks() {
        return tdRemarks;
    }

    public void setTdRemarks(String tdRemarks) {
        this.tdRemarks = tdRemarks;
    }
}
