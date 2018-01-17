package com.mazaiting;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;

/**
 * 每一个注解处理器类都必须有一个空的构造函数，默认不写就行
 */
public class CustomProcessor1 extends AbstractProcessor{
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
        return false;
    }

    /**
     * 这里必须指定，这个注解处理器是注册给那个注解的。
     * 注意：它的返回值是一个字符串的集合，包含本处理器想要处理注解的注解类型的合法全程。
     * @return 注解器所支持的注解类型集合，如果没有这样的类型，则返回一个空集合。
     */
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> annotations = new LinkedHashSet<>();
        annotations.add(CustomAnnotation.class.getCanonicalName());
        return annotations;
    }

    /**
     * 指定Java版本，通常这里使用SourceVersion.latestSupported(),
     * 默认返回SourceVersion.RELEASE_6
     * @return 使用的Java版本
     */
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }
}
