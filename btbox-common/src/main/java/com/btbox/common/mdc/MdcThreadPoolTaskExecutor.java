package com.btbox.common.mdc;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * MDC线程池配置类
 */
@Slf4j
public class MdcThreadPoolTaskExecutor extends ThreadPoolTaskExecutor {

    /**
     * 接口请求开启的异步线程会调用下述方法
     */
    @Override
    public void execute(Runnable task) {
        // 复制主线程MDC
        Map<String, String> context = MDC.getCopyOfContextMap();
        super.execute(() -> {
            if (null != context) {
                // 主线程MDC赋予子线程
                MDC.setContextMap(context);
            } else {
                // 主线程没有MDC就自己生成一个
                RequestIdUtil.setRequestId();
            }
            try {
                task.run();
            } finally {
                try {
                    RequestIdUtil.clear();
                } catch (Exception e) {
                    log.warn("MDC clear exception：{}", e.getMessage());
                }
            }
        });
    }

    /**
     * 定时任务会调用下述方法
     */
    @Override
    public <T> Future<T> submit(Callable<T> task) {
        Map<String, String> context = MDC.getCopyOfContextMap();
        return super.submit(() -> {
            if (null != context) {
                // 主线程MDC赋予子线程
                MDC.setContextMap(context);
            } else {
                // 主线程没有MDC就自己生成一个
                RequestIdUtil.setRequestId();
            }
            try {
                return task.call();
            } finally {
                try {
                    RequestIdUtil.clear();
                } catch (Exception e) {
                    log.warn("MDC clear exception：{}", e.getMessage());
                }
            }
        });
    }
}