package com.btbox.demo.quartz;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Slf4j
@Configuration
@EnableScheduling
public class ScheduleTask {
    private static int count = 0;
    private ThreadLocal<Integer> num = new ThreadLocal<>();
 
    @Async("schedulePoolTaskExecutor")
    // @Scheduled(initialDelay = 3000, fixedDelay = 1000 * 5)
    public void task() {
        count++;
        num.set(count);
        log.info("内部定时线程 {} 开启...", num.get());
        getTask();
    }
 
    private void getTask() {
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("内部定时线程 {} 结束", num.get());
    }
}
 