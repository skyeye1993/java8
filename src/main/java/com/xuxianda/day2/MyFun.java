package com.xuxianda.day2;

/**
 * @Author: xuxianda
 * @Date: 2019/5/23 15:20
 * @Version 1.0
 */
public interface MyFun {

    default String getName(){
        return "MyFun";
    }

    public static Integer getAge(){
        return 18;
    }

}

