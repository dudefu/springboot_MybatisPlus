package com.xinyi.xinfo.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class HttpHelper
{

    /**
     * 获取 HttpServletRequest
     */
    public static HttpServletRequest getHttpServletRequest()
    {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        if (attributes != null)
        {
            return attributes.getRequest();
        }
        else
        {
            return null;
        }

    }

}
