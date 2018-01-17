package com.mazaiting;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.TypeParameterElement;

/**
 * 每一个注解处理器类都必须有一个空的构造函数，默认不写就行
 */
@AutoService(Processor.class)
@SupportedSourceVersion(SourceVersion.RELEASE_7)
@SupportedAnnotationTypes("com.mazaiting.CustomAnnotation")
public class CustomProcessor extends AbstractProcessor{

    private Filer mFiler;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        mFiler = processingEnvironment.getFiler();
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        for (Element element : roundEnvironment.getElementsAnnotatedWith(CustomAnnotation.class)) {
            // 判断元素的类型为Class
            if (element.getKind() == ElementKind.CLASS) {
                // 显示转换元素类型
                TypeElement typeElement = (TypeElement) element;
                // 输出元素名称
//                System.out.println(typeElement.getSimpleName());
//                // 输出注解属性值
//                System.out.println(typeElement.getAnnotation(CustomAnnotation.class).value());
                // 创建main方法
                MethodSpec main =
                        MethodSpec.methodBuilder("main")
                                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                                .returns(void.class)
                                .addParameter(String[].class, "args")
                                .addStatement("$T.out.println($S)", System.class, "Hello, JavaPoet!")
                                .build();
                String value = element.getAnnotation(CustomAnnotation.class).value();
                String first = value.substring(0, 1);
                String className = first.toUpperCase() + value.substring(1, value.length());
                TypeSpec valueClass =
                        TypeSpec.classBuilder(className)
                                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                                .addMethod(main)
                                .build();
                // 生成文件
                try {
                    // 生成com.mazaiting.xxx.java
                    JavaFile javaFile =
                            JavaFile.builder("com.mazaiting.example", valueClass)
                                    .addFileComment("This codes are generated automatically. Do not modify!")
                                    .build();
                    javaFile.writeTo(mFiler);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
}
