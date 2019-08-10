package com.lin.ch01.caching;

import com.lin.ch01.caching.UnsafeCachingFactorizer;
import com.lin.ch01.servlet.ServletRequest;
import com.lin.ch01.servlet.ServletResponse;
import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 在没有足够原子性保证的情况下对其最近计算结果进行缓存的Servlet测试（不要这样做）
 * @author lkmc2
 * @date 2019/8/10 16:51
 */
public class UnsafeCachingFactorizerTest {

    private UnsafeCachingFactorizer servlet = new UnsafeCachingFactorizer();

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