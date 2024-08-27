package com.example.testdemo.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTImeUtil {

    private static final ThreadLocal<SimpleDateFormat> simpleDateFormatThreadLocal = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    //使用完之后记得删除
    public static String formatDate(Date date){
        return simpleDateFormatThreadLocal.get().format(date);
    }
}
