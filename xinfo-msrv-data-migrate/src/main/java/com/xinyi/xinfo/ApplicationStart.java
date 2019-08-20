package com.xinyi.xinfo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@MapperScan(basePackages = {"com.xinyi.xinfo.domain.repository"})
//@ComponentScan(basePackages = { "com.xinyi.xinfo" })
@EnableAspectJAutoProxy(proxyTargetClass = true) // 开启AspectJ代理，并将proxyTargetClass置为true，表示启用cglib对Class也进行代理
@EnableEurekaClient
@EnableFeignClients
public class ApplicationStart
{
    
    public static void main(String[] args)
    {
        SpringApplication.run(ApplicationStart.class, args);
    }
    
}
