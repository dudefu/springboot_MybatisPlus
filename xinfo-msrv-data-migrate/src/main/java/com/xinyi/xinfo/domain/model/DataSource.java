package com.xinyi.xinfo.domain.model;

public class DataSource {
    public DataSource(java.math.BigDecimal sourceId, String sourceType, String driverClass, String userName, String password, String url, String remarks) {
        this.sourceId = sourceId;
        this.sourceType = sourceType;
        this.driverClass = driverClass;
        this.userName = userName;
        this.password = password;
        this.url = url;
        this.remarks = remarks;
    }

    public DataSource(String sourceType, String driverClass, String userName, String password, String url, String remarks, String userId) {
        this.sourceType = sourceType;
        this.driverClass = driverClass;
        this.userName = userName;
        this.password = password;
        this.url = url;
        this.remarks = remarks;
        this.userId = userId;
    }

    public DataSource() {
    }

    /**
     * 数据源ID
     */
    private java.math.BigDecimal sourceId;

    /**
     * 数据源类型 （MySQL、Oracle、SQLServer……）
     */
    private String sourceType;
    /**
     * Driver driverClass
     */
    private String driverClass;
    /**
     * 数据库用户名
     */
    private String userName;
    /**
     * 数据库密码
     */
    private String password;
    /**
     * 数据库地址
     */
    private String url;
    /**
     * 备注，对于数据源的描述
     */
    private String remarks;

    /**
     * 该数据源所属用户ID
     */
    private String userId;
    /**
     * 是否启用该数据源
     */
    private java.math.BigDecimal enable;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public java.math.BigDecimal getEnable() {
        return enable;
    }

    public void setEnable(java.math.BigDecimal enable) {
        this.enable = enable;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public String getDriverClass() {
        return driverClass;
    }

    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }


    public java.math.BigDecimal getSourceId() {
        return sourceId;
    }

    public void setSourceId(java.math.BigDecimal sourceId) {
        this.sourceId = sourceId;
    }

}
