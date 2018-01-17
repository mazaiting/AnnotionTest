package com.mazaiting.annotiontest;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Arrays;

/**
 * Inherited使用
 * @author mazaiting
 * @date 2018/1/16
 */

public class InheritedAnnotation {
    @Inherited
    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface DocumentA {

    }

    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface DocumentB {

    }

    @InheritedAnnotation.DocumentA
    class A {
    }

    class B extends A {
    }

    @InheritedAnnotation.DocumentB
    class C {
    }

    class D extends C {
    }

    public void main() {
        A a = new B();
        System.out.println("已使用的@Inherited注解:" + Arrays.toString(a.getClass().getAnnotations()));

        C c = new D();
        System.out.println("没有使用的@Inherited注解:" + Arrays.toString(c.getClass().getAnnotations()));
    }

}
