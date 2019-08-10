package com.lin.ch01.stateless;

import com.lin.ch01.stateless.StatelessFactorizer;
import com.lin.ch01.servlet.ServletRequest;
import com.lin.ch01.servlet.ServletResponse;
import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 无状态的Servlet测试（无状态的对象是线程安全的）
 * @author lkmc2
 * @date 2019/8/10 16:44
 */
public class StatelessFactorizerTest {

    private StatelessFactorizer servlet = new StatelessFactorizer();

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