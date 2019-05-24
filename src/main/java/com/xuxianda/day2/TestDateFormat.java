package com.xuxianda.day2;

import sun.util.resources.LocaleData;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @Author: xuxianda
 * @Date: 2019/5/23 16:29
 * @Version 1.0
 */
public class TestDateFormat {

    public static void main(String[] args) throws Exception{
        //线程不安全
        /*SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        Callable<Date> task = new Callable<Date>() {
            @Override
            public Date call() throws Exception {
                return df.parse("20161010");
            }
        };
        ExecutorService pool = Executors.newFixedThreadPool(10);
        List<Future<Date>> list = new ArrayList();
        for(int i=0;i<10;i++){
            list.add(pool.submit(task));
        }
        for(Future<Date> future : list){
            System.out.println(future.get());
        }
        pool.shutdown();*/
        //传统的线程安全
        /*Callable<Date> task = new Callable<Date>() {
            @Override
            public Date call() throws Exception {
                return DateFormatThreadLocal.convert("20181010");
            }
        };
        ExecutorService pool = Executors.newFixedThreadPool(10);
        List<Future<Date>> list = new ArrayList();
        for(int i=0;i<10;i++){
            list.add(pool.submit(task));
        }
        for(Future<Date> future : list){
            System.out.println(future.get());
        }
        pool.shutdown();*/
        //jdk1.8对时间就行的修改
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyyMMdd");
        Callable<LocalDate> task = new Callable<LocalDate>() {
            @Override
            public LocalDate call() throws Exception {
                return LocalDate.parse("20181010",df);
            }
        };
        ExecutorService pool = Executors.newFixedThreadPool(10);
        List<Future<LocalDate>> list = new ArrayList();
        for(int i=0;i<10;i++){
            list.add(pool.submit(task));
        }
        for(Future<LocalDate> future : list){
            System.out.println(future.get());
        }
        pool.shutdown();
    }

}

