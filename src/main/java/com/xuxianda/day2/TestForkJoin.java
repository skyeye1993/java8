package com.xuxianda.day2;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.LongStream;

/**
 * @Author: xuxianda
 * @Date: 2019/5/22 15:01
 * @Version 1.0
 */
public class TestForkJoin {

    @Test
    public void test1(){
        long start = System.currentTimeMillis();
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinCalculate calculate = new ForkJoinCalculate(0L, 100000000000L);
        Long sum = pool.invoke(calculate);
        System.out.println(sum);
        long end = System.currentTimeMillis();
        System.out.println(end-start);
    }

    @Test
    public void test2(){
        long start = System.currentTimeMillis();
        long sum=0;
        for(long i=1;i<=100000000000L;i++){
            sum+=i;
        }
        System.out.println(sum);//5340232226128654848
        long end = System.currentTimeMillis();
        System.out.println(end-start);
    }

    @Test
    public void test3(){
        Instant now = Instant.now();
        LongStream.range(0L,100000000000L)
                .parallel()
                .reduce(0,Long::sum);
        Instant end = Instant.now();
        System.out.println(Duration.between(now,end).toMillis());
    }

}

