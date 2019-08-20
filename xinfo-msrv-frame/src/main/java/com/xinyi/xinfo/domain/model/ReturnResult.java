package com.xinyi.xinfo.domain.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value="返回信息")
public class ReturnResult<T>
{
	@ApiModelProperty(value="状态码")
    private Integer code = 0;// 是否成功 0：成功
	
	@ApiModelProperty(value="路径")
    private String cmd = "";
	
	@ApiModelProperty(value="请求数据")
    private T data = null;
		
		
	public ReturnResult()
	{
	    super();
	}
	
	public ReturnResult(T data,String cmd,Integer code)
	{
	    super();
	    this.data=data;
	    this.cmd=cmd;
	    this.code=code;
	}
	
    public Integer getCode()
    {
        return code;
    }

    public void setCode(Integer code)
    {
        this.code = code;
    }

    public String getCmd()
    {
        return cmd;
    }

    public void setCmd(String cmd)
    {
        this.cmd = cmd;
    }

    public T getData()
    {
        return data;
    }

    public void setData(T data)
    {
        this.data = data;
    }
}
