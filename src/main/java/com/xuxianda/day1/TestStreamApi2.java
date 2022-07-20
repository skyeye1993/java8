package com.xuxianda.day1;

import org.junit.Test;

import java.util.ArrayList;
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

    /*
     * 映射
     * map -- 接收lambda，将元素转换成其他形式或提取信息。接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素
     * flapMap-接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流
     */

    @Test
    public void test4(){
        List<String> stringList = Arrays.asList("aa", "bb", "ccc", "ddd");
        stringList.stream().map((str)->str.toUpperCase()).forEach(System.out::println);
        System.out.println("---------------------------------------------");
        emps.stream().map(Employee::getAge).forEach(System.out::println);
        System.out.println("---------------------------------------------");
        /*Stream<Stream<Character>> stream = stringList.stream().map(TestStreamApi2::filterCharacter);
        stream.forEach((sm)->sm.forEach(System.out::println));{{a,a},{b,b},{c,c,c}}*/
        Stream<Character> stream = stringList.stream().flatMap(TestStreamApi2::filterCharacter);
        stream.forEach(Test1::print1); //效果与注释代码相同  {a,a,b,b,c,c,c}
    }

    public static Stream<Character> filterCharacter(String string){
        List<Character> list = new ArrayList<>();
        for(int i=0;i<string.length();i++){
            list.add(string.charAt(i));
        }
        return list.stream();
    }

    /**
     * 排序   sorted()--自然排序（Comparable）
     * sorted(Comparator com)--定制排序（Comparator）
     */
    @Test
    public void test7(){
        List<String> stringList = Arrays.asList("aa", "bb", "ccc", "ddd");
        stringList.stream().sorted().forEach(System.out::println);
        emps.stream().sorted((e1,e2)->{
          if(e1.getSalary()>e2.getSalary()){
              return -1;
          }else {
              return 1;
          }
        }).forEach(System.out::println);
    }

}

