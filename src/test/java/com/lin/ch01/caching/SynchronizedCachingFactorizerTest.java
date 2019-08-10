package com.lin.ch01.caching;

import com.lin.ch01.servlet.ServletRequest;
import com.lin.ch01.servlet.ServletResponse;
import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * 使用 synchronized 方法来缓存最新的计算结果，但并发性非常糟糕的Servlet测试（线程安全，但不要这么做）
 * @author lkmc2
 * @date 2019/8/10 17:15
 */
public class SynchronizedCachingFactorizerTest {

    private SynchronizedCachingFactorizer servlet = new SynchronizedCachingFactorizer();

    private Runnable task = new Runnable() {
        public void run() {
            servlet.service(new ServletRequest(), new ServletResponse());
        }
    };

    @Test
    public void service() throws InterruptedException {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(5, 10, 1, TimeUnit.MINUTES, new ArrayBlockingQueue<Runnable>(100));

        for (int i = 0; i < 50; i++) {
            pool.execute(task);
        }

        // 等待线程池执行完成
        pool.awaitTermination(3, TimeUnit.SECONDS);
    }
}