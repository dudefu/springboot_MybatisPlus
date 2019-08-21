package com.xinyi.xinfo.util;

import com.xinyi.xinfo.contant.Constant;

import java.sql.Connection;
import java.sql.DriverManager;

public class OracleDBUtils {

    public static Connection getConnection(){
        try{
            Class.forName(Constant.Oracle_CLASS_NAME);
            Connection connection = DriverManager.getConnection(Constant.Oracle_URL, Constant.Oracle_USERNAME, Constant.Oracle_PASSWORD);
            return connection;
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }
}
