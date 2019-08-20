package com.xinyi.xinfo.aop;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * <p>
 * 日志切入点接口类
 * </p>
 * 
 * @author XIVA
 * @Date 2016-05-09
 */
public interface LogPoint
{

    /**
     * 日志保存
     * @param joinPoint
     * @param methodName
     * @param operate
     */
    void saveLog(ProceedingJoinPoint joinPoint, String methodName, String operate);
}
