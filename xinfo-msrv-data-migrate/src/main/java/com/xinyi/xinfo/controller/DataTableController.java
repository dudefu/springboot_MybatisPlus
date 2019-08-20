package com.xinyi.xinfo.controller;


import com.xinyi.xinfo.domain.service.DataTableService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Api(value = "底层数据处理，数据源中表相关接口", description = "底层数据处理，数据源中表相关接口")
@RequestMapping("/table")
@RestController
public class DataTableController {

    @Autowired
    private DataTableService dataTableService;

    /**
     * 查询数据源中的所有表接口
     *
     * @param sourceId 数据源ID
     * @return
     */
    @RequestMapping(value = "/queryTableList", method = RequestMethod.POST)
    public String queryTableList(@ApiParam(name = "sourceId", value = "数据源ID", required = true) @RequestParam(required = true) String sourceId) {
        return dataTableService.queryTableList(sourceId);
    }

    /**
     * 查询表结构接口
     *
     * @param sourceId  数据源ID
     * @param tableName 表名
     * @return
     */
    @RequestMapping(value = "/queryTableSchema", method = RequestMethod.POST)
    public String queryTableSchema(
            @ApiParam(name = "sourceId", value = "数据源ID", required = true) @RequestParam(required = true) String sourceId,
            @ApiParam(name = "tableName", value = "数据表名称", required = true) @RequestParam(required = true) String tableName
    ) {
        return dataTableService.queryTableSchema(sourceId, tableName);
    }


    /**
     * 接入数据预览接口
     *
     * @param sourceId  数据源ID
     * @param tableName 表名
     * @param columns   查询字段
     * @return
     */
    @RequestMapping(value = "/queryTableDataList", method = RequestMethod.POST)
    public String queryTableDataList(
            @ApiParam(name = "sourceId", value = "数据源ID", required = true) @RequestParam(required = true) String sourceId,
            @ApiParam(name = "tableName", value = "数据表名称", required = true) @RequestParam(required = true) String tableName,
            @ApiParam(name = "columns", value = "要查询的列名，多个用逗号分隔", required = true) @RequestParam(required = true) String columns
    ) {
        return dataTableService.queryTableDataList(sourceId, tableName, columns);
    }
}
