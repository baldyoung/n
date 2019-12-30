package n.baldyoung.DateTimeOption;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static java.lang.System.out;

/**
 * 与时间字符串相关的操作集
 * 提供以下静态方法：
 * 获取Date对象所处的年f、月f、日f、小时（24）f、小时（12）、分钟f、秒f、毫秒
 * 将yyyy-MM-dd HH:mm:ss格式的字符串转换为Date对象
 *
 * 高并发请求下，defaultFormat()性能优于format()
 *
 */
public class DateStringOption {
    private static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final String ZERO_FORMAT = "yyyy-MM-dd 00:00:00";
    private static final ThreadLocal<DateFormat> threadLocalDateFormats = ThreadLocal.withInitial(() ->
        new SimpleDateFormat(DEFAULT_FORMAT)
    );
    private static final DateFormat zeroDateFormat = new SimpleDateFormat(ZERO_FORMAT);

    /**（线程安全）
     * 不推荐使用
     * @param date
     * @param pattern
     * @return
     */
    public static String format(Date date, String pattern) {
        return new SimpleDateFormat(pattern).format(date);
    }

    /**（线程安全）
     * 推举使用
     * @param date
     * @return
     */
    public static String defaultFormat(Date date) {
        return threadLocalDateFormats.get().format(date);
    }

    public static String getYearOfDate(Date date) {
        String dateString = defaultFormat(date);
        return dateString.split("-")[0];
    }
    public static String getMonthOfDate(Date date) {
        String dateString = defaultFormat(date);
        return dateString.split("-")[1];
    }
    public static String getDayOfDate(Date date) {
        String dateString = defaultFormat(date);
        return dateString.replace(" ", "-").split("-")[2];
    }
    public static String getHourOfDate(Date date) {
        String dateString = defaultFormat(date);
        return dateString.replace(" ", ":").split(":")[1];
    }
    public static String getMinuteOfDate(Date date) {
        String dateString = defaultFormat(date);
        return dateString.split(":")[1];
    }
    public static String getSecondOfDate(Date date) {
        String dateString = defaultFormat(date);
        return dateString.split(":")[2];
    }

    /**（线程安全）
     * 数值上的不合理会被“递增”校正
     * @param dateString
     * @return
     * @throws ParseException
     */
    public static Date parseDate(String dateString) {
        try {
            return threadLocalDateFormats.get().parse(dateString);
        } catch (ParseException e) {
            return null;
        }
    }

    /**（线程安全）
     * 将指定时间转换为零点时间
     * @param date
     * @return
     */
    public static Date getZeroDate(Date date) {
        synchronized (zeroDateFormat) {
            try {
                return zeroDateFormat.parse(zeroDateFormat.format(date));
            } catch (ParseException e) {
                return null;
            }
        }
    }






}
