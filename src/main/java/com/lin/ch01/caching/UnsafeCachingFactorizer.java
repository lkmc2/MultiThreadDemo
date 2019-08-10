package com.lin.ch01.caching;

import com.lin.annotion.NotThreadSafe;
import com.lin.ch01.servlet.Servlet;
import com.lin.ch01.servlet.ServletRequest;
import com.lin.ch01.servlet.ServletResponse;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 在没有足够原子性保证的情况下对其最近计算结果进行缓存的Servlet（不要这样做）
 * @author lkmc2
 * @date 2019/8/10 16:37
 */
@NotThreadSafe
public class UnsafeCachingFactorizer implements Servlet {

    /** 上次的请求数值 **/
    private final AtomicReference<BigInteger> lastNumber = new AtomicReference<BigInteger>();

    /** 上次请求数值计算出的因数分解结果 **/
    private final AtomicReference<BigInteger[]> lastFactors = new AtomicReference<BigInteger[]>();

    public void service(ServletRequest request, ServletResponse response) {
        BigInteger i = extractFromRequest(request);

        if (i.equals(lastNumber.get())) {
            // 数值与上次请求一致，从缓存获取结果
            encodeIntoResponse(response, lastFactors.get());
        } else {
            // 实时计算结果，并存入缓存
            BigInteger[] factors = factor(i);

            // 因为无法同时更新lastNumber和lastFactors两个变量，可能被其他线程更改，所以是非线程安全的
            lastNumber.set(i);
            lastFactors.set(factors);
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
