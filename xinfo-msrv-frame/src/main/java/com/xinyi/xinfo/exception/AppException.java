/**
 * 
 */
package com.xinyi.xinfo.exception;

/**
 * 
 * 功能说明：应用异常处理类(系统所有自定义的异常都是继承它)
 * 
 * AppException.java
 * 
 * Original Author: 方文荣,2014年12月3日
 *
 * Copyright (C)1997-2014 深圳信义科技 All rights reserved.
 */
public class AppException extends RuntimeException {
    
    /**
     * 
     */
    private static final long serialVersionUID = 5026463863450374456L;
    
    private String errorCode;
    
    
    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * 构造方法。
     * @param message 异常信息
     */
    public AppException(String message) {
        super(message);
    }
    
    
    /**
     * 构造方法
     * @param code 错误码
     * @param message 错误提示信息
     */
    public AppException(String code,String message){
        super(message);
        this.errorCode = code;
    }

    /**
     * 构造方法。
     * @param cause  异常原因
     */
    public AppException(Throwable cause) {
        super(cause);
    }
    
    /**
     * 构造方法
     * @param code 错误码
     * @param message 错误提示信息
     * @param cause  异常原因
     */
    public AppException(String code,String message,Throwable cause){
        super(message, cause);
        this.errorCode = code;
    }

    /**
     * 构造方法。
     * @param message  异常信息
     * @param cause 异常原因
     */
    public AppException(String message, Throwable cause) {
        super(message, cause);
    }

}
