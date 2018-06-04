package com.adoph.framework.test.annotation;

import java.lang.annotation.*;

/**
 * TODO
 *
 * @author Adoph
 * @version v1.0
 * @since 2018/5/31
 */
//@Inherited
@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface OtherTest {
    String name() default "dududu";
}
