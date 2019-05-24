package com.xuxianda.day2;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: xuxianda
 * @Date: 2019/5/23 16:49
 * @Version 1.0
 */
public class DateFormatThreadLocal {

    private static final ThreadLocal<DateFormat> tl = new ThreadLocal<DateFormat>(){
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyyMMdd");
        }
    };

    public static final Date convert(String source) throws Exception{
        return tl.get().parse(source);
    }

}

