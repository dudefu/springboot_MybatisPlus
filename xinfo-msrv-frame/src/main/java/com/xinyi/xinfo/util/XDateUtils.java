/**
 * 日期工具类
 */
package com.xinyi.xinfo.util;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Interval;
import org.joda.time.Period;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * 时间工具类
 * 
 * @author XIVA
 *
 */
public class XDateUtils
{

    public static final String TO_MONTH = "yyyy-MM";
    public static final String TO_DATE = "yyyy-MM-dd";
    public static final String TO_TIME = "HH:mm:ss";
    public static final String TO_MINUTE = "yyyy-MM-dd HH:mm";
    public static final String TO_SECOND = "yyyy-MM-dd HH:mm:ss";
    public static final String TO_MILLISECOND = "yyyy-MM-dd HH:mm:ss SSSS";

    public static final String TO_DATE_CN = "yyyy年MM月dd日";
    public static final String TO_TIME_CN = "HH时mm分ss秒";
    public static final String TO_MINUTE_CN = "yyyy年MM月dd日 HH时mm分";
    public static final String TO_SECOND_CN = "yyyy年MM月dd日 HH时mm分ss秒";

    public static final String TO_MONTH_N = "yyyyMM";
    public static final String TO_DATE_N = "yyyyMMdd";
    public static final String TO_MINUTE_N = "yyyyMMddHHmm";
    public static final String TO_SECOND_N = "yyyyMMddHHmmss";
    public static final String TO_MILLISECOND_N = "yyyyMMddHHmmssSSSS";

    public static final String[] FORMATS = new String[] { TO_MONTH, TO_DATE, TO_TIME, TO_MINUTE, TO_SECOND, TO_MILLISECOND, TO_MONTH_N, TO_DATE_N, TO_MINUTE_N, TO_SECOND_N,
            TO_MILLISECOND_N, TO_DATE_CN, TO_TIME_CN, TO_MINUTE_CN, TO_SECOND_CN };

    /**
     * 将字符串解析成Date对象。<br/>
     * 该方法尝试用[yyyy-MM/yyyy-MM-dd/ yyyy-MM-dd HH:mm/yyyy-MM-dd HH:mm:ss/yyyy-MM-dd HH:mm:ss SSSS/ yyyyMM/yyyyMMdd/yyyyMMddHHmm/ yyyyMMddHHmmss/yyyyMMddHHmmssSSSS]格式进行解析，如果无法解析将抛出异常。
     * 
     * @param str
     *            字符串
     * @return 返回对应的Date对象。
     * @author fwenrong
     */
    public static Date parse(String str)
    {
        String pattern = getDateFormat(str);
        return parse(str, pattern);
    }

    /**
     * 将指定格式的字符串解析成Date对象。
     * 
     * @param str
     *            字符串
     * @param format
     *            格式
     * @return 返回对应的Date对象。
     * @author fwenrong
     */
    public static Date parse(String str, String format)
    {
        DateTimeFormatter formatter = DateTimeFormat.forPattern(format);
        return DateTime.parse(str, formatter).toDate();
    }

    /**
     * 将Date对象解析成yyyy-MM-dd格式的字符串。
     * 
     * @param date
     *            Date对象
     * @return 返回yyyy-MM-dd格式的字符串。
     * @author fwenrong
     */
    public static String format(Date date)
    {
        return format(date, TO_DATE);
    }

    /**
     * 将Date对象解析成指定格式的字符串。
     * 
     * @param date
     *            Date对象
     * @param pattern
     *            格式
     * @return 返回指定格式的字符串。
     * @author fwenrong
     */
    public static String format(Date date, String pattern)
    {
        return new DateTime(date).toString(pattern);
    }

    /**
     * 获取字符串的日期格式。如果字符串不在[{@link #TO_MONTH}/{@link #TO_DATE}/ {@link #TO_MINUTE} /{@link #TO_SECOND}/{@link #TO_MILLISECOND} ]格式范围内将抛出异常。
     * 
     * @param str
     *            字符串
     * @return 返回字符串的日期格式。
     * @author fwenrong
     */
    public static String getDateFormat(String str)
    {
        for (String format : FORMATS)
        {
            if (isDate(str, format))
            {
                return format;
            }
        }
        throw new IllegalArgumentException("不支持的日期格式：" + str);
    }

    /**
     * 判断字符串是否为日期格式的字符串。
     * 
     * @param str
     *            字符串
     * @return 如果是日期格式的字符串返回true，否则返回false。
     * @author fwenrong
     */
    public static Boolean isDate(String str)
    {
        for (String format : FORMATS)
        {
            if (isDate(str, format))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断字符串是否为指定日期格式的字符串。
     * 
     * @param str
     *            字符串
     * @param format
     *            日期格式
     * @return 如果是指定日期格式的字符串返回true，否则返回false。
     * @author fwenrong
     */
    public static Boolean isDate(String str, String format)
    {
        try
        {
            parse(str, format);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    /**
     * 获取当前日期（只取到日期，时间部分都为0）。
     * 
     * @return 返回当前日期。
     * @author fwenrong
     */
    public static Date getToday()
    {
        return DateTime.now().toLocalDate().toDate();
    }

    /**
     * 获取当前时间 精确到秒
     * 
     * @return 返回当前时间，未格式化输出
     * @author fwenrong
     */
    public static Date getNowTime()
    {
        return DateTime.now().toLocalDateTime().toDate();
    }

    /**
     * 按照指定的格式输出当前时间
     * 
     * @param pattern
     *            输出时间格式
     * @return
     * @author fwenrong
     */
    public static String getNowTimePattern(String pattern)
    {
        return format(getNowTime(), pattern);
    }

    /**
     * 获取下一天日期。（只取到日期，时间部分都为0）。
     * 
     * @return 返回下一天日期。
     * @author fwenrong
     */
    public static Date getNextDay()
    {
        return getNextDay(getToday());
    }

    /**
     * 获取指定日期的下一天日期。
     * 
     * @param date
     *            指定日期
     * @return 返回指定日期的下一天日期。
     * @author fwenrong
     */
    public static Date getNextDay(Date date)
    {
        return new DateTime(date).plusDays(1).toLocalDate().toDate();
    }

    /**
     * 获取Joda Time的Duration对象。
     * 
     * @param beginDate
     *            开始日期
     * @param endDate
     *            结束日期
     * @return 返回Joda Time的Duration对象。
     * @author fwenrong
     */
    public static Duration getDuration(Date beginDate, Date endDate)
    {
        return new Duration(new DateTime(beginDate), new DateTime(endDate));
    }

    /**
     * 获取Joda Time的Peroid对象。
     * 
     * @param beginDate
     *            开始日期
     * @param endDate
     *            结束日期
     * @return 返回Joda Time的Peroid对象。
     * @author fwenrong
     */
    public static Period getPeriod(Date beginDate, Date endDate)
    {
        return new Period(new DateTime(beginDate), new DateTime(endDate));
    }

    /**
     * 获取Joda Time的Interval对象。
     * 
     * @param beginDate
     *            开始日期
     * @param endDate
     *            结束日期
     * @return 返回Joda Time的Interval对象。
     * @author fwenrong
     */
    public static Interval getInterval(Date beginDate, Date endDate)
    {
        return new Interval(new DateTime(beginDate), new DateTime(endDate));
    }

    /**
     * 开始时间 初始化
     * 
     * @param date
     * @return
     * @author fwenrong
     */
    public static Timestamp getStartTime(Date date)
    {
        if (date != null)
        {
            Calendar dateCalendar = Calendar.getInstance();
            dateCalendar.setTime(date);
            Calendar calendar = new GregorianCalendar(dateCalendar.get(Calendar.YEAR), dateCalendar.get(Calendar.MONTH), dateCalendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
            return new Timestamp(calendar.getTimeInMillis());
        }
        else
        {
            return null;
        }
    }

    /**
     * 结束时间初始化
     * 
     * @param date
     * @return
     * @author fwenrong
     */
    public static Timestamp getEndTime(Date date)
    {
        if (date != null)
        {
            Calendar dateCalendar = Calendar.getInstance();
            dateCalendar.setTime(date);
            Calendar calendar = new GregorianCalendar(dateCalendar.get(Calendar.YEAR), dateCalendar.get(Calendar.MONTH), dateCalendar.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
            return new Timestamp(calendar.getTimeInMillis());
        }
        else
        {
            return null;
        }
    }

}
