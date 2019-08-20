package com.xinyi.xinfo.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import feign.RequestInterceptor;
import feign.RequestTemplate;

@Configuration
public class FeignConfiguration
{

    @Bean
    public RequestInterceptor requestInterceptor()
    {
        return new RequestInterceptor()
        {
            @Override
            public void apply(RequestTemplate template)
            {
                ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

                if (attributes != null)
                {
                    HttpServletRequest request = attributes.getRequest();

                    String userId = request.getHeader("userId");
                    String userName = request.getHeader("userName");
                    String name = request.getHeader("name");
                    String orgCode = request.getHeader("orgCode");
                    String orgName = request.getHeader("orgName");

                    template.header("userId", userId);
                    template.header("userName", userName);
                    template.header("name", name);
                    template.header("orgCode", orgCode);
                    template.header("orgName", orgName);
                }

            }
        };
    }
}
