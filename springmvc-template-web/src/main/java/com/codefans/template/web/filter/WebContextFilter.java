package com.codefans.template.web.filter;

import com.codefans.template.common.data.Code;
import com.codefans.template.common.data.Result;
import com.codefans.template.common.util.DateUtils;
import com.codefans.template.common.util.JsonUtils;
import com.codefans.template.common.util.ValidateUtil;
import com.codefans.template.web.client.WebContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class WebContextFilter implements Filter {
    private static final Logger LOGGER = LoggerFactory.getLogger(WebContextFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        try {
            System.out.println("WebContextFilter.doFilter() begin, uri=" + req.getRequestURI() + ", time=" + DateUtils.formatSSS(new Date()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Map<String, String[]> params =  req.getParameterMap();
        if (params != null && params.size() > 0) {
            for (Map.Entry<String, String[]> entry : params.entrySet()) {
                String name = entry.getKey();
                String[] values = entry.getValue();
                if (values != null && values.length > 0) {
                    for (String value : values) {
                        LOGGER.info("name:" + name + ",value:" + value);
                        if (ValidateUtil.isXSS(value)) {
                            response.setContentType("application/json; charset=utf-8");
                            Map<String, Object> map = new HashMap<>();
                            map.put("result", Result.FAILED.getValue());
                            map.put("code", Code.FAIL.getValue());
                            map.put("message", "非法请求");
                            resp.getWriter().write(JsonUtils.writeValue(map));
                            return;
                        }
                    }
                }

            }
        }
        WebContext.create(req, resp);
        long startTime = System.currentTimeMillis();
        chain.doFilter(request, response);
        long endTime = System.currentTimeMillis();
        LOGGER.info("requestUrl:" + req.getRequestURI() + ",cost:" + (endTime - startTime));
//        System.out.println("WebContextFilter.doFilter() end, time=" + new Date());
    }

    @Override
    public void destroy() {
        WebContext.clear();
    }

    public static void main(String args[]) throws UnsupportedEncodingException {
        Map<String, Object> map= new HashMap<>();
        map.put("result", Result.FAILED.getValue());
        map.put("code", Code.FAIL.getValue());
        map.put("message", "非法请求".getBytes("UTF-8"));
        System.out.println(JsonUtils.writeValue(map));
    }
    
}
