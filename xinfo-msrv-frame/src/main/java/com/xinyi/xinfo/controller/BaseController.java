package com.xinyi.xinfo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xinyi.xinfo.domain.model.UserInfo;

public class BaseController extends SuperController implements HandlerInterceptor
{

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception
    {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception
    {
    }

    /**
     * 判断是否为合法的视图地址
     * <p>
     * 
     * @param modelAndView
     *            spring 视图对象
     * @return boolean
     */
    protected boolean isLegalView(ModelAndView modelAndView)
    {
        boolean legal = false;
        if (modelAndView != null)
        {
            String viewUrl = modelAndView.getViewName();
            if (viewUrl != null && viewUrl.contains("redirect:"))
            {
                legal = false;
            }
            else
            {
                legal = true;
            }
        }
        return legal;
    }

    /**
     * <p>
     * 转换为 bootstrap-table 需要的分页格式 JSON
     * </p>
     * 
     * @param page
     *            分页对象
     * @return
     */
    protected String jsonPage(Page<?> page)
    {
        JSONObject jo = new JSONObject();
        jo.put("total", page.getTotal());
        jo.put("rows", page.getRecords());
        return toJson(jo);
    }

    @Override
    protected <T> Page<T> getPage(int size)
    {
        int _size = size, _index = 1;
        if (request.getParameter("_size") != null)
        {
            _size = Integer.parseInt(request.getParameter("_size"));
        }
        if (request.getParameter("_index") != null)
        {
            int _offset = Integer.parseInt(request.getParameter("_index"));
            _index = _offset / _size + 1;
        }
        return new Page<T>(_index, _size);
    }

    protected String booleanToString(boolean rlt)
    {
        return rlt ? "true" : "false";
    }

    /**
     * 获取当前登录用户基本信息
     * 
     * @return
     */
    protected UserInfo getUserInfo()
    {

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        UserInfo userInfo = null;

        if (attributes != null)
        {
            HttpServletRequest request = attributes.getRequest();

            if (request != null)
            {
                String userId = request.getHeader("userId");
                String userName = request.getHeader("userName");
                String name = request.getHeader("name");
                String orgCode = request.getHeader("orgCode");
                String orgName = request.getHeader("orgName");

                userInfo = new UserInfo();
                userInfo.setUserId(userId);
                userInfo.setUserName(userName);
                userInfo.setName(name);
                userInfo.setOrgCode(orgCode);
                userInfo.setOrgName(orgName);

            }
        }

        return userInfo;
    }

    /**
     * 获取当前登录用户所登录的应用id
     * 
     * @return
     */
    protected String getAdhibitionId()
    {

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        String clientId = null;

        if (attributes != null)
        {
            HttpServletRequest request = attributes.getRequest();

            if (request != null)
            {
                clientId = request.getHeader("clientId");
            }
        }

        return clientId;
    }

}
