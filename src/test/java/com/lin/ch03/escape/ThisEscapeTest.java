package com.lin.ch03.escape;

import com.lin.ch03.escape.event.EventSource;
import org.junit.Test;

/**
 * 引用逸出的例子测试
 * @author lkmc2
 * @date 2019/8/10 17:55
 */
public class ThisEscapeTest {

    @Test
    public void test() {
        ThisEscape thisEscape = new ThisEscape(new EventSource());
    }

}