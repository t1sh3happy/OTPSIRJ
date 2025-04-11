package org.Evgeniy.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class RequestLoggingFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(RequestLoggingFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Инициализация фильтра, если требуется
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        long startTime = System.currentTimeMillis();

        logger.info("Incoming request: {} {}", req.getMethod(), req.getRequestURI());


        req.getHeaderNames().asIterator().forEachRemaining(headerName ->
                logger.info("Header: {} = {}", headerName, req.getHeader(headerName))
        );


        chain.doFilter(request, response);


        long duration = System.currentTimeMillis() - startTime;
        logger.info("Completed processing request in {} ms", duration);
    }

    @Override
    public void destroy() {

    }
}