package com.xuxianda;

import org.junit.Test;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 方法引用：若Lambda体重的内容方法已经实现了，我们可以使用“方法引用”
 *            （可以理解为方法引用是Lambda表达式的表现形式）
 * 主要有三种语法格式：
 * 对象::实例方法名
 * 类::静态方法名
 * 类::实例方法名
 *
 * 注意：
 * 1、Lambda体重调用方法的参数列表与返回值类型，要与函数式接口中抽象方法的函数列表和返回值类型保持一致
 * 2、若Lamdba参数列表中的第一参数是实例方法的调用者，二第二个参数是实例方法的参数是，可以使用ClassName::方法名
 *
 * 二：构造器引用：
 * ClassName:new
 * 注意：需要调用的构造器的参数列表与函数式接口中抽象方法的参数列表保持一致！
 *
 * 三：数组引用
 *  Type[]::new
 */
public class TestMethodRef {

    @Test
    public void test1(){
        PrintStream ps1 = System.out;
        Consumer<String> consumer1 = (x)-> System.out.println(x);

        PrintStream ps2 = System.out;
        Consumer<String> consumer2 = ps2::print;

        Consumer<String> consumer3 = System.out::println;
        consumer3.accept("1111");
    }

    @Test
    public void test2(){
        Employee emp = new Employee("张三",111,222f);

        Supplier<String> s1 = ()->emp.getName();
        System.out.println(s1.get());

        Supplier<Integer> s2 = emp::getAge;
        System.out.println(s2.get());
    }

    @Test
    public void test3(){
        Comparator<Integer> comparator1 = (x,y)->Integer.compare(x,y);
        System.out.println(comparator1.compare(1,2));

        Comparator<Integer> comparator2 = Integer::compare;
        System.out.println(comparator2.compare(1,2));

    }

    public void test4(){
        BiPredicate<String,String> bp1 = (x,y)->x.equals(y);
        BiPredicate<String,String> bp2 = String::equals;
    }

    @Test
    public void test5(){
        Supplier<Employee> supplier = ()->new Employee();
        Supplier<Employee> supplier1 = Employee::new;
        System.out.println(supplier1.get());
    }

    @Test
    public void test6(){
        Function<Integer,Employee> function1 = (x)->new Employee(x);
        System.out.println(function1.apply(20));
        Function<Integer,Employee> function2=Employee::new;
        System.out.println(function2.apply(30));
    }

    @Test
    public void test7(){
        Function<Integer,String[]> function1 = (x)->new String[x];
        System.out.println(function1.apply(10).length);
        Function<Integer,String[]> function2 = String[]::new;
        System.out.println(function2.apply(20).length);
    }

}

