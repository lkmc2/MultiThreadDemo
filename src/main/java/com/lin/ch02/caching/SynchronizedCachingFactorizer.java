package com.lin.ch02.caching;

import com.lin.annotion.GuardedBy;
import com.lin.annotion.ThreadSafe;
import com.lin.ch02.servlet.Servlet;
import com.lin.ch02.servlet.ServletRequest;
import com.lin.ch02.servlet.ServletResponse;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * 使用 synchronized 方法来缓存最新的计算结果，但并发性非常糟糕的Servlet（线程安全，但不要这么做）
 * @author lkmc2
 * @date 2019/8/10 16:37
 */
@ThreadSafe
public class SynchronizedCachingFactorizer implements Servlet {

    @GuardedBy("this")
    private BigInteger lastNumber;

    @GuardedBy("this")
    private BigInteger[] lastFactors;

    public synchronized void service(ServletRequest request, ServletResponse response) {
        BigInteger i = extractFromRequest(request);

        if (i.equals(lastNumber)) {
            // 数值与上次请求一致，从缓存获取结果
            encodeIntoResponse(response, lastFactors);
        } else {
            // 实时计算结果，并存入缓存
            BigInteger[] factors = factor(i);

            // 因为使用了同步方法，以下两个变量可以同时更新，线程安全，但性能可能不是很好
            lastNumber = i;
            lastFactors = factors;
            encodeIntoResponse(response, factors);
        }
    }

    /**
     * 将因数分解的结果编码到响应中
     * @param response 响应
     * @param factors 因数分解的结果
     */
    private void encodeIntoResponse(ServletResponse response, BigInteger[] factors) {
        System.out.println(Arrays.toString(factors));
    }

    /**
     * 因数分解
     * @param i 数值
     * @return 因数分解后的数组
     */
    private BigInteger[] factor(BigInteger i) {
        return new BigInteger[]{
                new BigInteger("1"),
                new BigInteger("3"),
                new BigInteger("5"),
                new BigInteger("7"),
                new BigInteger("9")
        };
    }

    /**
     * 从请求中获取数值
     * @param request 请求
     * @return 数值
     */
    private BigInteger extractFromRequest(ServletRequest request) {
        return new BigInteger("10");
    }
}
