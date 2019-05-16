package com.xuxianda;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @Author: xuxianda
 * @Date: 2019/5/16 14:34
 * @Version 1.0
 */
public class TestStreamApi2 {

    List<Employee> emps = Arrays.asList(
            new Employee("张三",15,1111.111f),
            new Employee("李四",25,2222.222f),
            new Employee("王五",35,3333.333f),
            new Employee("赵六",45,4444.444f),
            new Employee("田七",55,5555.555f),
            new Employee("田七",55,5555.555f),
            new Employee("田七",55,5555.555f),
            new Employee("田七",55,5555.555f)
    );

    //中间操作

    /*
        筛选与切片
        filter--接收lambda，从流中排除某些元素。
        limit--截断流，使其元素不超过给定数量
        skip(n)--跳过元素，返回一个扔掉了前n个元素的流。若流中元素不足n个，则返回一个空流。与limit(n)互补
    */
    //内部迭代：迭代操作有Stream API完成
    @Test
    public void test1(){
        //中间操作：不会执行任何操作
        Stream<Employee> stream = emps.stream()
                .filter((e) -> {
                    System.out.println("Stream API 的中间操作");
                    return e.getAge() > 20;
                });
        //终止操作：一次性执行全部内容，即“惰性求值”
        stream.forEach(System.out::println);
    }

    @Test
    public void test2(){
        emps.stream()
                .filter((e)->{
                    System.out.println("中间操作");
                    return e.getSalary()>3000;
                })
                .limit(3).forEach(System.out::println);
    }

    @Test
    public void test3(){
        emps.stream().distinct().forEach(System.out::println);
    }

}

