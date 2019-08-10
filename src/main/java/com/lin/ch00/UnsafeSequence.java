package com.lin.ch00;

import com.lin.annotion.NotThreadSafe;

/**
 * 非线程安全的序列生成器
 * @author lkmc2
 * @date 2019/8/10 16:15
 */
@NotThreadSafe
public class UnsafeSequence {
    private int value;

    /** 返回一个唯一的数值 **/
    public int getNext() {
        return value++;
    }
}
