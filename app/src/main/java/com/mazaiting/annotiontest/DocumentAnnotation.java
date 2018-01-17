package com.mazaiting.annotiontest;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Documented使用
 * @author mazaiting
 */

public class DocumentAnnotation {
    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    public @interface DocumentA{

    }


    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    public @interface DocumentB{

    }

    @DocumentA
    public class TestDocumentA{
        public void a(){

        }
    }

    @DocumentB
    public class TestDocumentB{
        public void b(){

        }
    }
}
