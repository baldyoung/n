package n.baldyoung.DateTimeOption;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.CancellationException;

/**
 * 与时间值相关的操作集
 * 提供有以下静态方法：
 * 对Date对象的以下单位进行加减：年、月、日、小时（24）、小时（12）、分钟、秒、毫秒
 */
public class DateValueOption {

    private static final ThreadLocal<Calendar> threadLocalCalendar = ThreadLocal.withInitial(() -> Calendar.getInstance());

    public static Calendar getCalendarInstance() {
        return threadLocalCalendar.get();
    }

    public static Calendar getCalendarInstance(Date date) {
        Calendar calendar = getCalendarInstance();
        calendar.setTime(date);
        return calendar;
    }


    /**
     * 获取秒
     * @param date
     * @return
     */
    public static int getSecond(Date date) {
        return getCalendarInstance(date).get(Calendar.SECOND);
    }

    /**
     * 获取分钟
     * @param date
     * @return
     */
    public static int getMinute(Date date) {
        return getCalendarInstance(date).get(Calendar.MINUTE);
    }

    /**
     * 获取小时（12小时制）
     * @param date
     * @return
     */
    public static int getHour(Date date) {
        return getCalendarInstance(date).get(Calendar.HOUR);
    }

    /**
     * 获取小时（24小时制）
     * @param date
     * @return
     */
    public static int getHourOfDay(Date date) {
        return getCalendarInstance(date).get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 获取星期几
     * @param date
     * @return
     */
    public static int getDayOfWeek(Date date) {
        return getCalendarInstance(date).get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 获取月里的几号
     * @param date
     * @return
     */
    public static int getDayOfMonth(Date date) {
        return getCalendarInstance(date).get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取年里的第几天
     * @param date
     * @return
     */
    public static int getDayOfYear(Date date) {
        return getCalendarInstance(date).get(Calendar.DAY_OF_YEAR);
    }

    /**
     * 获取月份（从0开始）
     * @param date
     * @return
     */
    public static int getMonth(Date date) {
        return getCalendarInstance(date).get(Calendar.MONTH);
    }

    /**
     * 获取年
     * @param date
     * @return
     */
    public static int getYear(Date date) {
        return getCalendarInstance(date).get(Calendar.YEAR);
    }


    /**
     * 增减秒
     * @param date
     * @param second
     * @return
     */
    public static Date plusSecond(Date date, int second) {
        Calendar calendar = getCalendarInstance(date);
        calendar.add(Calendar.SECOND, second);
        return calendar.getTime();
    }

    /**
     * 增减分钟
     * @param date
     * @param minute
     * @return
     */
    public static Date plusMinute(Date date, int minute) {
        Calendar calendar = getCalendarInstance(date);
        calendar.add(Calendar.MINUTE, minute);
        return calendar.getTime();
    }

    /**
     * 增减小时
     * @param date
     * @param hour
     * @return
     */
    public static Date plusHour(Date date, int hour) {
        Calendar calendar = getCalendarInstance(date);
        calendar.add(Calendar.HOUR_OF_DAY, hour);
        return calendar.getTime();
    }

    /**
     * 增减天数
     * @param date
     * @param days
     * @return
     */
    public static Date plusDay(Date date, int days) {
        Calendar calendar = getCalendarInstance(date);
        calendar.add(Calendar.DAY_OF_MONTH, days);
        return calendar.getTime();
    }

    /**
     * 增减月份
     * @param date
     * @param month
     * @return
     */
    public static Date plusMonth(Date date, int month) {
        Calendar calendar = getCalendarInstance(date);
        calendar.add(Calendar.MINUTE, month);
        return calendar.getTime();
    }

    /**
     * 增减年份
     * @param date
     * @param year
     * @return
     */
    public static Date plusYear(Date date, int year) {
        Calendar calendar = getCalendarInstance(date);
        calendar.add(Calendar.YEAR, year);
        return calendar.getTime();
    }







}
