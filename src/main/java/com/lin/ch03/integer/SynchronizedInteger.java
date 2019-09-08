package com.lin.ch03.integer;

import com.lin.annotion.GuardedBy;
import com.lin.annotion.ThreadSafe;

/**
 * 线程安全的可变整数类（使用synchronized方法保证线程安全）
 * @author lkmc2
 * @date 2019/8/10 17:33
 */
@ThreadSafe
public class SynchronizedInteger {
    @GuardedBy("this")
    private int value;

    public synchronized int getValue() {
        return value;
    }

    public synchronized void setValue(int value) {
        this.value = value;
    }
}
