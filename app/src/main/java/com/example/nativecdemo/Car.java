package com.example.nativecdemo;

/**
 * ******************************
 * 项目名称:NativeCDemo1
 *
 * @Author zhangsen
 * 邮箱:zhangsen839705693@163.com
 * 创建时间:2022    6:07 下午
 * 说明:
 * ******************************
 */
public class Car {

    static {
        System.loadLibrary("nativecdemo-lib");
    }

    public native String getCarName();

    @Override
    public String toString() {
        return "super.toString()";
    }
}
