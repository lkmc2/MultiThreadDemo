package com.lin.annotion;
import	java.lang.annotation.RetentionPolicy;
import	java.lang.annotation.Retention;
import	java.lang.annotation.ElementType;
import	java.lang.annotation.Target;

import java.lang.annotation.Documented;

/**
 * 线程安全注解
 * @author lkmc2
 * @date 2019/8/10 16:16
 */
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ThreadSafe {
}
