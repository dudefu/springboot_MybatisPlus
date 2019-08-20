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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OracleDaoImpl implements RdbmsDao {
    private PreparedStatement pst;
    private ResultSet rs;


    @Override
    public JSONObject queryTableList(DataSource ds) throws Exception {
        final String table_sql = "select table_name from all_tab_comments where Owner = ? and table_type ='TABLE'";
        final String view_sql = "select table_name from all_tab_comments where Owner = ? and table_type ='VIEW'";
        JSONObject jsonObject = new JSONObject();
        Connection conn = DBUtils.getConnection(ds.getDriverClass(), ds.getUserName(), ds.getPassword(), ds.getUrl());


        pst = conn.prepareStatement(table_sql);
        pst.setString(1, ds.getUserName());
        rs = pst.executeQuery();

        List<String> tableList = new ArrayList();
        while (rs.next()) {
            tableList.add(rs.getString(1));
        }
        jsonObject.put("tableList", tableList);

        pst = conn.prepareStatement(view_sql);
        pst.setString(1, ds.getUserName());
        rs = pst.executeQuery();

        List<String> viewList = new ArrayList();
        while (rs.next()) {
            viewList.add(rs.getString(1));
        }
        jsonObject.put("viewList", viewList);

        return jsonObject;
    }

    @Override
    public JSONObject queryTableSchema(DataSource ds, String tableName) throws Exception {
        JSONObject jsonObject = new JSONObject();

        String sql = "select column_name as field ,data_type as type,data_length as length from cols WHERE TABLE_name=upper(?) ";
        Connection conn = DBUtils.getConnection(ds.getDriverClass(), ds.getUserName(), ds.getPassword(), ds.getUrl());
        String k_sql = "select constraint_name,constraint_type from user_constraints where TABLE_name=upper(?)";

        pst = conn.prepareStatement(k_sql);
        pst.setString(1,tableName);
        rs = pst.executeQuery();

        Map<String,String> map = new HashMap<String,String>();
        while (rs.next()){
            map.put(rs.getString("constraint_name"),rs.getString("constraint_type"));
        }

        pst = conn.prepareStatement(sql);
        pst.setString(1,tableName);
        rs = pst.executeQuery();

        jsonObject.put("tableName",tableName);
        JSONArray jr = new JSONArray();
        while(rs.next()){
            JSONObject columnJSONObject = new JSONObject();
            columnJSONObject.put("field",rs.getString("field"));
            columnJSONObject.put("type",rs.getString("type"));
            columnJSONObject.put("length",rs.getString("length"));
            columnJSONObject.put("key",map.get(rs.getString("field"))==null?"":map.get(rs.getString("field")));
            jr.add(columnJSONObject);
        }
        jsonObject.put("columns",jr);

        return jsonObject;

    }

    @Override
    public JSONArray queryTableDataList(DataSource ds, String  tableName,String columns) throws Exception {
        JSONObject jsonObject = new JSONObject();
        Connection conn = DBUtils.getConnection(ds.getDriverClass(),ds.getUserName(),ds.getPassword(),ds.getUrl());
        String sql = new StringBuffer().append("select \"").append(columns.replaceAll(",","\",\"")).append("\" from ").append(tableName).append(" where ROWNUM <= 10").toString();

        System.out.println(sql);
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
