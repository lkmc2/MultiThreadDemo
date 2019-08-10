package com.lin.ch03.integer;

import com.lin.annotion.NotThreadSafe;

/**
 * 非线程安全的可变整数类
 * @author lkmc2
 * @date 2019/8/10 17:33
 */
@NotThreadSafe
public class MutableInteger {
    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
