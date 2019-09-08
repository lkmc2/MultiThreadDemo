package com.lin.annotion;
import	java.lang.annotation.RetentionPolicy;
import	java.lang.annotation.Retention;
import	java.lang.annotation.ElementType;
import	java.lang.annotation.Target;

/**
 * 守护线程的对象
 * @author lkmc2
 * @date 2019/8/10 16:16
 */
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface GuardedBy {
    String value();
}
