package com.xinyi.xinfo.domain.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 数据源表
 * </p>
 *
 * @author mingshen.wang
 * @since 2019-08-16
 */
@TableName("TAB_DATA_MIGRATE_SOURCE")
@ApiModel(value="TabDataMigrateSource对象", description="数据源表")
public class TabDataMigrateSource extends Model<TabDataMigrateSource> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "数据源id")
    private Long sourceId;

    @ApiModelProperty(value = "数据源类型")
    private String sourceType;

    private String driverClass;

    private String userName;

    private String password;

    private String url;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "是否有效，1 有效，2 失效")
    private Long enable;

    @ApiModelProperty(value = "该数据源配置属于哪个用户")
    private String userId;

    public Long getSourceId() {
        return sourceId;
    }

    public void setSourceId(Long sourceId) {
        this.sourceId = sourceId;
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
    public Long getEnable() {
        return enable;
    }

    public void setEnable(Long enable) {
        this.enable = enable;
    }
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "TabDataMigrateSource{" +
        "sourceId=" + sourceId +
        ", sourceType=" + sourceType +
        ", driverClass=" + driverClass +
        ", userName=" + userName +
        ", password=" + password +
        ", url=" + url +
        ", remarks=" + remarks +
        ", enable=" + enable +
        ", userId=" + userId +
        "}";
    }
}
