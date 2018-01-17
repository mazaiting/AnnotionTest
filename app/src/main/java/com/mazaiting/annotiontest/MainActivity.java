package com.mazaiting.annotiontest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.mazaiting.CustomAnnotation;
import com.mazaiting.example.HelloWorld;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

/**编译时注解*/
@CustomAnnotation("HelloWorld")
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView =  (TextView) findViewById(R.id.textView);
        // 报错Must be one of: SourceAnnotation.STATUS_OPEN, SourceAnnotation.STATUS_CLOSE
//        SourceAnnotation.setStatus(1);
        /**源码注解*/
        SourceAnnotation.setStatus(SourceAnnotation.STATUS_OPEN);
        /**运行时注解*/
        textView.setText(parse());
        /**测试Inherited*/
        new InheritedAnnotation().main();
    }

    /**
     * 解析运行时注解
     */
    private String parse() {
        // 创建一个线程安全的字符串拼接对象
        StringBuffer sb = new StringBuffer();
        // 获取类字节码
        Class cls = TestRuntimeAnnotation.class;
        // 获取构造方法
        Constructor[] constructors = cls.getConstructors();
        // 获取指定类型的注解
        // 获取ClassInfo注解
        sb.append("Class注解：").append("\n");
        RuntimeAnnotation.ClassInfo classInfo =
                (RuntimeAnnotation.ClassInfo) cls.getAnnotation(RuntimeAnnotation.ClassInfo.class);
        if (null != classInfo) {
            // 拼接访问修饰符和类名
            sb.append(Modifier.toString(cls.getModifiers())).append(" ")
                    .append(cls.getSimpleName()).append("\n");
            sb.append("注解值：").append(classInfo.value()).append("\n\n");
        }

        // 获取FieldInfo注解
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            RuntimeAnnotation.FieldInfo fieldInfo = field.getAnnotation(RuntimeAnnotation.FieldInfo.class);
            if (null != fieldInfo) {
                // 拼接访问修饰符，类型名，字段名
                sb.append(Modifier.toString(field.getModifiers())).append(" ")
                        .append(field.getType().getSimpleName()).append(" ")
                        .append(field.getName()).append("\n");
                sb.append("注解值：").append(Arrays.toString(fieldInfo.value())).append("\n\n");
            }
        }

        // 获取MethodInfo注解
        sb.append("Method注解：").append("\n");
        Method[] methods = cls.getDeclaredMethods();
        for (Method method: methods) {
            RuntimeAnnotation.MethodInfo methodInfo = method.getAnnotation(RuntimeAnnotation.MethodInfo.class);
            if (null != methodInfo) {
                // 拼接访问修饰符、返回值类型、方法名
                sb.append(Modifier.toString(method.getModifiers())).append(" ")
                        .append(method.getReturnType().getSimpleName()).append(" ")
                        .append(method.getName()).append("\n");
                // 注解值
                sb.append("注解值：").append("\n");
                sb.append("name: ").append(methodInfo.name()).append("\n");
                sb.append("data: ").append(methodInfo.data()).append("\n");
                sb.append("age: ").append(methodInfo.age()).append("\n");
            }
        }
        return sb.toString();
    }
}
