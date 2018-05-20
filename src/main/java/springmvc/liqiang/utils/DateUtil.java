package springmvc.liqiang.utils;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期操作工具类，主要实现了日期的常用操作。
 * <p>
 * 在工具类中经常使用到工具类的格式化描述，这个主要是一个日期的操作类，所以日志格式主要使用 SimpleDateFormat的定义格式.
 * <p>
 * 格式的意义如下： 日期和时间模式 <br>
 * 日期和时间格式由日期和时间模式字符串指定。在日期和时间模式字符串中，未加引号的字母 'A' 到 'Z' 和 'a' 到 'z'
 * 被解释为模式字母，用来表示日期或时间字符串元素。文本可以使用单引号 (') 引起来，以免进行解释。"''"
 * 表示单引号。所有其他字符均不解释；只是在格式化时将它们简单复制到输出字符串，或者在分析时与输入字符串进行匹配。
 * <p>
 * 定义了以下模式字母（所有其他字符 'A' 到 'Z' 和 'a' 到 'z' 都被保留）： <br>
 * <p>
 * 模式字母通常是重复的，其数量确定其精确表示：
 */
public final class DateUtil implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -3098985139095632110L;

    // 默认显示日期的格式
    public static final String DATAFORMAT_STR = "yyyy-MM-dd";

    // 默认显示日期的格式
    public static final String YYYY_MM_DATAFORMAT_STR = "yyyy-MM";

    // 默认显示日期时间的格式
    public static final String DATATIMEF_STR = "yyyy-MM-dd HH:mm:ss";

    // 默认显示日期时间的格式
    public static final String DATATIMEF_P_STR = "yyyyMMddHHmmss";

    // 默认显示简体中文日期的格式
    public static final String ZHCN_DATAFORMAT_STR = "yyyy年MM月dd日";

    // 默认显示简体中文日期时间的格式
    public static final String ZHCN_DATATIMEF_STR = "yyyy年MM月dd日HH时mm分ss秒";

    // 默认显示简体中文日期时间的格式
    public static final String ZHCN_DATATIMEF_STR_4yMMddHHmm = "yyyy年MM月dd日HH时mm分";

    public DateUtil() {
    }

    public static Timestamp dateToTimestamp(Date date) {
        //先把date转换为String
        String dateStr = "";
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateStr = sdf.format(date);
        //string转换为timestamp
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        ts = Timestamp.valueOf(dateStr);
        return ts;
    }

    public static Date parseStrToDate(String date, String format) {
        try {
            if (null == date || "".equals(date)) {
                return null;
            }
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.parse(date);
        } catch (Exception e) {
            return null;
        }
    }

    public static Timestamp parseStrToTimestamp(String tsStr, String format) {
        try {
            if (null == tsStr || "".equals(tsStr)) {
                return null;
            }
            Date date = parseStrToDate(tsStr, format);
            return new Timestamp(date.getTime());
        } catch (Exception e) {
            return null;
        }
    }
}
