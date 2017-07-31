package com.example.butterknifedemo.ButterKnife;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by 博 on 2017/7/30.
 */


@Target(ElementType.METHOD)                      //说明注解修饰的范围
@Retention(RetentionPolicy.RUNTIME)             //说明注解虚拟机范围运行
//@Retention(RetentionPolicy.CLASS)              //说明在源码范围运行
//@Retention(RetentionPolicy.SOURCE)             //说明在字节码范围运行

public @interface BindOnclick {

    int value() ;

}
