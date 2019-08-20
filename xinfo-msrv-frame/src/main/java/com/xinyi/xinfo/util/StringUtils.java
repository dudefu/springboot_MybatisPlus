package com.xinyi.xinfo.util;

import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.sql.Clob;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xinyi.xinfo.exception.BasicAppException;

/**
 * 
 * 功能说明： 字符串处理工具类
 * 
 * StringUtils.java
 * 
 * Original Author: 方文荣,2014年12月3日
 *
 * Copyright (C)1997-2014 深圳信义科技 All rights reserved.
 */
public class StringUtils
{

    private static final Logger logger = LoggerFactory.getLogger(StringUtils.class);

    /**
     * 对象为空返回空字符串，否则toString
     * @param obj
     * @return
     */
    public static String toString(Object obj) 
    {
		return obj == null ? "" : obj.toString();
	}
    
    
    /**
     * 判断指定字符串是否为空。
     * 
     * @param str
     *            待判断的字符串
     * @return 返回指定字符串是否为空。
     */
    public static Boolean isEmpty(String str)
    {
        return (str == null || str.isEmpty() || "undefined".equalsIgnoreCase(str));
    }

    public static Boolean isEmpty(Object str)
    {
        return (str == null || "".equals(str) || "undefined".equalsIgnoreCase(String.valueOf(str)));
    }

    /**
     * 判断指定字符串是否不为空。
     * 
     * @param str
     *            待判断的字符串
     * @return 返回指定字符串是否不为空。
     */
    public static Boolean isNotEmpty(String str)
    {
        return !isEmpty(str);
    }

    /**
     * 判断指定字符串是否为空字符串。
     * 
     * @param str
     *            待判断的字符串
     * @return 返回指定字符串是否为空字符串。
     */
    public static Boolean isBlank(String str)
    {
        if (isEmpty(str))
        {
            return true;
        }
        for (char c : str.toCharArray())
        {
            if (!Character.isWhitespace(c))
            {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断指定字符串是否不为空字符串。
     * 
     * @param str
     *            待判断的字符串
     * @return 返回指定字符串是否不为空字符串。
     */
    public static Boolean isNotBlank(String str)
    {
        return !isBlank(str);
    }

    /**
     * 截取指定分隔符前的字符串内容。
     * 
     * @param str
     *            待截取的字符串
     * @param separator
     *            分隔符
     * @return 返回指定分隔符前的字符串内容。
     */
    public static String substringBefore(String str, String separator)
    {
        if (isEmpty(str) || isEmpty(separator))
        {
            logger.error("待截取的字符串为空或匹配字符为空..");
            return null;
        }
        int pos = str.indexOf(separator);
        if (pos == -1)
        {
            return str;
        }
        return str.substring(0, pos);
    }

    /**
     * 截取最后一个分隔符前的字符串内容。
     * 
     * @param str
     *            待截取的字符串
     * @param separator
     *            分隔符
     * @return 返回最后一个分隔符前的字符串内容。
     */
    public static String substringBeforeLast(String str, String separator)
    {
        if (isEmpty(str) || isEmpty(separator))
        {
            logger.error("待截取的字符串为空或匹配字符为空..");
            return null;
        }
        int pos = str.lastIndexOf(separator);
        if (pos == -1)
        {
            return str;
        }
        return str.substring(0, pos);
    }

    /**
     * 截取指定分隔符后的字符串内容。
     * 
     * @param str
     *            待截取的字符串
     * @param separator
     *            分隔符
     * @return 返回指定分隔符后的字符串内容。
     */
    public static String substringAfter(String str, String separator)
    {
        if (isEmpty(str) || isEmpty(separator))
        {
            logger.error("待截取的字符串为空或匹配字符为空..");
            return null;
        }
        int pos = str.indexOf(separator);
        if (pos == -1)
        {
            return "";
        }
        return str.substring(pos + separator.length());
    }

    /**
     * 截取最后一个分隔符后的字符串内容。
     * 
     * @param str
     *            待截取的字符串
     * @param separator
     *            分隔符
     * @return 返回最后一个分隔符后的字符串内容。
     */
    public static String substringAfterLast(String str, String separator)
    {
        if (isEmpty(str) || isEmpty(separator))
        {
            logger.error("待截取的字符串为空或匹配字符为空..");
            return null;
        }

        int pos = str.lastIndexOf(separator);
        if (pos == -1 || pos == (str.length() - separator.length()))
        {
            return "";
        }
        return str.substring(pos + separator.length());
    }

    /**
     * 截取两个分隔符之间的字符串。
     * 
     * @param str
     *            待截取的字符串
     * @param startSeparator
     *            开始分隔符
     * @param endSeparator
     *            结束分隔符
     * @return 返回两个分隔符之间的字符串。
     */
    public static String substringBetween(String str, String startSeparator, String endSeparator)
    {
        if (isEmpty(str) || isEmpty(startSeparator) || isEmpty(endSeparator))
        {
            logger.error("待截取的字符串为空或匹配字符为空..");
            return null;
        }
        int start = str.indexOf(startSeparator);
        if (start != -1)
        {
            int end = str.indexOf(endSeparator, start + startSeparator.length());
            if (end != -1)
            {
                return str.substring(start + startSeparator.length(), end);
            }
        }
        return null;
    }

    /**
     * 截取指定起始位置和截取长度的字符串。
     * 
     * @param str
     *            待截取的字符串
     * @param pos
     *            起始位置
     * @param len
     *            截取长度
     * @return 返回指定起始位置和截取长度的字符串。
     */
    public static String mid(String str, int pos, int len)
    {
        if (isEmpty(str))
        {
            logger.error("待截取的字符串为空..");
            return null;
        }
        if (pos < 0 || pos > str.length())
        {
            logger.error("起始位置不在长度范围内..");
            return null;
        }
        if (len < 0)
        {
            logger.error("截取长度不合法..");
            return null;
        }
        if (str.length() <= (pos + len))
        {
            return str.substring(pos);
        }
        return str.substring(pos, pos + len);
    }

    /**
     * 将一个字符串数组用指定分隔符串联起来。
     * 
     * @param strs
     *            字符串数组
     * @param separator
     *            分隔符
     * @return 返回串联起来的字符串。
     */
    public static String join(String[] strs, String separator)
    {
        if (strs == null || strs.length == 0)
        {
            logger.error("待截取的字符串数组为空..");
            return null;
        }
        if (isEmpty(separator))
        {
            logger.error("分隔符为空..");
            return null;
        }
        StringBuffer buf = new StringBuffer(256);
        for (String str : strs)
        {
            buf.append(str + separator);
        }

        String result = buf.toString();
        if (!separator.isEmpty())
        {
            result = substringBeforeLast(result, separator);
        }
        return result;
    }

    /**
     * 将一个字符串列表用指定分隔符串联起来。
     * 
     * @param strs
     *            字符串数组
     * @param separator
     *            分隔符
     * @return 返回串联起来的字符串数组。
     */
    public static String join(List<String> strs, String separator)
    {
        return join(strs.toArray(new String[] {}), separator);
    }

    /**
     * 将一个字符串Set用指定分割符号串联起来
     * 
     * @param strs
     * @param separator
     * @return
     * @author wenrong.fang
     */
    public static String joion(Set<String> strs, String separator)
    {
        return join(strs.toArray(new String[] {}), separator);

    }

    /**
     * 对字符串进行字符集转换。
     * 
     * @param str
     *            字符串
     * @param origEncoding
     *            原字符集编码
     * @param destEncoding
     *            新字符集编码
     * @return 返回转换后的字符串。
     */
    public static String encode(String str, String origEncoding, String destEncoding)
    {
        try
        {
            return new String(str.getBytes(origEncoding), destEncoding);
        }
        catch (UnsupportedEncodingException e)
        {
            throw new BasicAppException("对字符串进行字符集转换时发生异常", e);
        }
    }
    
    public static String clobToString(Clob clob) {
        Reader inStream = null;
        String data = "";
        if(clob != null)
        {
            try {
                inStream = clob.getCharacterStream();
                char[] c = new char[(int)clob.length()];
                inStream.read(c);
                data = new String(c);
                inStream.close();
            } catch (Exception e) { 
                e.printStackTrace();
            }
        }
        return data;
     }
}
