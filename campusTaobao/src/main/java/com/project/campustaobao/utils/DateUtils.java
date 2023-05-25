package com.project.campustaobao.utils;

import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    /**
     * 将Date回想转换成 2023-5-20的形式
     * @param date 日期对象
     * @return 字符串
     */
    public static String formatDate(Date date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }
    public static String formatDateTime(Date date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SS");
        return format.format(date);
    }
}
