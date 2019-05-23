package com.xuxianda.day2;

/**
 * @Author: xuxianda
 * @Date: 2019/5/23 15:26
 * @Version 1.0
 */
public class TestInterface /*extends MyClass*/ implements MyFun,MyInterface{



    public static void main(String[] args) {
        TestInterface testInterface = new TestInterface();
        System.out.println(testInterface.getName());
        System.out.println(MyFun.getAge());
    }

    @Override
    public String getName() {
        return "TestInterface";
    }
}

