package com.xinyi.xinfo.util;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xinyi.xinfo.constants.CodeMsg;
import com.xinyi.xinfo.domain.model.ReturnJSON;

@Component
public class ReturnJSONUtils
{
    @Autowired
    private HttpServletRequest request;

    private static ReturnJSONUtils returnJSONUtils;

    @PostConstruct
    public void init()
    {
        returnJSONUtils = this;
        returnJSONUtils.request = this.request;
    }

    /**
     * 设置接口返回的JSON值
     *
     * @param data
     *            返回数据
     * @param code
     *            错误码
     */
    public static ReturnJSON setReturnJson(Object data, Integer... code)
    {
        ReturnJSON json = new ReturnJSON();
        json.setCmd(returnJSONUtils.request.getRequestURI());
        int codeId = CodeMsg.C404;
        if (code != null && code.length > 0)
        {
            codeId = code[0];
        }
        json.setCode(codeId);
        if (data != null)
        {
            json.setData(data);
            return json;
        }
        String codeMsg = "C" + codeId + "_MSG";
        String message;
        try
        {
            message = (String) CodeMsg.class.getField(codeMsg).get(new CodeMsg());
        }
        catch (Exception e)
        {
            message = null;
        }
        json.setData(message);
        return json;
    }
    
    public static String getCmd()
    {
		return returnJSONUtils.request.getRequestURI();   	
    }
}
