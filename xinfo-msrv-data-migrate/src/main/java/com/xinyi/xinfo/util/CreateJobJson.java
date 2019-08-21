package com.xinyi.xinfo.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xinyi.xinfo.contant.Constant;
import com.xinyi.xinfo.domain.model.ContentSub;
import com.xinyi.xinfo.domain.model.DataSource;
import com.xinyi.xinfo.domain.model.JobJson;
import com.xinyi.xinfo.domain.model.TaskDescription;
import com.xinyi.xinfo.domain.repository.DataSourceMapper;
import com.xinyi.xinfo.domain.repository.TaskDescriptionMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

public class CreateJobJson {

    @Autowired
    static
    DataSourceMapper dataSourceMapper ;
    @Autowired
    static
    TaskDescriptionMapper taskDescriptionMapper;

    public static void main(String[] args) {
        DataSource dataSource = dataSourceMapper.queryDataSourceById("41");
        TaskDescription taskDescription = taskDescriptionMapper.queryTaskDescriptionById(1);
        String createJobJson = createJobJson(dataSource,taskDescription);
        System.out.println(createJobJson);
    }

    // 创建JSONObject对象
    public static String createJobJson(DataSource dataSource,TaskDescription taskDescription) {

        JobJson jobJson = new JobJson();
        Map<String,Object> map = new HashMap<>();
        map.put("setting",getSetting());
        map.put("content",getContent(dataSource,taskDescription));
        jobJson.setJob(map);
        String jobJsonStr = JSON.toJSONString(jobJson);
        return jobJsonStr;
    }

    public static JSONArray getContent( DataSource dataSource,TaskDescription taskDescription){
        JSONArray content = new JSONArray();
        ContentSub contentSub = new ContentSub();
        contentSub.setReader(getReader(dataSource,taskDescription));
        contentSub.setWriter(getWriter(taskDescription ));
        content.add(0,contentSub);
        return  content ;
    }

    public static JSONObject getSetting(){
        JSONObject setting = new JSONObject();
        JSONObject speedContent = new JSONObject();
        speedContent.put("channel",1);
        setting.put("speed", speedContent);
        return setting ;
    }

    public static Map<String,Object> getReader(DataSource dataSource,TaskDescription taskDescription){
        Map<String,Object> reader = new HashMap<>();
        Map<String,Object> parameter = new HashMap<>();
        parameter.put("username",dataSource.getUserName());
        parameter.put("password",dataSource.getPassword());
        JSONArray column = new JSONArray();
        column.add(0,taskDescription.getTdColumns());
        parameter.put("column",column);
        JSONArray connection = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        JSONArray table = new JSONArray();
        table.add(0,taskDescription.getTdTableName());
        JSONArray jdbcUrl = new JSONArray();
        jdbcUrl.add(0,dataSource.getUrl());
        jsonObject.put("table",table);
        jsonObject.put("jdbcUrl",jdbcUrl);
        connection.add(0,jsonObject);
        parameter.put("connection",connection);
        String sourceId = dataSource.getSourceId().toString();
        String mysqlSourceId = Constant.MYSQL_SOURCEID;
        String oracleSouceId = Constant.ORACLE_SOURCEID;
        String postgresqlSourceId = Constant.POSTG_SOURCEID;
        if(mysqlSourceId.equals(sourceId)){
            reader.put("name",Constant.MYSQLREADER_NAME);
        }else if(oracleSouceId.equals(sourceId)){
            reader.put("name",Constant.ORACLEREADER_NAME);
        }else if(postgresqlSourceId.equals(sourceId)){
            reader.put("name",Constant.POSTGRESQLREADER_NAME);
        }
        reader.put("parameter",parameter);
        return reader ;
    }

    public static Map<String,Object> getWriter(TaskDescription taskDescription){
        Map<String,Object> writer = new HashMap<>();
        Map<String,Object> parameter = new HashMap<>();
        parameter.put("username",Constant.GP_USERNAME);
        parameter.put("password",Constant.GP_PASSWORD);
        parameter.put("segment_reject_limit",Constant.segment_reject_limit);
        parameter.put("copy_queue_size",Constant.copy_queue_size);
        parameter.put("num_copy_processor",Constant.num_copy_processor);
        parameter.put("num_copy_writer",Constant.num_copy_writer);
        JSONArray column = new JSONArray();
        column.add(0,taskDescription.getTdColumns());
        parameter.put("column",column);
        JSONArray preSql = new JSONArray();
        preSql.add(0,"truncate table "+taskDescription.getTdTargetTableName());
        parameter.put("preSql",preSql);
        JSONArray connection = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        JSONArray table = new JSONArray();
        table.add(0,taskDescription.getTdTargetTableName());
        jsonObject.put("table",table);
        jsonObject.put("jdbcUrl",Constant.GP_URL);
        connection.add(0,jsonObject);
        parameter.put("connection",connection);
        writer.put("name",Constant.GPWRITER_NAME);
        writer.put("parameter",parameter);
        return writer ;
    }
}
