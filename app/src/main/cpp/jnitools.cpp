//
// Created by zhangsen61 on 2022/1/13.
//

#include <jni.h>
#include <stdio.h>
#include <stdlib.h>
#include <android/log.h>

#define TAG "learn JNI" // 这个是自定义的LOG的标识
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG,TAG ,__VA_ARGS__) // 定义LOGD类型
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO,TAG ,__VA_ARGS__) // 定义LOGI类型
#define LOGW(...) __android_log_print(ANDROID_LOG_WARN,TAG ,__VA_ARGS__) // 定义LOGW类型
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR,TAG ,__VA_ARGS__) // 定义LOGE类型
#define LOGF(...) __android_log_print(ANDROID_LOG_FATAL,TAG ,__VA_ARGS__) // 定义LOGF类型

using namespace std;

#ifdef __cplusplus
extern "C" {
#endif

static const char *className = "com/example/nativecdemo/JNITools";

static jstring sayHello(JNIEnv *env, jobject instance, jint num,
                     jstring str_, jint num2) {
    LOGE("JNI 动态注册  %s\n", "native: say hello ###");//简约型
    //获取java传递的参数
    const char *strCont = env->GetStringUTFChars(str_, 0);
    //通过反射获取java类
    jclass ccallj = env->FindClass("com/example/nativecdemo/JNITools");

    //实例化该类
    jobject jobj = env->AllocObject(ccallj);
    //得到方法id
    //参数列表：反射类，方法名称，方法签名;
    //I:int类类型签名，Ljava/lang/String;是String的签名
    jmethodID methodId = env->GetMethodID(ccallj, "sayHelloMethod",
                                          "(ILjava/lang/String;I)Ljava/lang/String;");
    //调用方法
    jstring result = (jstring) env-> CallObjectMethod(jobj, methodId, num,env->NewStringUTF(strCont), num2);
    env->ReleaseStringUTFChars(str_, strCont);
    return result;
}


jint addNumber(JNIEnv *env,jclass clazz,jint a,jint b){
    return a+b;
}

jint subNumber(JNIEnv *env,jclass clazz,jint a,jint b){
    return a-b;
}

jint mulNumber(JNIEnv *env,jclass clazz,jint a,jint b){
    return a*b;
}

jint divNumber(JNIEnv *env,jclass clazz,jint a,jint b){
    return a/b;
}

static JNINativeMethod gJni_Methods_table[] = {
        {"sayHello", "(ILjava/lang/String;I)Ljava/lang/String;", (void *) sayHello},
        {"add","(II)I",(void*)addNumber},
        {"sub","(II)I",(void*)subNumber},
        {"mul","(II)I",(void*)mulNumber},
        {"div","(II)I",(void*)divNumber}
};

static int jniRegisterNativeMethods(JNIEnv *env, const char *className,
                                    const JNINativeMethod *gMethods, int numMethods) {
    jclass clazz;

    LOGE("JNI 动态注册 Registering  %s natives\n", className);//简约型

    clazz = env->FindClass(className);
    if (clazz == NULL) {
        LOGE("JNI Native registration unable to find class '%s'\n", className);//简约型
        return -1;
    }

    int result = 0;
    if ((env)->RegisterNatives(clazz, gJni_Methods_table, numMethods) < 0) {
        LOGE("JNI RegisterNatives failed for '%s'\n", className);//简约型
        result = -1;
    }

    (env)->DeleteLocalRef(clazz);
    return result;
}

jint JNI_OnLoad(JavaVM *vm, void *reserved) {

    LOGE("JNI 动态注册  %s\n", "enter jni_onload");//简约型

    JNIEnv *env = NULL;
    jint result = -1;

    if (vm->GetEnv((void **) &env, JNI_VERSION_1_6) != JNI_OK) {
        return result;
    }

    jniRegisterNativeMethods(env, className, gJni_Methods_table,
                             sizeof(gJni_Methods_table) / sizeof(JNINativeMethod));

    return JNI_VERSION_1_6;
}

#ifdef __cplusplus
}
#endif
