package com.lin.ch01;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * 非线程安全的序列生成器测试
 * @author lkmc2
 * @date 2019/8/10 16:18
 */
public class UnsafeSequenceTest {

    private UnsafeSequence sequence;

    @Before
    public void before() {
        sequence = new UnsafeSequence();
    }

    private Runnable task = new Runnable() {
        public void run() {
            System.out.println(sequence.getNext());
        }
    };

    @Test
    public void getNext() throws InterruptedException {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(5, 10, 1, TimeUnit.MINUTES, new ArrayBlockingQueue<Runnable>(100));

        for (int i = 0; i < 50; i++) {
            pool.execute(task);
        }

        // 等待线程池执行完成
        pool.awaitTermination(3, TimeUnit.SECONDS);
    }

}