package com.arano.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * TranCode
 * 标记tranCode用于处理uri
 *
 * @author arano
 * @since 2021/7/23 14:18
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TranCode {
    @AliasFor("tranCode")
    String value() default "";

    @AliasFor("value")
    String tranCode() default "";

}
