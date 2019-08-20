package com.xinyi.xinfo.controller;

import com.xinyi.xinfo.domain.service.DataSourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "底层数据处理，数据源相关接口", description = "底层数据处理，数据源相关接口")
@RequestMapping("/source")
@RestController
public class DataSourceController extends BaseController {
    @Autowired
    private DataSourceService dataSourceService;

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
        String result = dataSourceService.addDataSource(sourceType, driverClass, userName, password, url, remarks, userId);
        return result;
    }

    /**
     * 测试配置的数据源是否可用
     *
     * @param driverClass
     * @param userName
     * @param password
     * @param url
     * @return
     */
    @RequestMapping(value = "/connectionTestDataSource", method = RequestMethod.POST)
    public String connectionTestDataSource(
            @ApiParam(name = "driverClass", value = "driver class", required = true) @RequestParam(required = true) String driverClass,
            @ApiParam(name = "userName", value = "连接的数据源用户名", required = true) @RequestParam(required = true) String userName,
            @ApiParam(name = "password", value = "连接的数据源密码", required = true) @RequestParam(required = true) String password,
            @ApiParam(name = "url", value = "连接的数据源URL", required = true) @RequestParam(required = true) String url
    ) {
        
        String result = dataSourceService.connectionTestDataSource(driverClass, userName, password, url);
        return result;
    }

    /**
     * 修改数据源接口
     *
     * @param sourceId
     * @param sourceType
     * @param driverClass
     * @param userName
     * @param password
     * @param url
     * @param remarks
     * @return
     */
    @RequestMapping(value = "/updateDataSourceById", method = RequestMethod.POST)
    public String updateDataSourceById(
            @ApiParam(name = "sourceId", value = "数据源ID", required = true) @RequestParam(required = true) String sourceId,
            @ApiParam(name = "sourceType", value = "数据源类型", required = true) @RequestParam(required = true) String sourceType,
            @ApiParam(name = "driverClass", value = "driver class", required = true) @RequestParam(required = true) String driverClass,
            @ApiParam(name = "userName", value = "连接的数据源用户名", required = true) @RequestParam(required = true) String userName,
            @ApiParam(name = "password", value = "连接的数据源密码", required = true) @RequestParam(required = true) String password,
            @ApiParam(name = "url", value = "连接的数据源URL", required = true) @RequestParam(required = true) String url,
            @ApiParam(name = "remarks", value = "备注", required = false) @RequestParam(required = false) String remarks
    ) {
        return dataSourceService.updateDataSourceById(sourceId, sourceType, driverClass, userName, password, url, remarks);
    }


    /**
     * 通过 sourceId 获取数据源详细信息
     *
     * @param sourceId
     * @return
     */
    @RequestMapping(value = "/queryDataSourceById", method = RequestMethod.POST)
    public String queryDataSourceById(@ApiParam(name = "sourceId", value = "数据源ID", required = true) @RequestParam(required = true) String sourceId) {

        return dataSourceService.queryDataSourceById(sourceId);
    }


    /**
     * 通过userId 查询该用户下面配置所有的数据源
     *
     * @param userId
     * @return
     */
    @RequestMapping(value = "/queryDataSourceList", method = RequestMethod.POST)
    public String queryDataSourceList(@ApiParam(name = "userId", value = "数据源ID", required = true) @RequestParam(required = true) String  userId) {

        return dataSourceService.queryDataSourceList(userId);
    }


    /**
     * 删除（停用）数据源
     *
     * @param sourceId
     * @return
     */
    @RequestMapping(value = "/disableDataSourceById", method = RequestMethod.POST)
    public String disableDataSourceById(@ApiParam(name = "sourceId", value = "数据源ID", required = true) @RequestParam(required = true) String sourceId) {

        return dataSourceService.disableDataSourceById(sourceId);
    }

    /**
     * 启用数据源
     *
     * @param sourceId
     * @return
     */
    @RequestMapping(value = "/enableDataSourceById", method = RequestMethod.POST)
    public String enableDataSourceById(@ApiParam(name = "sourceId", value = "数据源ID", required = true) @RequestParam(required = true) String sourceId) {

        return dataSourceService.enableDataSourceById(sourceId);
    }


}
