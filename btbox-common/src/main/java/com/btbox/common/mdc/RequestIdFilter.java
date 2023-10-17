package com.btbox.common.mdc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import jakarta.servlet.*;
import java.io.IOException;

/**
 * MDC Filter
 */
@Slf4j
@Component
public class RequestIdFilter implements Filter {
 
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        RequestIdUtil.setRequestId();
        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            RequestIdUtil.clear();
        }
    }
}