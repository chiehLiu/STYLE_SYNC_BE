package StyleSync.demo.controller;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import StyleSync.demo.filter.RequestLoggingFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 處理request和response相關共用函數
 */
public class BaseController implements ApplicationContextAware {

    protected static ApplicationContext applicationContext;

    @Autowired
    protected HttpServletRequest httpReq;

    @Autowired
    protected HttpServletResponse httpRes;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        BaseController.applicationContext = applicationContext;
    }

    protected String getRequestLogTraceId() {
        return MDC.get(RequestLoggingFilter.SESSION_KEY);
    }

    protected String getClientIp(HttpServletRequest req) {
        String clientIp = req.getHeader("Client_IP");
        if (StringUtils.isBlank(clientIp) || "unknown".equalsIgnoreCase(clientIp)) clientIp = req.getHeader("X-FORWARDED-FOR");
        if (StringUtils.isBlank(clientIp) || "unknown".equalsIgnoreCase(clientIp)) clientIp = req.getHeader("X-Forwarded-For");
        if (StringUtils.isBlank(clientIp) || "unknown".equalsIgnoreCase(clientIp)) clientIp = req.getHeader("x-forwarded-for");
        if (StringUtils.isBlank(clientIp) || "unknown".equalsIgnoreCase(clientIp)) clientIp = req.getHeader("Proxy-Client-IP");
        if (StringUtils.isBlank(clientIp) || "unknown".equalsIgnoreCase(clientIp)) clientIp = req.getHeader("WL-Proxy-Client-IP");
        if (StringUtils.isBlank(clientIp) || "unknown".equalsIgnoreCase(clientIp)) clientIp = req.getRemoteAddr();

        return clientIp;
    }
}