package com.btbox.demo.controller;

import com.btbox.demo.service.impl.MdcThreadTaskServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Slf4j
@RestController
@RequestMapping("/mdc")
@RequiredArgsConstructor
public class TestMdcThreadTaskController {

    private final MdcThreadTaskServiceImpl taskService;
    
    @GetMapping(value = "/thread/start")
    public String getValue() throws ExecutionException, InterruptedException {
        MdcThreadTaskServiceImpl.threadNum++;
        CompletableFuture<String> result = taskService.task(MdcThreadTaskServiceImpl.threadNum);
        System.out.println("result = " + result.get());
        return "hello...";
    }

}