package com.xinyi.xinfo.domain.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.xinyi.xinfo.bean.Status;
import com.xinyi.xinfo.domain.dao.RdbmsDao;
import com.xinyi.xinfo.domain.model.DataSource;
import com.xinyi.xinfo.domain.repository.DataSourceMapper;
import com.xinyi.xinfo.domain.service.DataTableService;
import com.xinyi.xinfo.util.DBUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataTableServiceImpl implements DataTableService {
    private RdbmsDao rdbmsDao;

    @Autowired
    private DataSourceMapper dataSourceMapper;

    private JSONObject jsonObject;

    @Override
    public String queryTableList(String sourceId) {
        jsonObject = new JSONObject();
        try {
            DataSource ds = dataSourceMapper.queryDataSourceById(sourceId);
            rdbmsDao = DBUtils.RDBMSServiceFactory(ds.getDriverClass());
            jsonObject.put("state", new Status(Status.stateEnmu.SUCCESS.code, Status.stateEnmu.SUCCESS.msg));
            jsonObject.put("data", rdbmsDao.queryTableList(ds));
        } catch (Exception e) {
            e.printStackTrace();
            jsonObject.put("state", new Status(Status.stateEnmu.FAILURE.code, e.getMessage()));
        }
        return jsonObject.toJSONString();
    }

    @Override
    public String queryTableSchema(String sourceId, String tableName) {
        jsonObject = new JSONObject();
        try {
            DataSource ds = dataSourceMapper.queryDataSourceById(sourceId);
            rdbmsDao = DBUtils.RDBMSServiceFactory(ds.getDriverClass());
            jsonObject.put("state", new Status(Status.stateEnmu.SUCCESS.code, Status.stateEnmu.SUCCESS.msg));
            jsonObject.put("data", rdbmsDao.queryTableSchema(ds, tableName));
        } catch (Exception e) {
            e.printStackTrace();
            jsonObject.put("state", new Status(Status.stateEnmu.FAILURE.code, e.getMessage()));
        }
        return jsonObject.toJSONString();

    }

    @Override
    public String queryTableDataList(String sourceId, String  tableName,String columns) {
        jsonObject = new JSONObject();
        try {
            DataSource ds = dataSourceMapper.queryDataSourceById(sourceId);
            rdbmsDao = DBUtils.RDBMSServiceFactory(ds.getDriverClass());
            jsonObject.put("state", new Status(Status.stateEnmu.SUCCESS.code, Status.stateEnmu.SUCCESS.msg));
            jsonObject.put("data", rdbmsDao.queryTableDataList(ds,tableName,columns));

        } catch (Exception e) {
            e.printStackTrace();
            jsonObject.put("state", new Status(Status.stateEnmu.FAILURE.code, e.getMessage()));
        }
        return jsonObject.toJSONString();
    }
}
