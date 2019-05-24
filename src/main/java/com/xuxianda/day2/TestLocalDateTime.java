package com.xuxianda.day2;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Set;

/**
 * @Author: xuxianda
 * @Date: 2019/5/23 17:49
 * @Version 1.0
 */
public class TestLocalDateTime {

    @Test
    public void test8(){
        LocalDateTime ldt = LocalDateTime.now(ZoneId.of("Asia/Shanghai"));
        System.out.println(ldt);

        ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("Asia/Shanghai"));
        System.out.println(zonedDateTime);
    }

    @Test
    public void test7(){
        Set<String> availableZoneIds = ZoneId.getAvailableZoneIds();
        availableZoneIds.stream().forEach(System.out::println);
    }

    //DateTimeFormatter：格式化时间/日期
    @Test
    public void test6(){
//		DateTimeFormatter dtf = DateTimeFormatter.ISO_LOCAL_DATE;

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss E");

        LocalDateTime ldt = LocalDateTime.now();
        String strDate = ldt.format(dtf);

        System.out.println(strDate);

        LocalDateTime newLdt = LocalDateTime.parse(strDate, dtf);
        System.out.println(newLdt);
    }

    //4. TemporalAdjuster : 时间校正器
    @Test
    public void test5(){
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);

        LocalDateTime ldt2 = ldt.withDayOfMonth(10);
        System.out.println(ldt2);

        LocalDateTime ldt3 = ldt.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
        System.out.println(ldt3);

        //自定义：下一个工作日
        LocalDateTime ldt5 = ldt.with((l) -> {
            LocalDateTime ldt4 = (LocalDateTime) l;

            DayOfWeek dow = ldt4.getDayOfWeek();

            if(dow.equals(DayOfWeek.FRIDAY)){
                return ldt4.plusDays(3);
            }else if(dow.equals(DayOfWeek.SATURDAY)){
                return ldt4.plusDays(2);
            }else{
                return ldt4.plusDays(1);
            }
        });
        System.out.println(ldt5);
    }

    @Test
    public void test4(){
        LocalDate ld1 = LocalDate.now();
        LocalDate ld2 = LocalDate.of(2018, 1, 1);
        Period between = Period.between(ld2,ld1);
        System.out.println(between);
        System.out.println(between.getYears());
        System.out.println(between.getMonths());
        System.out.println(between.getDays());
    }

    //Duration：计算两个时间之间的间隔
    @Test
    public void test3() throws Exception{
        Instant ins1 = Instant.now();
        Thread.sleep(1000);
        Instant ins2 = Instant.now();
        Duration duration = Duration.between(ins1, ins2);
        System.out.println(duration.toNanos());
    }

    @Test
    public void test2(){
        Instant ins = Instant.now();
        System.out.println(ins);//默认获取的是本初子午线的时间
        OffsetDateTime odt = ins.atOffset(ZoneOffset.ofHours(8));//东八区
        System.out.println(odt);
    }

    //1. LocalDate、LocalTime、LocalDateTime
    @Test
    public void test1(){
        LocalDateTime ld1 = LocalDateTime.now();
        System.out.println(ld1);

        LocalDateTime ld2 = LocalDateTime.of(2019, 5, 23, 10, 10, 10);
        System.out.println(ld2);

        LocalDateTime ld3 = ld2.plusYears(1);
        System.out.println(ld3);

        System.out.println(ld3.getYear());
        System.out.println(ld3.getMonth());
        System.out.println(ld3.getDayOfMonth());
        System.out.println(ld3.getHour());
        System.out.println(ld3.getMinute());
        System.out.println(ld3.getSecond());
    }

}

