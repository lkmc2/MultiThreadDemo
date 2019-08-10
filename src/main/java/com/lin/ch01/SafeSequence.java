package com.lin.ch01;

import com.lin.annotion.GuardBy;
import com.lin.annotion.ThreadSafe;

/**
 * 线程安全的序列生成器
 * @author lkmc2
 * @date 2019/8/10 16:30
 */
@ThreadSafe
public class SafeSequence {

    @GuardBy("this")
    private int value;

    /** 返回一个唯一的数值，使用同步方法保证线程安全 **/
    public synchronized int getNext() {
        return value++;
    }

}
