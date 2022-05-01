package com.gmail.evanloafakahaitao.computer.store.servlets.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDateTime;

public class LogFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("Logging Filter initialized");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        System.out.printf(
                "#LOG_INFO %s %s URL = %s%s%n",
                LocalDateTime.now(),
                request.getHeader("User-Agent"),
                request.getRequestURL(),
                (request.getQueryString() == null) ? "" : "?" + request.getQueryString()
        );
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        System.out.println("Logging Filter destroyed");
    }
}
