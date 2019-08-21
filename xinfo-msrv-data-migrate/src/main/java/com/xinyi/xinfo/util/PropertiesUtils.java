package com.xinyi.xinfo.util;

import com.xinyi.xinfo.contant.Constant;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtils {

    private static Properties props ;

    public static Properties getProperties(){
        props = new Properties();
        InputStream in = PropertiesUtils.class.getClassLoader().getResourceAsStream(Constant.PROPERTY_FILE_NAME);
        try {
            props.load(in);
            return props ;
        } catch (IOException e) {
            e.printStackTrace();
            return null ;
        }
    }
}
