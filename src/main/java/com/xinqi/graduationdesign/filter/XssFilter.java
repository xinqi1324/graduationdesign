package com.xinqi.graduationdesign.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: xinqi
 * Date: 2017/2/11
 * Time: 11:07
 */
public class XssFilter implements Filter {
    FilterConfig filterConfig = null;

    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    public void destroy() {
        this.filterConfig = null;
    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        if(request.getParameterMap().size()>0) {
            chain.doFilter(new XssHttpServletRequestWrapper(
                    (HttpServletRequest) request), response);
        }else{
            chain.doFilter(request,response);
        }

    }
}