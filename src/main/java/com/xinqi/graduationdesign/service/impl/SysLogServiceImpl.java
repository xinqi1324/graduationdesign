package com.xinqi.graduationdesign.service.impl;

import com.xinqi.framework.aop.LogPoint;
import com.xinqi.kisso.SSOHelper;
import com.xinqi.kisso.Token;
import com.xinqi.kisso.common.IpHelper;
import com.xinqi.graduationdesign.common.HttpHelper;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Service;

import com.xinqi.graduationdesign.mapper.SysLogMapper;
import com.xinqi.graduationdesign.entity.SysLog;
import com.xinqi.graduationdesign.service.ISysLogService;
import com.xinqi.graduationdesign.service.support.BaseServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Enumeration;

/**
 *
 * SysLog 表数据服务层接口实现类
 *
 */
@Service
public class SysLogServiceImpl extends BaseServiceImpl<SysLogMapper, SysLog> implements ISysLogService, LogPoint {

    private static final String LOG_CONTENT = "[类名]:%s,[方法]:%s,[参数]:%s,[IP]:%s";

    @Override
    public void saveLog(ProceedingJoinPoint joinPoint, String methodName, String operate) {
        /**
         * 日志入库
         */
        HttpServletRequest request = HttpHelper.getHttpServletRequest();
        SysLog sl = new SysLog();
        Token tk = SSOHelper.attrToken(request);
        if ( tk != null ) {
            sl.setUid(tk.getId());
        }
        sl.setContent(operateContent(joinPoint, methodName, request));
        sl.setOperation(operate);
        sl.setCrTime(new Date());
        baseMapper.insert(sl);
    }

    /**
     * 获取当前执行的方法
     *
     * @param joinPoint
     *            连接点
     * @param methodName
     *            方法名称
     * @return 操作内容
     */
    @SuppressWarnings("unchecked")
    public String operateContent(ProceedingJoinPoint joinPoint, String methodName, HttpServletRequest request) {
        String className = joinPoint.getTarget().getClass().getName();
        Object[] params = joinPoint.getArgs();
        StringBuffer bf = new StringBuffer();
        if (params != null && params.length > 0) {
            Enumeration<String> paraNames = request.getParameterNames();
            while (paraNames.hasMoreElements()) {
                String key = paraNames.nextElement();
                bf.append(key).append("=");
                bf.append(request.getParameter(key)).append("&");
            }
            if (StringUtils.isBlank(bf.toString())) {
                bf.append(request.getQueryString());
            }
        }
        return String.format(LOG_CONTENT, className, methodName, bf.toString(), IpHelper.getIpAddr(request));
    }
}