package com.xuxianda;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: xuxianda
 * @Date: 2019/5/21 15:17
 * @Version 1.0
 */
public class TestStreamAPI3 {

    List<Employee> emps = Arrays.asList(
            new Employee("李四",25,2222.222f),
            new Employee("王五",35,3333.333f),
            new Employee("赵六",45,4444.444f),
            new Employee("田七",55,5555.555f),
            new Employee("田七",55,5555.555f),
            new Employee("田七",55,5555.555f),
            new Employee("田七",55,5555.555f)
    );

    /**
     * 查找与匹配
     * allMatch--检查是否匹配所有元素
     * anyMatch--检查是够至少匹配一个元素
     * noneMatch--检查是否没有匹配元素
     * findFirst--返回第一个元素
     * findAny--返回当前流中的任意元素
     * count--返回流中元素的总个数
     * max--返回流中的最大值
     * min--返回流中最小值
     */

    @Test
    public void test1(){
        boolean b1 = emps.stream().allMatch(e->e.getSalary()>2000);
        System.out.println(b1);

        boolean b2 = emps.stream().anyMatch(e->e.getSalary()==2222.22f);
        System.out.println(b2);

        boolean b3 = emps.stream().noneMatch(e->e.getSalary()==2222.22f);
        System.out.println(b3);

        Optional<Employee> optional1 = emps.stream().sorted((e1, e2)->Float.compare(e1.getSalary(),e2.getSalary())).findFirst();
        System.out.println(optional1.orElse(new Employee()));//orElse：如果为空，则给一个默认值
        Optional<Employee> optional2 = emps.parallelStream().filter(x->x.getAge()>10).findAny();//并行执行，只要找到符合的数据就返回
        //Optional<Employee> optional2 = emps.stream().filter(x->x.getAge()>10).findAny();//串行返回
        System.out.println(optional2.get());
    }

    @Test
    public void test2(){
        long count = emps.stream().count();
        System.out.println(count);

        Optional<Employee> max = emps.stream().max((e1, e2) -> Float.compare(e1.getSalary(), e2.getSalary()));
        System.out.println(max.get());

        Optional<Float> min = emps.stream().map(Employee::getSalary).min(Float::compare);
        System.out.println(min.get());
    }

    /**
     * 归约
     * reduce(T identity,BinaryOperator)  /  reduce(BinaryOperator) -- 可以将流中元素反复结合起来，得到一个值。
     */
    @Test
    public void test3(){
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Integer sum = list.stream().reduce(0, (x, y) -> x + y);
        System.out.println(sum);
        System.out.println("------------------------------");
        Optional<Float> optional = emps.stream().map(Employee::getSalary).reduce(Float::sum);
        System.out.println(optional.get());
    }

    @Test
    public void test4(){
        List<String> list = emps.stream().map(Employee::getName).collect(Collectors.toList());
        list.forEach(System.out::println);
        System.out.println("--------------------------------");
        Set<String> set = emps.stream().map(Employee::getName).collect(Collectors.toSet());
        set.forEach(System.out::println);
        System.out.println("--------------------------------");
        HashSet<String> hashSet = emps.stream().map(Employee::getName).collect(Collectors.toCollection(HashSet::new));
        hashSet.forEach(System.out::println);
    }

    @Test
    public void test5(){
        Long count = emps.stream().map(Employee::getSalary).collect(Collectors.counting());
        System.out.println(count);
        System.out.println("---------------------------------");
        emps.stream().collect(Collectors.averagingInt(Employee::getAge));
    }

}

