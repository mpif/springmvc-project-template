package com.codefans.template.web.interceptor;

import com.codefans.template.common.util.DateUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * @Author: codefans
 * @Date: 2022-03-05 8:44
 */

public class InputInterceptor implements HandlerInterceptor {

    /**
     *
     */
    private List<String> exceptUrls;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        boolean allowPass = allowPass(uri);
        System.out.println("InputInterceptor.preHandle(), uri=" + uri + ", allowPass=" + allowPass + ", time=" + DateUtils.formatSSS(new Date()));
        return allowPass;
    }

    /**
     * 是否允许uri通过
     * @param uri
     * @return
     */
    private boolean allowPass(String uri) {
        boolean allowPass = false;
        for(String str : exceptUrls) {
            if(uri.startsWith(str)) {
                allowPass = true;
                break;
            }
        }
        return allowPass;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("InputInterceptor.postHandle(), time=" + new Date());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    public List<String> getExceptUrls() {
        return exceptUrls;
    }

    public void setExceptUrls(List<String> exceptUrls) {
        this.exceptUrls = exceptUrls;
    }
}
