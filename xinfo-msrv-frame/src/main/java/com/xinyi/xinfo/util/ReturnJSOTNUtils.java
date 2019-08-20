package com.xinyi.xinfo.util;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xinyi.xinfo.constants.CodeMsg;
import com.xinyi.xinfo.domain.model.ReturnJSONT;

import io.netty.util.internal.StringUtil;

@Component
public class ReturnJSOTNUtils
{
    @Autowired
    private HttpServletRequest request;

    private static ReturnJSOTNUtils ReturnJSONTUtils;

    @PostConstruct
    public void init()
    {
        ReturnJSONTUtils = this;
        ReturnJSONTUtils.request = this.request;
    }

    /**
     * 设置接口返回的JSON值
     *
     * @param data
     *            返回数据
     * @param msg          
     *            返回信息
     * @param code
     *            错误码
     */
    public static <T> ReturnJSONT<T> setReturnJSONT(T data,String msg, Integer... code)
    {
		ReturnJSONT<T> json = new ReturnJSONT<T>();
		json.setCmd(ReturnJSONTUtils.request.getRequestURI());
		int codeId = CodeMsg.C404;
		if (code != null && code.length > 0) {
			codeId = code[0];
			json.setCode(codeId);
		}
		if(StringUtil.isNullOrEmpty(msg))
		{
			String codeMsg = "C" + codeId + "_MSG";
			String message;
			try {
				message = (String) CodeMsg.class.getField(codeMsg).get(new CodeMsg());
			} catch (Exception e) {
				message = null;
			}
			json.setMsg(message);
		}
		else
		{
			json.setMsg(msg);
		}
		if (data != null) {
			json.setData(data);
		}
		return json;
    }
    
   
    
    
    public static String getCmd()
    {
		return ReturnJSONTUtils.request.getRequestURI();   	
    }
}
