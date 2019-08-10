package com.lin.ch03.visibility;

/**
 * 表示变量对线程不可见的例子
 * @author lkmc2
 * @date 2019/8/10 17:27
 */
public class NoVisibility {

    private static boolean ready;
    private static int number;

    private static class ReaderThread extends Thread {
        @Override
        public void run() {
            // 未准备好时，当前线程让出CPU执行时间
            while (!ready) {
                Thread.yield();
            }
            System.out.println(number);
        }
    }

    public static void main(String[] args) {
        // 该线程可以无限执行下去，也可能输出0，因为读线程可能用于都看不到ready的值
        new ReaderThread().start();
        new ReaderThread().start();
        new ReaderThread().start();
        new ReaderThread().start();
        number = 42;
        ready = true;
    }

}
