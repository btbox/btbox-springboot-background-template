package com.btbox.demo.service;

import java.util.concurrent.CompletableFuture;

/**
 * @description: MDC线程测试任务
 * @author: BT-BOX
 * @createDate: 2023/10/12 16:56
 * @version: 1.0
 */
public interface MdcThreadTaskService {

    CompletableFuture<String> task(int index);

}
