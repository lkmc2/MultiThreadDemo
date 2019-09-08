package com.lin.ch03.escape;

import com.lin.ch03.escape.event.Event;
import com.lin.ch03.escape.event.EventSource;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * 使用工厂方法防止this引用在构造过程中逸出的测试
 * @author lkmc2
 * @date 2019/8/10 18:01
 */
public class SafeListenerTest {

    @Test
    public void test() {
        SafeListener safeListener = SafeListener.newInstance(new EventSource());
    }

}