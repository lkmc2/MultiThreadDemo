package com.lin.ch03.integer;

import org.junit.Before;
import org.junit.Test;

/**
 * 线程安全的可变整数类测试（使用synchronized方法保证线程安全）
 * @author lkmc2
 * @date 2019/8/10 17:47
 */
public class SynchronizedIntegerTest {

    private SynchronizedInteger integer = new SynchronizedInteger();

    @Before
    public void setUp() {
        integer.setValue(0);
    }

    @Test
    public void test() throws InterruptedException {
        for (int i = 0; i < 50; i++) {
            MyThread thread = new MyThread("Thread-" + i, i);
            thread.start();
            thread.join();
        }
    }

    private class MyThread extends Thread {
        private int number;

        MyThread(String name, int number) {
            super(name);
            this.number = number;
        }

        @Override
        public void run() {
            integer.setValue(number);
            System.out.println(String.format("%s: getValue = %s", this.getName(), integer.getValue()));
        }
    }

}