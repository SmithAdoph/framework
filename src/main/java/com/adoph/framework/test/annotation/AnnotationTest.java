package com.adoph.framework.test.annotation;

import java.lang.annotation.Annotation;

/**
 * 注解测试类
 *
 * @author Adoph
 * @version v1.0
 * @date 2018/5/31
 */
public class AnnotationTest {
    @org.junit.Test
    public void testInherited() {
        Annotation[] annotations = C.class.getAnnotations();
        for (Annotation att : annotations) {
            System.out.println(att.annotationType());
        }
//        Annotation[] declaredAnnotations = C.class.getDeclaredAnnotations();
//        for (Annotation att : declaredAnnotations) {
//            System.out.println(att.annotationType());
//        }

//        Annotation[] annotations1 = C.class.getFields()[0].getAnnotations();
//        for (Annotation att : annotations1) {
//            System.out.println(att.annotationType());
//        }

    }
}


@MyTest(name = "AClass")
@OtherTest(name = "aaa")
class A {
    @OtherTest(name = "aaa")
    private String text;
}
class C extends A {
    private String text;
}