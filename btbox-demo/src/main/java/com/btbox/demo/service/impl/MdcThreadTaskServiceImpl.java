package com.btbox.demo.service.impl;

import com.btbox.demo.service.MdcThreadTaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class MdcThreadTaskServiceImpl implements MdcThreadTaskService {
    public static int threadNum = 0;

    @Override
    @Async("threadPoolTaskExecutor")
    public CompletableFuture<String> task(int index) {
        String result = "asy start...";
        log.info("task {} start...", index);
        try {
            Thread.sleep(10000);
            result = "asy end...";
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("task {} end...", index);
        return CompletableFuture.completedFuture(result);
    }
}