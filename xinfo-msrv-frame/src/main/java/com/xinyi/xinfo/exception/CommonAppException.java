/**
 * 
 */
package com.xinyi.xinfo.exception;

/**
 * 
 * 功能说明：通用业务统一异常类
 * 
 * CommonAppException.java
 * 
 * Original Author: 方文荣,2014年12月3日
 *
 * Copyright (C)1997-2014 深圳信义科技 All rights reserved.
 */
public class CommonAppException extends AppException
{

    /**
     * 
     */
    private static final long serialVersionUID = 8697513410860758642L;

    public CommonAppException(String message)
    {
        super(message);
    }

    public CommonAppException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public CommonAppException(Throwable cause)
    {
        super(cause);
    }

}
