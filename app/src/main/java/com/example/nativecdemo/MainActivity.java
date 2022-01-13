package com.example.nativecdemo;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import butterknife.OnClick;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private TestJNIBean mTestJNIBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_recyclerview_activity);
        ButterKnife.bind(this);
        mTestJNIBean = new TestJNIBean();
    }

    @OnClick({R.id.jni_log,R.id.jni_hello,R.id.jni_date,R.id.jni_static_random,R.id.jni_random,R.id.jni_method,R.id.jni_array
    ,R.id.jni_more_param,R.id.jni_duo_tai,R.id.jni_string,R.id.jni_exception,R.id.jni_dongtai,R.id.jni_dongtai_more_param})
    void submit(View view) {
        switch (view.getId()){
            case R.id.jni_hello:
                Toast.makeText(this, new Car().getCarName(), Toast.LENGTH_LONG).show();
                break;
            case R.id.jni_log:
                TestJNIBean.testLogInJNI();
                break;
            case R.id.jni_date:
                long newJavaDate = mTestJNIBean.testNewJavaDate();
                Date myDate = new Date(newJavaDate);
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                Toast.makeText(this, df.format(myDate), Toast.LENGTH_LONG).show();
                break;
            case R.id.jni_static_random:
                long newJavaDateStatic = TestJNIBean.testNewStaticRandomParam(100);
                Toast.makeText(this, "JNI中获取的随机数为："+String.valueOf(newJavaDateStatic), Toast.LENGTH_LONG).show();
                break;
            case R.id.jni_random:
                int testNewRandomParam = mTestJNIBean.testNewRandomParam(100);
                Toast.makeText(this, "JNI中获取的随机数为："+String.valueOf(testNewRandomParam), Toast.LENGTH_LONG).show();
                break;
            case R.id.jni_method:
                String staticCallMethod = mTestJNIBean.testStaticCallMethod();
                String staticCallStaticMethod = mTestJNIBean.testStaticCallStaticMethod();
                Toast.makeText(this, "JNI调用Java中的方法：非静态结果："+ mTestJNIBean.testCallMethod()
                        + "\n 静态结果：" + staticCallMethod
                        + "\n JNI中调用Java中的静态方法 = "  + staticCallStaticMethod, Toast.LENGTH_LONG).show();
                break;
            case R.id.jni_array:
                mTestJNIBean.testGetTArrayElement();
                break;
            case R.id.jni_more_param:
                String methodParamList = mTestJNIBean.testCallMethodParamList2(18, "aserbao", 100);
                Toast.makeText(this, methodParamList, Toast.LENGTH_LONG).show();
                break;
            case R.id.jni_duo_tai:
                String callChildMethod = mTestJNIBean.testCallChildMethod();
                String callFatherMethod = mTestJNIBean.testCallFatherMethod();
                Toast.makeText(this, "JNI调用子类对象的方法："+ callChildMethod + "\n 调用父类对象方法的结果为：" + callFatherMethod, Toast.LENGTH_LONG).show();
                break;
            case R.id.jni_string:
                String aserbao = TestJNIBean.testChangeString("aserbao");
                Toast.makeText(this, aserbao, Toast.LENGTH_LONG).show();
                break;
            case R.id.jni_exception:
                mTestJNIBean.testThrowException();
                break;
            case R.id.jni_dongtai:
                Toast.makeText(this, JNITools.add(18, 100)+"", Toast.LENGTH_LONG).show();
                break;
            case R.id.jni_dongtai_more_param:
                Toast.makeText(this, JNITools.sayHello(18, "aserbao", 100), Toast.LENGTH_LONG).show();
                break;
        }
    }
}
