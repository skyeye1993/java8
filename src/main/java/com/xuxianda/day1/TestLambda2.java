package com.xuxianda.day1;

import org.junit.Test;

import java.util.Comparator;
import java.util.function.Consumer;

/**
 * lambda表达式的基础语法：Java8中引入了一个新的操作符"->"
 * 左侧：Lambda表达式的参数列表
 * 右侧：Lambda表达式中所需执行的功能，即Lambda体
 * 语法格式1：无参数，无返回值
 *             ()->System.out.println("");
 * 语法格式2：有一个参数，并且无返回值
 *             (x)->System.out.println(x);
 * 语法格式3：有一个参数，并且无返回值，（）可省略不写
 *             x->System.out.println(x);
 * 语法格式4：有两个以上参数，有返回值，并且Lambda体重有多条语句
 *      Comparator<Integer> com = (x,y)->{
            System.out.println("函数式接口");
            return Integer.compare(x,y);
        };
 * 语法格式5：若Lambda体重只有一条语句，return和大括号都可以省略不写
 *             Comparator<Integer> com = (x,y)->Integer.compare(x,y);
 *
 * 二、lambda表达式需要函数式接口的支持
 *     函数式接口：接口中只有一个抽象方法的接口，称为函数式接口。可以使用注解 @FunctionalInterface检查接口是否是函数式接口
 */
public class TestLambda2 {

    @Test
    public void test1() throws Exception{
        final int num=10;
        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("runnable1:"+num);
            }
        };
        runnable1.run();
        System.out.println("--------------------------------");
        Runnable runnable2 = ()->{
            System.out.println("runnable1");
        };
        runnable2.run();
    }

    @Test
    public void test2(){
        Consumer<String> tConsumer = x -> System.out.println(x);
        tConsumer.accept("qwertyui");
    }

    @Test
    public void test3(){
        Comparator<Integer> com = (x,y)->{
            System.out.println("函数式接口");
            return Integer.compare(x,y);
        };
        com.compare(1,2);
    }

    @Test
    public void test4(){
        Comparator<Integer> com = (x,y)->Integer.compare(x,y);
        com.compare(1,2);
    }


    @Test
    public void test5(){
        int num = getOperation(10,x->x*x);
        System.out.println(num);
    }

    @Test
    public void test6(){
        System.out.println(getOperation(" 1 234 55 ",x->x.trim()));
    }

    public Integer getOperation(Integer num,MyFun<Integer> mf){
        return mf.getValue(num);
    }

    public String getOperation(String str,MyFun<String> mf){
        return mf.getValue(str);
    }

}
