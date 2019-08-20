package com.xinyi.xinfo.domain.model;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

/**
 * <p>
 * 操作日志表
 * </p>
 *
 * @author xiva
 * @since 2018-07-06
 */
@TableName("tf_sys_log")
public class TfSysLog extends Model<TfSysLog>
{

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;
    /**
     * 用户ID
     */
    private Long uid;
    /**
     * 日志内容
     */
    private String content;
    /**
     * 用户操作
     */
    private String operation;
    /**
     * 操作时间
     */
    private Date crTime;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getUid()
    {
        return uid;
    }

    public void setUid(Long uid)
    {
        this.uid = uid;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public String getOperation()
    {
        return operation;
    }

    public void setOperation(String operation)
    {
        this.operation = operation;
    }

    public Date getCrTime()
    {
        return crTime;
    }

    public void setCrTime(Date crTime)
    {
        this.crTime = crTime;
    }

    @Override
    protected Serializable pkVal()
    {
        return this.id;
    }

}
