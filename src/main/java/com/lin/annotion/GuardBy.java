package com.lin.annotion;

/**
 * 守护线程的对象
 * @author lkmc2
 * @date 2019/8/10 16:16
 */
public @interface GuardBy {
    String value() default "";
}
