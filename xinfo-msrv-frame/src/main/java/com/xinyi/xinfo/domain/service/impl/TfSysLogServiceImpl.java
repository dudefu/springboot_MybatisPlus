package com.xinyi.xinfo.domain.service.impl;

import java.util.Date;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Service;

import com.xinyi.xinfo.aop.LogPoint;
import com.xinyi.xinfo.domain.model.TfSysLog;
import com.xinyi.xinfo.domain.repository.TfSysLogMapper;
import com.xinyi.xinfo.domain.service.ITfSysLogService;
import com.xinyi.xinfo.domain.service.support.BaseServiceImpl;
import com.xinyi.xinfo.util.HttpHelper;
import com.xinyi.xinfo.util.IpHelper;

/**
 * <p>
 * 操作日志表 服务实现类
 * </p>
 *
 * @author xiva
 * @since 2018-07-06
 */
@Service(value="logPoint")
public class TfSysLogServiceImpl extends BaseServiceImpl<TfSysLogMapper, TfSysLog> implements ITfSysLogService, LogPoint
{

    private static final String LOG_CONTENT = "[类名]:%s,[方法]:%s,[参数]:%s,[IP]:%s";

    @Override
    public void saveLog(ProceedingJoinPoint joinPoint, String methodName, String operate)
    {
        /**
         * 日志入库
         */
        HttpServletRequest request = HttpHelper.getHttpServletRequest();
        TfSysLog sl = new TfSysLog();
        /*
         * Token tk = SSOHelper.attrToken(request); if ( tk != null ) { sl.setUid(tk.getId()); }
         */
        sl.setContent(operateContent(joinPoint, methodName, request));
        sl.setOperation(operate);
        sl.setCrTime(new Date());
        baseMapper.insert(sl);
    }

    /**
     * 获取当前执行的方法
     *
     * @param joinPoint
     *            连接点
     * @param methodName
     *            方法名称
     * @return 操作内容
     */
    public String operateContent(ProceedingJoinPoint joinPoint, String methodName, HttpServletRequest request)
    {
        String className = joinPoint.getTarget().getClass().getName();
        Object[] params = joinPoint.getArgs();
        StringBuffer bf = new StringBuffer();
        if (params != null && params.length > 0)
        {
            Enumeration<String> paraNames = request.getParameterNames();
            while (paraNames.hasMoreElements())
            {
                String key = paraNames.nextElement();
                bf.append(key).append("=");
                bf.append(request.getParameter(key)).append("&");
            }
            if (StringUtils.isBlank(bf.toString()))
            {
                bf.append(request.getQueryString());
            }
        }
        return String.format(LOG_CONTENT, className, methodName, bf.toString(), IpHelper.getIpAddr(request));
    }
}
