package com.xinyi.xinfo.feign;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

//@FeignClient(value = "Remote Server Name", fallback = DemoFeignFallback.class)
public interface DemoFeign
{
    @RequestMapping(value = "/demo/queryById", method = RequestMethod.POST)
    public String queryById(@RequestParam("id") Integer id);
}
