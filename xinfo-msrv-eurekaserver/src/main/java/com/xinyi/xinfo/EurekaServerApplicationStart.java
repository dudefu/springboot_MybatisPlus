package com.xinyi.xinfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class,SecurityAutoConfiguration.class})
public class EurekaServerApplicationStart
{
    public static void main( String[] args )
    {
        SpringApplication.run(EurekaServerApplicationStart.class, args);
    }
}
