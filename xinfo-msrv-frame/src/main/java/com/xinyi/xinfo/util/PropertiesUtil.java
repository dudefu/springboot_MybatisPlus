package com.xinyi.xinfo.util;

import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil
{
    private static Properties pros = null;
    
    static
    {
        PropertiesUtil.load("application.properties");
    }
    
    public static void load(String fileName)
    {
        pros = new Properties();
        InputStream is = PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName);
        try
        {
            pros.load(is);
        }
        catch(Exception e)
        {
        }
    }
    
    public static String get(String key)
    {
        return pros.getProperty(key);
    }
    
    
    public static String get(String key, String defaultValue)
    {
        String value = pros.getProperty(key);
        if (value == null)
        {
            value = defaultValue;
        }
        return value;
    }
    
    public static void main(String[] args)
    {
        System.out.println(PropertiesUtil.get("cas.server.url"));
    }
    
}
