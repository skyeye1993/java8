package com.xuxianda.day1;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Java8 内置的四大核心函数式接口
 * Consumer<T> ：消费型接口
 *              void accept(T t)
 * Supplier<T> ：供给型接口
 *          T get();
 * Function<T,R> :函数型接口
 *          R apply(T t);
 * Predicate<T>  :  断言型接口
 *          boolean test(T t)
 */
public class TestLambda3 {

    @Test
    public void test4(){
        List<String> list = new ArrayList<String>();
        list.add("111");
        list.add("222");
        list.add("333");
        list.add("444");
        list.add("555");
        list.add("666");
        List<String> stringList = filterStr(list,(s)->s.compareTo("333")>0);
        stringList.forEach((x)-> System.out.println(x));
    }

    //Predicate<T>  :  断言型接口
    public List<String> filterStr(List<String> stringList,Predicate<String> p){
        List strList = new ArrayList();
        for(String str : stringList){
            if(p.test(str)){
                strList.add(str);
            }
        }
        return strList;
    }

    @Test
    public void test3(){
        System.out.println(strHandler("     asdf  asdfqer",(x)->x.trim()));
        System.out.println(strHandler("zxcvasdasdfqer",(x)->x.toUpperCase()));
    }

    //函数型接口
    public String strHandler(String str, Function<String,String> function){
        return function.apply(str);
    }

    @Test
    public void test2(){
        List<Integer> list = getNum(10,()->(int)(Math.random()*100));
        list.stream().forEach(System.out::println);
    }

    //供给型接口
    public List<Integer> getNum(int num , Supplier<Integer> supplier){
        List<Integer> list = new ArrayList<>();
        for(int i=0;i<10;i++){
            list.add(supplier.get());
        }
        return list;
    }

    @Test
    public void test1(){
        happy(1000,(x)-> System.out.println("消费了"+x));
    }

    //消费型接口
    public void happy(double money, Consumer consumer){
        consumer.accept(money);
    }
}
