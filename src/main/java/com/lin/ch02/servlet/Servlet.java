package com.lin.ch02.servlet;

/**
 * 模拟Servlet接口
 * @author lkmc2
 * @date 2019/8/10 16:35
 */
public interface Servlet {

    /**
     * 执行Servlet服务
     * @param request 请求
     * @param response 响应
     */
    void service(ServletRequest request, ServletResponse response);
}
