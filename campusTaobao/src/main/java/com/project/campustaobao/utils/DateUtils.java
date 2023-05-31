package com.project.campustaobao.utils;

import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtils {
    private static final SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat formatDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SS");
    /**
     * 将Date回想转换成 2023-5-20的形式
     * @param date 日期对象
     * @return 字符串
     */
    public static String formatDate(Date date){
        return formatDate.format(date);
    }
    public static String formatDateTime(Date date){
        return formatDateTime.format(date);
    }
    public static String formatDate(LocalDateTime date){
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return date.format(fmt);
    }
    public static String formatDateTime(LocalDateTime datetime){
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return datetime.format(fmt);
    }
}
