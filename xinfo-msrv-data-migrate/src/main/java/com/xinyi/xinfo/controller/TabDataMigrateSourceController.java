package com.xinyi.xinfo.controller;


import com.xinyi.xinfo.domain.model.DataSource;
import com.xinyi.xinfo.domain.repository.TabDataMigrateSourceMapper;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.xinyi.xinfo.controller.BaseController;

/**
 * <p>
 * 数据源表 前端控制器
 * </p>
 *
 * @author mingshen.wang
 * @since 2019-08-16
 */
@RestController
@RequestMapping("/source1")
public class TabDataMigrateSourceController extends BaseController {

    @Autowired
    TabDataMigrateSourceMapper dataSourceMapper;


    /**
     * 数据源增加接口
     *
     * @param sourceType
     * @param driverClass
     * @param userName
     * @param password
     * @param url
     * @param remarks
     * @return
     */
    @RequestMapping(value = "/addDataSource", method = RequestMethod.POST)
    public String addDataSource(
            @ApiParam(name = "sourceType", value = "数据源类型", required = true) @RequestParam(required = true) String sourceType,
            @ApiParam(name = "driverClass", value = "driver class", required = true) @RequestParam(required = true) String driverClass,
            @ApiParam(name = "userName", value = "连接的数据源用户名", required = true) @RequestParam(required = true) String userName,
            @ApiParam(name = "password", value = "连接的数据源密码", required = true) @RequestParam(required = true) String password,
            @ApiParam(name = "url", value = "连接的数据源URL", required = true) @RequestParam(required = true) String url,
            @ApiParam(name = "userId", value = "持有数据源的用户ID", required = true) @RequestParam(required = true) String userId,
            @ApiParam(name = "remarks", value = "备注", required = false) @RequestParam(required = false) String remarks
    ) {
        System.out.println("a-a-sourceTypess==>  " + sourceType);
        System.out.println("a-driverClass==>  " + driverClass);
        System.out.println("a-userName==>  " + userName);
        System.out.println("a-password==>  " + password);
        System.out.println("a-url==>  " + url);
        System.out.println("a-remarks==>  " + remarks);
        DataSource ds = new DataSource( sourceType, driverClass, userName, password, url, remarks, userId);
        dataSourceMapper.addDataSource(ds);
        return "";
    }
}
