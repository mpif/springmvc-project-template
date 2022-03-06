package com.codefans.template.web.filter;

import com.codefans.template.common.util.DateUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

/**
 * @Author: codefans
 * @Date: 2022-03-05 8:49
 */

public class HeaderCheckFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        try {
            System.out.println("HeaderCheckFilter.doFilter() begin, uri=" + req.getRequestURI() + ", time=" + DateUtils.formatSSS(new Date()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        chain.doFilter(request, response);
//        System.out.println("HeaderCheckFilter.doFilter() end, time=" + new Date());
    }

    @Override
    public void destroy() {

    }
}
