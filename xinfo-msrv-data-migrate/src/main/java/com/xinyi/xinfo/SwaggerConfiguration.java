package com.xinyi.xinfo;


import static springfox.documentation.builders.PathSelectors.ant;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.google.common.base.Predicates;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
@ComponentScan(basePackages = { "com.xinyi.xinfo" })
public class SwaggerConfiguration {
    
    @Bean
    public Docket restApi() 
    {
        return new Docket(DocumentationType.SWAGGER_2)
                .ignoredParameterTypes(SessionAttribute.class)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.xinyi.xinfo.controller"))
                .paths(Predicates.and(ant("/**"), Predicates.not(ant("/error"))))
                .build();
    }

    private ApiInfo apiInfo() 
    {
        return new ApiInfoBuilder()
                .title("Demo API")
                .description("信义科技，demo API")
                .version("1.0")
                .build();
    }
}
