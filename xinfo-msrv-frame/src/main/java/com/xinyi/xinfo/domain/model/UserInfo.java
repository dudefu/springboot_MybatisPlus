package com.xinyi.xinfo.domain.model;

import java.io.Serializable;

public class UserInfo implements Serializable
{

    private static final long serialVersionUID = 6213612077659876829L;

    private String userId;

    private String userName;

    private String name;

    private String orgCode;

    private String orgName;

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getOrgCode()
    {
        return orgCode;
    }

    public void setOrgCode(String orgCode)
    {
        this.orgCode = orgCode;
    }

    public String getOrgName()
    {
        return orgName;
    }

    public void setOrgName(String orgName)
    {
        this.orgName = orgName;
    }

    @Override
    public String toString()
    {
        return "UserInfo [userId=" + userId + ", userName=" + userName + ", name=" + name + ", orgCode=" + orgCode + ", orgName=" + orgName + "]";
    }

}
