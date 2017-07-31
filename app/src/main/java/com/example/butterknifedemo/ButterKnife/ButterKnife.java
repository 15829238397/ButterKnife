package com.example.butterknifedemo.ButterKnife;

import android.app.Activity;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

/**
 * Created by 博 on 2017/7/30.
 */

public class ButterKnife {

    public static void bind(Activity activity)  {

        try {
            bindView( activity ) ;
            bindOnclick( activity ) ;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }


    private static void bindOnclick(final Activity activity) {

        //获取字节码
        final Class<? extends Activity> clazz = activity.getClass() ;

        //获取所有方法
        final Method[] methods = clazz.getDeclaredMethods();

        //遍历所有方法
        for (final Method method : methods) {

            //获取注解
            BindOnclick bindOnclick = method.getAnnotation(BindOnclick.class) ;

            if (bindOnclick != null){

                //取得注解的值
                int resId = bindOnclick.value() ;

                //添加点击事件
                View view = activity.findViewById( resId ) ;
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            //暴力反射
                            method.setAccessible(true);

                            method.invoke( activity , v ) ;

                            //关闭暴力反射
                            method.setAccessible(false);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }else {
                //如果注解为空什么都不做
            }
        }
    }

    /**
     *
     * 代替findById绑定View控件与变量
     * @param activity
     * @throws IllegalAccessException
     */
    private static void bindView(Activity activity) throws IllegalAccessException {
        
        //获取字节码
        Class<? extends Activity> clazz = activity.getClass() ;

        //获取所有成员
        Field[] fields =  clazz.getDeclaredFields() ;

        //遍历所有成员
        for (int i = 0; i < fields.length ; i++) {
            //获取成员注解
            BindView bindView = fields[i].getAnnotation(BindView.class) ;
            if (bindView != null){

                //暴力反射
                fields[i].setAccessible(true);

                //取得注解的值
                int resId = bindView.value() ;
                //给该参数赋值
                View view = activity.findViewById(resId) ;
                fields[i].set(activity , view);

                //关闭暴力反射
                fields[i].setAccessible(false);

            }else {
                //如果注解为空什么都不做
            }

        }
    }

}
