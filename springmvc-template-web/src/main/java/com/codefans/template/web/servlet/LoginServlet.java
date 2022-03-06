package com.codefans.template.web.servlet;

import com.codefans.template.common.util.DateUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

/**
 * @Author: codefans
 * @Date: 2022-03-05 9:05
 */

public class LoginServlet implements Servlet {

    @Override
    public void init(ServletConfig config) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        try {
            HttpServletRequest request = (HttpServletRequest) req;
            System.out.println("loginServlet.service(), uri=" + request.getRequestURI() + ", time=" + DateUtils.formatSSS(new Date()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
