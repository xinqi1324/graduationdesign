package com.xinqi.graduationdesign.filter;

import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * Created by IntelliJ IDEA.
 * User: xinqi
 * Date: 2017/2/11
 * Time: 11:06
 * 用于过滤传入的参数，将所有非法字符转换为转义字符
 */
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {
    public XssHttpServletRequestWrapper(HttpServletRequest servletRequest) {
        super(servletRequest);
    }
    public String[] getParameterValues(String parameter) {
        String[] values = super.getParameterValues(parameter);
        if (values==null)  {
            return null;
        }
        int count = values.length;
        String[] encodedValues = new String[count];
        for (int i = 0; i < count; i++) {
            encodedValues[i] = cleanXSS(values[i]);
        }
        return encodedValues;
    }
    public String getParameter(String parameter) {
        String value = super.getParameter(parameter);
        if (value == null) {
            return null;
        }
        return cleanXSS(value);
    }
    /*public String getHeader(String name) {
        String value = super.getHeader(name);
        if (value == null)
            return null;
        return cleanXSS(value);
    }*/
    private String cleanXSS(String value) {
        value = HtmlUtils.htmlEscape(value);
        return value;
    }

}