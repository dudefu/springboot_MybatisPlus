package com.xinyi.xinfo.domain.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.xinyi.xinfo.bean.Status;
import com.xinyi.xinfo.domain.model.DataSource;
import com.xinyi.xinfo.domain.repository.DataSourceMapper;
import com.xinyi.xinfo.domain.service.DataSourceService;
import com.xinyi.xinfo.util.DBUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class DataSourceServiceImpl implements DataSourceService {

    @Autowired
    private DataSourceMapper dataSourceMapper;

    private JSONObject jsonObject;

    @Override
    public String addDataSource(String sourceType, String driverClass, String userName, String password, String url, String remarks, String userId) {


        jsonObject = new JSONObject();
        Boolean b = false;

        try {

            try{
                b = DBUtils.testConnection(driverClass, userName, password, url);
            }catch (Exception e){
                e.printStackTrace();
                jsonObject.put("state", new Status(Status.stateEnmu.FAILURE.code, "数据源验证连接失败，请检查数据源配置信息:"+e.getMessage()));
            }

            if(b){ //连接成功
                DataSource ds = new DataSource(sourceType, driverClass, userName, password, url, remarks, userId);
                dataSourceMapper.addDataSource(ds);
                jsonObject.put("state", new Status(Status.stateEnmu.SUCCESS.code, Status.stateEnmu.SUCCESS.msg));
            }else{//连接失败
                jsonObject.put("state", new Status(Status.stateEnmu.FAILURE.code, "数据源验证连接失败，请检查数据源配置信息"));
            }


        } catch (Exception e) {
            e.printStackTrace();
            jsonObject.put("state", new Status(Status.stateEnmu.FAILURE.code, e.getMessage()));
        }
        return jsonObject.toJSONString();
    }

    @Override
    public String connectionTestDataSource(String driverClass, String userName, String password, String url) {
        JSONObject jsonObject = new JSONObject();

        try{
            Boolean b = DBUtils.testConnection(driverClass, userName, password, url);
            if (b) { //连接成功
                jsonObject.put("state", new Status(Status.stateEnmu.SUCCESS.code, Status.stateEnmu.SUCCESS.msg));
            } else {//连接失败
                jsonObject.put("state", new Status(Status.stateEnmu.FAILURE.code, "请确认配置信息是否正确"));
            }

        }catch (Exception e){
            e.printStackTrace();
            jsonObject.put("state", new Status(Status.stateEnmu.FAILURE.code, e.getMessage()));
        }

        return jsonObject.toJSONString();
    }


    @Override
    public String disableDataSourceById(String sourceId) {
        jsonObject = new JSONObject();
        try{
            int re = dataSourceMapper.disableDataSourceById(sourceId);
            jsonObject.put("state",new Status(Status.stateEnmu.SUCCESS.code,Status.stateEnmu.SUCCESS.msg));
            JSONObject jb = new JSONObject();
            jb.put("count",re);
            jsonObject.put("data",jb);
        }catch (Exception e){
            e.printStackTrace();
            jsonObject.put("state",new Status(Status.stateEnmu.FAILURE.code,e.getMessage()));
        }
        return jsonObject.toJSONString();
    }

    @Override
    public String enableDataSourceById(String sourceId) {
        jsonObject = new JSONObject();
        try{


            int re = dataSourceMapper.enableDataSourceById(sourceId);
            jsonObject.put("state",new Status(Status.stateEnmu.SUCCESS.code,Status.stateEnmu.SUCCESS.msg));
            JSONObject jb = new JSONObject();
            jb.put("count",re);
            jsonObject.put("data",jb);
        }catch (Exception e){
            e.printStackTrace();
            jsonObject.put("state",new Status(Status.stateEnmu.FAILURE.code,e.getMessage()));
        }
        return jsonObject.toJSONString();
    }

    @Override
    public String queryDataSourceList(String userId) {
        jsonObject = new JSONObject();
        try{
            jsonObject.put("state",new Status(Status.stateEnmu.SUCCESS.code,Status.stateEnmu.SUCCESS.msg));
            List<DataSource> list =  dataSourceMapper.queryDataSourceList(userId);
            jsonObject.put("data",list);
        }catch (Exception e){
            e.printStackTrace();
            jsonObject.put("state",new Status(Status.stateEnmu.FAILURE.code,e.getMessage()));
        }
        return jsonObject.toJSONString();
    }

    @Override
    public String queryDataSourceById(String sourceId) {
        jsonObject = new JSONObject();
        try{
            jsonObject.put("state",new Status(Status.stateEnmu.SUCCESS.code,Status.stateEnmu.SUCCESS.msg));
            DataSource ds =  dataSourceMapper.queryDataSourceById(sourceId);
            jsonObject.put("data",ds);
        }catch (Exception e){
            e.printStackTrace();
            jsonObject.put("state",new Status(Status.stateEnmu.FAILURE.code,e.getMessage()));
        }
        return jsonObject.toJSONString();
    }

    @Override
    public String updateDataSourceById(String sourceId, String sourceType, String driverClass, String userName, String password, String url, String remarks) {
        jsonObject = new JSONObject();
        try{

            Boolean b =false;
            try{
                b = DBUtils.testConnection(driverClass, userName, password, url);
            }catch (Exception e){
                e.printStackTrace();
                jsonObject.put("state", new Status(Status.stateEnmu.FAILURE.code, "数据源验证连接失败，请检查数据源配置信息:"+e.getMessage()));
            }

            if(b){
                DataSource  ds = new DataSource( new BigDecimal(sourceId),sourceType, driverClass, userName, password, url, remarks);

                int re = dataSourceMapper.updateDataSourceById(ds);
                jsonObject.put("state",new Status(Status.stateEnmu.SUCCESS.code,Status.stateEnmu.SUCCESS.msg));
                JSONObject jb = new JSONObject();
                jb.put("count",re);
                jsonObject.put("data",jb);
            }else{
                jsonObject.put("state", new Status(Status.stateEnmu.FAILURE.code, "数据源验证连接失败，请检查数据源配置信息"));
            }


        }catch (Exception e){
            e.printStackTrace();
            jsonObject.put("state",new Status(Status.stateEnmu.FAILURE.code,e.getMessage()));
        }
        return jsonObject.toJSONString();
    }


    @Override
    public DataSource getDataSourceById() {
        return null;
    }


}
