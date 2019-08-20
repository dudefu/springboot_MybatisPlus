package com.xinyi.xinfo.feign.impl;

import org.springframework.stereotype.Component;

import com.xinyi.xinfo.feign.DemoFeign;

@Component
public class DemoFeignFallback implements DemoFeign
{

    @Override
    public String queryById(Integer id)
    {
        return "跨服务调用失败!";
    }

}
