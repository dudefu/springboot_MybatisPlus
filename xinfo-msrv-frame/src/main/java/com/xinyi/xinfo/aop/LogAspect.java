/**
 * Copyright (c) 2011-2020, hubin (jobob@qq.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.xinyi.xinfo.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xinyi.xinfo.annotations.XLog;

/**
 * <p>
 * 日志切面处理类
 * </p>
 * 
 * @author XIVA
 * @Date 2018-05-09
 */
@Component
@Aspect
public class LogAspect
{

    /**
     * 日志切入点
     */
    @Autowired
    private LogPoint logPoint;

    /**
     * 保存系统操作日志
     *
     * @param joinPoint
     *            连接点
     * @return 方法执行结果
     * @throws Throwable
     *             调用出错
     */
    @Around(value = "@annotation(com.xinyi.xinfo.annotations.XLog)")
    public Object saveLog(ProceedingJoinPoint joinPoint) throws Throwable
    {
        /**
         * 解析Log注解
         */
        String methodName = joinPoint.getSignature().getName();
        Method method = currentMethod(joinPoint, methodName);
        XLog log = method.getAnnotation(XLog.class);
        boolean invokeSuccess = true;
        Exception exp = null;
        Object invokeResult = null;
        try
        {
            invokeResult = joinPoint.proceed();
        }
        catch (Exception e)
        {
            invokeSuccess = false;
            exp = e;
        }

        /**
         * 日志入库
         */
        if (log != null)
        {
            logPoint.saveLog(joinPoint, methodName, log.value());
        }

        if (invokeSuccess)
        {
            throw exp;
        }

        /**
         * 方法执行
         */
        return invokeResult;
    }

    /**
     * 获取当前执行的方法
     *
     * @param joinPoint
     *            连接点
     * @param methodName
     *            方法名称
     * @return 方法
     */
    private Method currentMethod(ProceedingJoinPoint joinPoint, String methodName)
    {
        /**
         * 获取目标类的所有方法，找到当前要执行的方法
         */
        Method[] methods = joinPoint.getTarget().getClass().getMethods();
        Method resultMethod = null;
        for (Method method : methods)
        {
            if (method.getName().equals(methodName))
            {
                resultMethod = method;
                break;
            }
        }
        return resultMethod;
    }

    public LogPoint getLogPoint()
    {
        return logPoint;
    }

    public void setLogPoint(LogPoint logPoint)
    {
        this.logPoint = logPoint;
    }

}
