package com.xinyi.xinfo.domain.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.xinyi.xinfo.bean.Status;
import com.xinyi.xinfo.domain.model.DataSource;
import com.xinyi.xinfo.domain.repository.DataSourceMapper;
import com.xinyi.xinfo.domain.service.DataSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataSourceServiceImpl implements DataSourceService {

    @Autowired
    private DataSourceMapper dataSourceMapper;

    private JSONObject jsonObject;
    @Override
    public String addDataSource(String sourceType, String driverClass, String userName, String password, String url, String remarks, String userId) {

        System.out.println("s e r v i c e- - - - - - ");
        System.out.println("s-sourceTypess==>  "+sourceType);
        System.out.println("s-driverClass==>  "+driverClass);
        System.out.println("s-userName==>  "+userName);
        System.out.println("s-password==>  "+password);
        System.out.println("s-url==>  "+url);
        System.out.println("s-remarks==>  "+remarks);
        /*DataSource  ds = new DataSource( sourceType, driverClass, userName, password, url, remarks, userId);
        dataSourceMapper.addDataSource(ds);*/

        jsonObject = new JSONObject();
        try{
            DataSource  ds = new DataSource( sourceType, driverClass, userName, password, url, remarks, userId);
            dataSourceMapper.addDataSource(ds);
            jsonObject.put("state",new Status(Status.stateEnmu.SUCCESS.code,Status.stateEnmu.SUCCESS.msg));
        }catch (Exception e){
            e.printStackTrace();
            jsonObject.put("state",new Status(Status.stateEnmu.FAILURE.code,e.getMessage()));
        }
        return jsonObject.toJSONString();
    }


    @Override
    public String disableDataSourceById(String sourceId) {
        return null;
    }

    @Override
    public String enableDataSourceById(String sourceId) {
        return null;
    }

    @Override
    public String queryDataSourceList(String userId) {
        return null;
    }

    @Override
    public String queryDataSourceById(String sourceId) {
        return null;
    }

    @Override
    public String updateDataSourceById(String sourceId, String sourceType, String driverClass, String userName, String password, String url, String remarks) {
        return null;
    }



    @Override
    public DataSource getDataSourceById() {
        return null;
    }

    @Override
    public String connectionTestDataSource(String driverClass, String userName, String password, String url) {
        return null;
    }
}
