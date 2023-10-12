package com.btbox.common.mdc;

import cn.hutool.core.lang.UUID;
import org.slf4j.MDC;

/**
 * MDC工具类
 */
public class RequestIdUtil {
    public static final String REQUEST_ID = "traceId";
 
    public static void setRequestId() {
        MDC.put(REQUEST_ID, UUID.randomUUID().toString());
    }
    
    public static String getRequestId() {
        return MDC.get(REQUEST_ID);
    }
 
    public static void clear() {
        MDC.clear();
    }
}
 