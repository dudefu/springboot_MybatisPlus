package com.xinyi.xinfo.domain.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.baomidou.mybatisplus.annotation.TableField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "日志信息实体对象")
public class LogInfo implements Serializable
{
    private static final long serialVersionUID = -6229906676902563167L;

    @ApiModelProperty(value = "id主键")
    private Long id;

    @ApiModelProperty(value = "服务id")
    private String foreignRegId;

    @ApiModelProperty(value = "用户标识")
    private String userId;

    @ApiModelProperty(value = "单位名称")
    private String organization;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "操作时间(yyyy-MM-dd HH:mm:ss)")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date operateTime;

    @ApiModelProperty(value = "终端标识")
    private String terminalId;

    @ApiModelProperty(value = "操作类型0：登录；1：查询；2：新增；3：修改；4：删除")
    private Integer operateType;

    @ApiModelProperty(value = "操作结果1:成功；0：失败")
    private Integer operateResult;

    @ApiModelProperty(value = "机构代码")
    private String organizationId;

    @ApiModelProperty(value = "失败原因代码(非必填)")
    private Integer errorCode;

    @ApiModelProperty(value = "功能模块名称")
    private String operateName;

    @ApiModelProperty(value = "操作条件(非必填)")
    private String operateCondition;

    @ApiModelProperty(value = "操作返回条目数(非必填)")
    private Integer operateNumber;

    @ApiModelProperty(value = "被操作数据表(非必填)")
    private String operateTable;

    @ApiModelProperty(value = "被操作数据的主键标识(非必填)")
    private String operateKey;
    
    @ApiModelProperty(value = "应用系统id")
    private String adhibitionId;
    
    @ApiModelProperty(value = "操作URL")
    private String operateUrl;

    @ApiModelProperty(value = "应用名称", hidden = true)
    @TableField(exist = false)
    private String serverName;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getForeignRegId()
    {
        return foreignRegId;
    }

    public void setForeignRegId(String foreignRegId)
    {
        this.foreignRegId = foreignRegId == null ? null : foreignRegId.trim();
    }

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getOrganization()
    {
        return organization;
    }

    public void setOrganization(String organization)
    {
        this.organization = organization == null ? null : organization.trim();
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName == null ? null : userName.trim();
    }

    public Date getOperateTime()
    {
        return operateTime;
    }

    public void setOperateTime(Date operateTime)
    {
        this.operateTime = operateTime;
    }

    public String getTerminalId()
    {
        return terminalId;
    }

    public void setTerminalId(String terminalId)
    {
        this.terminalId = terminalId == null ? null : terminalId.trim();
    }

    public Integer getOperateType()
    {
        return operateType;
    }

    public void setOperateType(Integer operateType)
    {
        this.operateType = operateType;
    }

    public Integer getOperateResult()
    {
        return operateResult;
    }

    public void setOperateResult(Integer operateResult)
    {
        this.operateResult = operateResult;
    }

    public String getOrganizationId()
    {
        return organizationId;
    }

    public void setOrganizationId(String organizationId)
    {
        this.organizationId = organizationId == null ? null : organizationId.trim();
    }

    public Integer getErrorCode()
    {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode)
    {
        this.errorCode = errorCode;
    }

    public String getOperateName()
    {
        return operateName;
    }

    public void setOperateName(String operateName)
    {
        this.operateName = operateName == null ? null : operateName.trim();
    }

    public String getOperateCondition()
    {
        return operateCondition;
    }

    public void setOperateCondition(String operateCondition)
    {
        this.operateCondition = operateCondition == null ? null : operateCondition.trim();
    }

    public Integer getOperateNumber()
    {
        return operateNumber;
    }

    public void setOperateNumber(Integer operateNumber)
    {
        this.operateNumber = operateNumber;
    }

    public String getOperateTable()
    {
        return operateTable;
    }

    public void setOperateTable(String operateTable)
    {
        this.operateTable = operateTable == null ? null : operateTable.trim();
    }

    public String getOperateKey()
    {
        return operateKey;
    }

    public void setOperateKey(String operateKey)
    {
        this.operateKey = operateKey == null ? null : operateKey.trim();
    }

    public String getOperateUrl() {
		return operateUrl;
	}

	public void setOperateUrl(String operateUrl) {
		this.operateUrl = operateUrl;
	}

	public String getServerName()
    {
        return serverName;
    }

    public void setServerName(String serverName)
    {
        this.serverName = serverName;
    }
    
    public String getAdhibitionId() {
		return adhibitionId;
	}

	public void setAdhibitionId(String adhibitionId) {
		this.adhibitionId = adhibitionId;
	}

	@Override
	public String toString() {
		return "LogInfo [id=" + id + ", foreignRegId=" + foreignRegId + ", userId=" + userId + ", organization="
				+ organization + ", userName=" + userName + ", operateTime=" + operateTime + ", terminalId="
				+ terminalId + ", operateType=" + operateType + ", operateResult=" + operateResult + ", organizationId="
				+ organizationId + ", errorCode=" + errorCode + ", operateName=" + operateName + ", operateCondition="
				+ operateCondition + ", operateNumber=" + operateNumber + ", operateTable=" + operateTable
				+ ", operateKey=" + operateKey + ", adhibitionId=" + adhibitionId + ", operateUrl=" + operateUrl
				+ ", serverName=" + serverName + "]";
	}

	

}