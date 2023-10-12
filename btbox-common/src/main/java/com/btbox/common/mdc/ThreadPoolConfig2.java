// package com.btbox.common.mdc;
//
// import lombok.extern.slf4j.Slf4j;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.scheduling.annotation.EnableAsync;
//
// import java.util.concurrent.Executor;
// import java.util.concurrent.ThreadPoolExecutor;
//
// @Slf4j
// @Configuration
// // @EnableAsync
// public class ThreadPoolConfig2 {
//
//     @Bean("normalThreadPool")
//     public Executor executorNormal() {
//         MdcThreadPoolTaskExecutor executor = new MdcThreadPoolTaskExecutor();
//         executor.setCorePoolSize(1);
//         executor.setMaxPoolSize(2);
//         executor.setQueueCapacity(1);
//         executor.setKeepAliveSeconds(60);
//         executor.setThreadNamePrefix("NORMAL--");
//         executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
//         executor.initialize();
//
//         return executor;
//     }
//
//     @Bean("scheduleThreadPool")
//     public Executor executorSchedule() {
//         MdcThreadPoolTaskExecutor executor = new MdcThreadPoolTaskExecutor();
//         executor.setCorePoolSize(5);
//         executor.setMaxPoolSize(8);
//         executor.setQueueCapacity(2);
//         executor.setKeepAliveSeconds(60);
//         executor.setThreadNamePrefix("SCHEDULE--");
//         executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
//         executor.initialize();
//
//         return executor;
//     }
// }