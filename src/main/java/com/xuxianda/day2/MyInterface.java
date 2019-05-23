package com.xuxianda.day2;

/**
 * @Author: xuxianda
 * @Date: 2019/5/23 15:24
 * @Version 1.0
 */
/*
    接口中可以写默认方法和静态方法
 */
public interface MyInterface {

    default String getName(){
        return "MyInterface";
    }

    public static Integer getAge(){
        return 28;
    }

}

