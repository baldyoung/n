package n.baldyoung.DateTimeOption;

import java.util.Calendar;
import java.util.Date;

/**
 * 与时间值相关的操作集
 * 提供有以下静态方法：
 * 对Date对象的以下单位进行加减：年、月、日、小时（24）、小时（12）、分钟、秒、毫秒
 */
public class DateValueOption {

    private static final ThreadLocal<Calendar> threadLocalCalendar = ThreadLocal.withInitial(() -> Calendar.getInstance());

    public static Calendar getSingleInstance() {
        return threadLocalCalendar.get();
    }

    public static Date plusDay(Date sourceDate, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(sourceDate);
        calendar.add(Calendar.DAY_OF_MONTH, days);
        return calendar.getTime();
    }


    //volatile

    public static int dayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }


}
