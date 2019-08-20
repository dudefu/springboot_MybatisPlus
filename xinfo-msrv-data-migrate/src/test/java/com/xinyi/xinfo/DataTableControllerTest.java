package com.xinyi.xinfo;

import com.xinyi.xinfo.utils.HttpUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 关于 {@link com.xinyi.xinfo.controller} 中各个方法的测试
 */
public class DataTableControllerTest {
    String url = "http://127.0.0.1:8080";

    /**
     * 获取数据源中的所有表 mysql
     */
    @Test
    public void queryTableList() {
        url = url+"/table/queryTableList";

        Map map = new HashMap<String, String>();
        map.put("sourceId", "22");

        System.out.println(HttpUtil.sendPost(url, map));
        //{"data":{"viewList":["v_collision_metadata","v_collision_metadata_2","v_collision_metadata_3"],"tableList":["collision_metadata","collision_type_info","impact_multi_task","impact_multi_task_result","person","t_fake_plate","t_flume_test","t_vehicle","tab_data_migrate_taskdesction","tb_area","tt","wq"]},"state":{"msg":"OK","state":"10200"}}
    }

    /**
     * 获取数据源中的所有表 oracle
     */
    @Test
    public void queryTableList_oracle() {
        url = url+"/table/queryTableList";

        Map map = new HashMap<String, String>();
        map.put("sourceId", "41");

        System.out.println(HttpUtil.sendPost(url, map));
        //{"data":{"viewList":[],"tableList":["VIEW_WORKSHEET_UPDATE","VIEW_WORKSHEET_SYNC_STRAT","VIEW_WORKSHEET_SOUCE","VIEW_WORKSHEET_MODEL_EQ","VIEW_WORKSHEET_MODEL_CONF","VIEW_WORKSHEET_FUNC_TYPE","VIEW_WORKSHEET_EXPRESSION","VIEW_WORKSHEET_DATA_TYPE","VIEW_WORKSHEET_CONDI_TYPE","VIEW_WORKSHEET_CONDIT_OP","VIEW_WORKSHEET_CONDIT_DO","VIEW_WORKSHEET_CONDITION","VIEW_WORKSHEET_COLMN","VIEW_WORKSHEET_CATALOG","VIEW_WORKSHEET","TEST_DATA_01_copy1","TEST_DATA_01","TEST","TAB_ROOM","TAB_NS_DUTY","TAB_ETL_SYNC_TASK","TAB_ETL_SYNC_LOG","TAB_ETL_SYNC_FIELD","TAB_DATA_TYPE_EXTEND","TAB_DATA_TYPE","TAB_DATA_MIGRATE_TASKDESCTION","TAB_DATA_MIGRATE_TASKCOMPLETE","TAB_DATA_MIGRATE_SOURCE","TAB_DATA_MIGRATE_P_copy1","TAB_DATA_MIGRATE_PROGRESSRATE","TAB_DATAVIEW_CONFIG","TAB_CONTROL_VALUE_TYPE","TAB_CONTROL_VALUE","TAB_CONTROL_TYPE","TAB_CONTROL_TAB_JOIN","TAB_CONTROL_TAB","TAB_CONTROL_PROPERTY_JOIN","TAB_CONTROL_PROPERTY","TAB_CONTROL","MPP_TABLE","MPP_DATATTYPE","MPP_COLUMN","FILES","DB_TABLE","DB_SQL_MODEL","DB_ROW_TO_COLUMN","DB_MAPPING","DB"]},"state":{"msg":"OK","state":"10200"}}
    }


    /**
     * 获取数据源中的所有表 mysql
     */
    @Test
    public void queryTableSchema() {
        url = url+"/table/queryTableSchema";

        Map map = new HashMap<String, String>();
        map.put("sourceId", "22");
        map.put("tableName", "collision_metadata");

        System.out.println(HttpUtil.sendPost(url, map));
        //{"data":{"columns":[{"field":"id","length":"20","type":"bigint","key":"PRI"},{"field":"data_type","length":"32","type":"varchar","key":""},{"field":"storage_type","length":"32","type":"varchar","key":""},{"field":"storage_table","length":"32","type":"varchar","key":""},{"field":"business_column","length":"32","type":"varchar","key":""},{"field":"time_column","length":"32","type":"varchar","key":""},{"field":"device_column","length":"32","type":"varchar","key":""},{"field":"all_columns","length":"256","type":"varchar","key":""}],"tableName":"collision_metadata"},"state":{"msg":"OK","state":"10200"}}
    }


    /**
     * 获取数据源中的所有表 oracle
     *
     */
    @Test
    public void queryTableSchema_oracle() {
        //@com.
        url = url+"/table/queryTableSchema";

        Map map = new HashMap<String, String>();
        map.put("sourceId", "41");
        map.put("tableName", "TAB_DATA_MIGRATE_SOURCE");


        System.out.println(HttpUtil.sendPost(url, map));
        //{"data":{"columns":[{"field":"source_id","length":"22","type":"NUMBER","key":"U"},{"field":"source_type","length":"255","type":"VARCHAR2","key":""},{"field":"driver_class","length":"255","type":"VARCHAR2","key":""},{"field":"user_name","length":"255","type":"VARCHAR2","key":""},{"field":"password","length":"255","type":"VARCHAR2","key":""},{"field":"url","length":"255","type":"VARCHAR2","key":""},{"field":"remarks","length":"255","type":"VARCHAR2","key":""},{"field":"enable","length":"22","type":"NUMBER","key":""},{"field":"user_id","length":"255","type":"VARCHAR2","key":""}],"tableName":"TAB_DATA_MIGRATE_SOURCE"},"state":{"msg":"OK","state":"10200"}}
    }

    /**
     * 获取数据源中的所有表 mysql
     */
    @Test
    public void queryTableDataList() {
        url = url+"/table/queryTableDataList";

        Map map = new HashMap<String, String>();
        map.put("sourceId", "22");
        map.put("tableName", "collision_metadata");
        map.put("columns", "id,data_type,storage_type,storage_table");


        System.out.println(HttpUtil.sendPost(url, map));
    }


    /**
     * 获取数据源中的所有表 oracle
     *
     */
    @Test
    public void queryTableDataList_oracle() {
        //@com.
        url = url+"/table/queryTableDataList";

        Map map = new HashMap<String, String>();
        map.put("sourceId", "41");
        map.put("tableName", "TAB_DATA_MIGRATE_SOURCE");
        map.put("columns", "source_id,source_type,driver_class");


        System.out.println(HttpUtil.sendPost(url, map));
    }





}
