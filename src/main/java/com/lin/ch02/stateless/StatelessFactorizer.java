package com.lin.ch02.stateless;

import com.lin.annotion.ThreadSafe;
import com.lin.ch02.servlet.Servlet;
import com.lin.ch02.servlet.ServletRequest;
import com.lin.ch02.servlet.ServletResponse;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * 无状态的Servlet（无状态的对象是线程安全的）
 * @author lkmc2
 * @date 2019/8/10 16:37
 */
@ThreadSafe
public class StatelessFactorizer implements Servlet {
    public void service(ServletRequest request, ServletResponse response) {
        BigInteger i = extractFromRequest(request);
        BigInteger[] factors = factor(i);
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
