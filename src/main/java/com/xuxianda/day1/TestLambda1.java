package com.xuxianda.day1;


import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestLambda1 {

    List<Employee> emps = Arrays.asList(
            new Employee("张三",15,1111.111f),
            new Employee("李四",25,2222.222f),
            new Employee("王五",35,3333.333f),
            new Employee("赵六",45,4444.444f),
            new Employee("田七",55,5555.555f)
    );

    //策略模式
    public  List<Employee> filterEmployee(List<Employee> list , MyPredicate<Employee> mp){
        List<Employee> empList = new ArrayList<Employee>();
        for (Employee e : list){
            if(mp.test(e)){
                empList.add(e);
            }
        }
        return empList;
    }

    @Test
    public void test1(){
        List<Employee> empList = filterEmployee(emps, new MyPredicate<Employee>() {
            @Override
            public boolean test(Employee employee) {
                if (employee.getSalary() > 3000) {
                    return true;
                }
                return false;
            }
        });
        for(Employee e : empList){
            System.out.println(e);
        }
    }

    @Test
    public void test2(){
        List<Employee> list1 = filterEmployee(emps, (e) -> e.getAge() <= 35);
        list1.forEach(System.out::println);

        System.out.println("------------------------------------------");

        List<Employee> list2 = filterEmployee(emps, (e) -> e.getSalary() >= 5000);
        list2.forEach(System.out::println);
    }

    //优化方式四：Stream API
    @Test
    public void test3(){
        emps.stream()
                .filter((e) -> e.getAge()>30)
                .forEach(System.out::println);

        System.out.println("----------------------------------------------");

        emps.stream()
                .map(Employee::getName)
                .limit(3)
                .sorted()
                .forEach(System.out::println);
    }

}
