package com.lin.ch01.counting;

import com.lin.annotion.NotThreadSafe;
import com.lin.annotion.ThreadSafe;
import com.lin.ch01.servlet.Servlet;
import com.lin.ch01.servlet.ServletRequest;
import com.lin.ch01.servlet.ServletResponse;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * 在没有同步的情况下统计已处理请求数量的Servlet（不要这样做）
 * @author lkmc2
 * @date 2019/8/10 16:37
 */
@NotThreadSafe
public class UnsafeCountingFactorizer implements Servlet {

    private long count = 0;

    public long getCount() {
        return count;
    }

    public void service(ServletRequest request, ServletResponse response) {
        BigInteger i = extractFromRequest(request);
        BigInteger[] factors = factor(i);
        // 此处的自增会被分成 取值、增加、赋值 三个步骤，当被多个线程访问时时不安全的
        count++;
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
