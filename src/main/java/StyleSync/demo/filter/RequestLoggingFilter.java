package StyleSync.demo.filter;

import java.util.UUID;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.MDC;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

/**
 * 日誌攔截器组件，在输出日誌中加上sessionId
 */
public class RequestLoggingFilter extends CommonsRequestLoggingFilter {

    public final static String SESSION_KEY = "sessionId";

    @Override
    protected boolean shouldLog(HttpServletRequest request) {
        return true;
    }

    @Override
    protected void beforeRequest(HttpServletRequest request, String message) {
        setMDC();
    }

    @Override
    protected void afterRequest(HttpServletRequest request, String message) {
        MDC.remove(SESSION_KEY);
    }

    public static void setMDC() {
        String token = UUID.randomUUID().toString().replace("-", "");
        MDC.put(SESSION_KEY, token);
    }

    public static void removeMDC() {
        MDC.remove(SESSION_KEY);
    }
}