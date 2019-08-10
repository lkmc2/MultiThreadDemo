package com.lin.ch02.caching;

import com.lin.annotion.GuardBy;
import com.lin.annotion.ThreadSafe;
import com.lin.ch02.servlet.Servlet;
import com.lin.ch02.servlet.ServletRequest;
import com.lin.ch02.servlet.ServletResponse;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * 使用 synchronized 块来缓存最新的计算结果的Servlet（线程安全，推荐）
 * @author lkmc2
 * @date 2019/8/10 16:37
 */
@ThreadSafe
public class CachedFactorizer implements Servlet {

    @GuardBy("this")
    private BigInteger lastNumber;

    @GuardBy("this")
    private BigInteger[] lastFactors;

    @GuardBy("this")
    private long hits;

    @GuardBy("this")
    private long cacheHits;

    public synchronized long getHits() {
        return hits;
    }

    public synchronized double getCacheHitRatio() {
        return (double) cacheHits / (double) hits;
    }

    public  void service(ServletRequest request, ServletResponse response) {
        BigInteger i = extractFromRequest(request);
        BigInteger[] factors = null;

        // 只对可能会被线程同时访问的变量加上同步代码块，效率比同步方法要好
        synchronized (this) {
            if (i.equals(lastNumber)) {
                // 数值与上次请求一致，从缓存获取结果
                ++cacheHits;
                factors = lastFactors.clone();
            }
        }

        if (factors == null)  {
            // 实时计算结果，并存入缓存
            factors = factor(i);

            synchronized (this) {
                // 因为使用了同步代码块，以下两个变量可以同时更新，线程安全
                lastNumber = i;
                lastFactors = factors.clone();
            }
        }

        encodeIntoResponse(response, factors);
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
