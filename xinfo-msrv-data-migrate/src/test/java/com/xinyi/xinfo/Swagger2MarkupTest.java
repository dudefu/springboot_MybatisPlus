package com.xinyi.xinfo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import springfox.documentation.staticdocs.SwaggerResultHandler;

@AutoConfigureMockMvc
@AutoConfigureTestEntityManager
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { ApplicationStart.class, SwaggerConfig.class })
public class Swagger2MarkupTest
{
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext wac;
    
    @Test
    public void createSpringfoxSwaggerJson() throws Exception
    {
        String outputDir = "/logs/json/";
        this.mvc =  MockMvcBuilders.webAppContextSetup(wac).build();
        
        this.mvc.perform(get("/v2/api-docs").accept(MediaType.parseMediaType("application/json;charset=UTF-8"))).andDo(print())
                .andDo(SwaggerResultHandler.outputDirectory(outputDir).build()).andExpect(status().isOk()).andReturn();
    }
}
