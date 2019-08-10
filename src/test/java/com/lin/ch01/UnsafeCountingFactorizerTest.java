package com.lin.ch01;

import com.lin.ch01.servlet.ServletRequest;
import com.lin.ch01.servlet.ServletResponse;
import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * 在没有同步的情况下统计已处理请求数量的Servlet测试（不要这样做）
 * @author lkmc2
 * @date 2019/8/10 16:51
 */
public class UnsafeCountingFactorizerTest {

    private UnsafeCountingFactorizer servlet = new UnsafeCountingFactorizer();

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