/**
 * 
 */
package com.xinyi.xinfo.exception;

/**
 * 
 * 功能说明：通用组件异常
 * 
 * BasicAppException.java
 * 
 * Original Author: 方文荣,2014年12月3日
 *
 * Copyright (C)1997-2014 深圳信义科技 All rights reserved.
 */
public class BasicAppException extends AppException {

    private static final long serialVersionUID = -3632238422563829997L;

    public BasicAppException(String message, Throwable cause) {
        super(message, cause);
    }

    public BasicAppException(String message) {
        super(message);
    }

    public BasicAppException(Throwable cause) {
        super(cause);
    }
    
    
    
    

    

}
