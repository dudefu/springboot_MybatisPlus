package com.xinyi.xinfo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;

import feign.Request;

//Feign的配置默认不能是Spring boot 的 Component，自定义Feign的配置
@Configuration
public class XFeignSrvConfig
{
    /**
     * 解决服务间调用超时问题 
     * 参照 https://stackoverflow.com/questions/38080283/how-to-solve-timeout-feignclient
     * @param env
     * @return
     */
    @Bean
    public static Request.Options requestOptions(ConfigurableEnvironment env)
    {
        int ribbonReadTimeout = env.getProperty("ribbon.ReadTimeout", int.class, 70000);
        int ribbonConnectionTimeout = env.getProperty("ribbon.ConnectTimeout", int.class, 60000);

        return new Request.Options(ribbonConnectionTimeout, ribbonReadTimeout);
    }
}
