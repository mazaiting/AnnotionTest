package com.mazaiting;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;

/**
 * 每一个注解处理器类都必须有一个空的构造函数，默认不写就行
 */
@SupportedSourceVersion(SourceVersion.RELEASE_7)
@SupportedAnnotationTypes("com.mazaiting.CustomAnnotation")
public class CustomProcessor extends AbstractProcessor{
    /**
     * init()方法会被注解处理器工具调用，并输入ProcessingEnvironment参数。
     * ProcessingEnvironment 提供很多有用的工具类Elements，Types和Filter
     * @param processingEnvironment 提供给process用来访问工具框架的环境
     */
    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
    }

    /**
     * 相当于每个处理器的主函数main()，在这里写扫描、评估和处理注解的代码，以及生成java文件。
     * 输入参数RoundEnvironment可以查询出包含特定注解的被注解元素
     * @param set 请求处理注解类型
     * @param roundEnvironment 有关当前和以前的信息环境
     * @return 返回true，则这些注解已声明并且不要求后续Processor处理他们;
     *          返回false，则这些注解未声明并且可能要求后续Processor处理他们;
     */
    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        // roundEnvironment.getElementsAnnotatedWith(CustomAnnotation.class)返回使用给定的注解类型的元素
        for (Element element : roundEnvironment.getElementsAnnotatedWith(CustomAnnotation.class)) {
            System.out.println("----------------------------------");
            // 判断元素的类型为Class
            if (element.getKind() == ElementKind.CLASS) {
                // 显示转换元素类型
                TypeElement typeElement = (TypeElement) element;
                // 输出元素名称
                System.out.println(typeElement.getSimpleName());
                // 输出注解属性值
                System.out.println(typeElement.getAnnotation(CustomAnnotation.class).value());
            }
            System.out.println("----------------------------------");
        }
        return false;
    }
}
