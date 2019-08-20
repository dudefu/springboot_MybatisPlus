package com.xinyi.xinfo.aop;

import java.lang.reflect.Method;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import io.swagger.annotations.ApiOperation;

@Aspect
@Configuration
public class ControllerInterceptor
{

    /**
     * 定义拦截规则：拦截com.xinyi.xinfo.controller包下面的所有类中，有@ApiOperation注解的方法。
     */
    @Pointcut("execution(* com.xinyi.xinfo.controller..*.*(..))  && @annotation(io.swagger.annotations.ApiOperation)")
    public void controllerMethodPointcut()
    {

    }

    /**
     * 拦截器具体实现
     * 
     * @param pjp
     * @return JsonResult（被拦截方法的执行结果，或需要登录的错误提示。）
     * @throws Throwable
     */
    @Around("controllerMethodPointcut()") // 指定拦截器规则；也可以直接把“execution(*
                                          // com.xinyi.........)”写进这里
    public Object interceptor(ProceedingJoinPoint thisJoinPoint) throws Throwable
    {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;

        String description = getControllerMethodDescription(thisJoinPoint);

        if (sra != null)
        {
            HttpServletResponse response = sra.getResponse();
            
            if (response != null)
            {
                response.addHeader("operateName", URLEncoder.encode(description, "UTF-8"));
            }
            
        }

        // result的值就是被拦截方法的返回值
        Object result = null;
        try
        {
            result = thisJoinPoint.proceed();
        }
        catch (Throwable e)
        {
            e.printStackTrace();
            throw e;
        }
        return result;
    }

    /**
     * @param joinPoint
     *            切点
     * @return 方法描述
     * @throws Exception
     */
    private String getControllerMethodDescription(JoinPoint joinPoint) throws Exception
    {
        String result = null;

        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Class<?> targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();

        for (Method method : methods)
        {
            if (method.getName().equals(methodName))
            {
                ApiOperation annotation = method.getAnnotation(ApiOperation.class);

                result = annotation.value();
                result = (result == null ? annotation.nickname() : result);
                result = (result == null ? annotation.notes() : result);
            }
        }
        return result;
    }

}
