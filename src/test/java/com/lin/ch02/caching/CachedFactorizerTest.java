package com.lin.ch02.caching;

import com.lin.ch02.servlet.ServletRequest;
import com.lin.ch02.servlet.ServletResponse;
import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 使用 synchronized 块来缓存最新的计算结果的Servlet测试（线程安全，推荐）
 * @author lkmc2
 * @date 2019/8/10 17:23
 */
public class CachedFactorizerTest {

    private CachedFactorizer servlet = new CachedFactorizer();

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