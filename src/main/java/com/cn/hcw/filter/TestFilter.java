package com.cn.hcw.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by hechengwen on 2017/3/29 0029.
 */
@Slf4j
public class TestFilter implements Filter {

    public FilterConfig config;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("filter过滤器init()方法只会在项目启动的时候执行一次！！------filter init,filterConfig = {}",filterConfig);
        config = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String test = config.getInitParameter("test");
        HttpServletRequest req =  (HttpServletRequest) request;
        String s = req.getRequestURL().toString();
        log.info("test={},path={}  filter dofilter",test,s);
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {
        log.info("filter过滤器destroy()方法只会在项目启动的时候执行一次！！-----------------filter destroy");
        config = null;
    }
}
