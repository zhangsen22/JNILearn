package com.example.nativecdemo;

/**
 * ******************************
 * 项目名称:JNILearn
 *
 * @Author zhangsen
 * 邮箱:zhangsen839705693@163.com
 * 创建时间:2022    10:47 上午
 * 说明: 动态注册
 * ******************************
 */
public class JNITools {
    static {
        System.loadLibrary("jnitools");
    }

    public static native String sayHello(int num, String str, int num2);

    public String sayHelloMethod(int num1, String str, int num2) {
        return "帅气的"+str + " 年龄：" + num1 + " 成绩为：" + num2;
    }

    //加法
    public static native int  add(int a,int b);

    //减法
    public static native int sub(int a,int b);

    //乘法
    public static native int mul(int a,int b);

    //除法
    public static native int div(int a,int b);
}
