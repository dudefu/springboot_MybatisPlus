package com.xinyi.xinfo.domain.dao.impl;

import com.xinyi.xinfo.domain.dao.RdbmsDao;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xinyi.xinfo.domain.model.DataSource;
import com.xinyi.xinfo.util.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MySQLDaoImpl implements RdbmsDao {
    private PreparedStatement pst;
    private ResultSet rs;
    private final String table_sql = "show table status where comment!='view'";
    private final String view_sql = "show table status where comment='view'";

    @Override
    public JSONObject queryTableList(DataSource ds) throws Exception {
        JSONObject jsonObject = new JSONObject();
        Connection conn = DBUtils.getConnection(ds.getDriverClass(),ds.getUserName(),ds.getPassword(),ds.getUrl());

        pst = conn.prepareStatement(table_sql);
        rs = pst.executeQuery();

        List<String> tableList = new ArrayList();
        while (rs.next()){
            tableList.add(rs.getString(1));
        }
        jsonObject.put("tableList",tableList);

        pst = conn.prepareStatement(view_sql);
        rs = pst.executeQuery();

        List<String> viewList = new ArrayList();
        while (rs.next()){
            viewList.add(rs.getString(1));
        }
        jsonObject.put("viewList",viewList);

        return jsonObject;
    }

    @Override
    public JSONObject queryTableSchema(DataSource ds, String tableName)throws Exception {
        JSONObject jsonObject = new JSONObject();
        Connection conn = DBUtils.getConnection(ds.getDriverClass(),ds.getUserName(),ds.getPassword(),ds.getUrl());
        pst = conn.prepareStatement("desc "+ tableName);
        rs = pst.executeQuery();

        jsonObject.put("tableName",tableName);
        JSONArray jr = new JSONArray();
        while(rs.next()){
            JSONObject columnJSONObject = new JSONObject();
            columnJSONObject.put("field",rs.getString("field"));
            String type = "";
            String length ="";

            if(null != rs.getString("type") &&
                    rs.getString("type").split("\\(").length >1){
                type = rs.getString("type").split("\\(")[0];
                length = rs.getString("type").split("\\(")[1].replace(")","");
            }


            //columnJSONObject.put("type",rs.getString("type"));
            columnJSONObject.put("type",type);
            columnJSONObject.put("length",length);
            columnJSONObject.put("key",rs.getString("key"));
            jr.add(columnJSONObject);
        }
        jsonObject.put("columns",jr);

        return jsonObject;
    }

    @Override
    public JSONArray queryTableDataList(DataSource ds,String  tableName,String columns) throws Exception {
        JSONObject jsonObject = new JSONObject();
        Connection conn = DBUtils.getConnection(ds.getDriverClass(),ds.getUserName(),ds.getPassword(),ds.getUrl());
        String sql = new StringBuffer().append("select ").append(columns).append(" from ").append(tableName).append(" limit 10").toString();
        pst = conn.prepareStatement(sql);
        rs = pst.executeQuery();
        String [] columnArr = columns.split(",");
        JSONArray jr = new JSONArray();
        while(rs.next()){
            JSONObject columnJSONObject = new JSONObject();
            for(String columnName:columnArr){
                columnJSONObject.put(columnName,rs.getString(columnName));
            }
            jr.add(columnJSONObject);
        }
        return jr;
    }
}
