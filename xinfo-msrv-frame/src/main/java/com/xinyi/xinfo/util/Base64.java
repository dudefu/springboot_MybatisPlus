/*
 * Base64Util.java Created on 2007-4-6
 * Copyright 2007@BroadText Inc.
 * All right reserved. 
 */
package com.xinyi.xinfo.util;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 关于Base64码的工具
 */
public class Base64
{

    public static byte[] decode(String base64Str)
    {
        byte[] b = null;
        BASE64Decoder decoder = new BASE64Decoder();
        try
        {
            b = decoder.decodeBuffer(replaceEnter(base64Str));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return b;
    }

    public static String replaceEnter(String str)
    {
        String reg = "[\n-\r]";
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(str);
        return m.replaceAll("");
    }

    public static String encode(byte[] data)
    {
        BASE64Encoder encoder = new BASE64Encoder();
        String base64 = encoder.encode(data);
        // 去掉换行
        byte bhh[] = new byte[2];
        bhh[0] = 13;
        bhh[1] = 10;
        String hchh = new String(bhh);
        base64 = base64.replaceAll(hchh, "");
        return base64;
    }
}
